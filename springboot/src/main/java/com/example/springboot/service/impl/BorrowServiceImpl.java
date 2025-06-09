package com.example.springboot.service.impl;

import com.example.springboot.Mapper.BookMapper;
import com.example.springboot.Mapper.BorrowMapper;
import com.example.springboot.Mapper.ReaderMapper;
import com.example.springboot.entity.Book;
import com.example.springboot.entity.Borrow;
import com.example.springboot.entity.Reader;
import com.example.springboot.service.BorrowService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 借阅服务实现类
 */
@Service
public class BorrowServiceImpl implements BorrowService {
    @Resource
    private BorrowMapper borrowMapper;
    
    @Resource
    private BookMapper bookMapper;
    
    @Resource
    private ReaderMapper readerMapper;

    @Override
    @Transactional
    public int borrowBook(Long bookId, Long readerId, Long operatorId) {
        // 检查图书是否可借
        if (!isBookAvailable(bookId)) {
            return -1; // 图书不可借
        }
        
        // 检查读者是否可以借书
        if (!canBorrow(readerId)) {
            return -2; // 读者不可借
        }
        
        // 获取读者信息，确定借阅期限
        Reader reader = readerMapper.selectById(readerId);
        int borrowDays = reader.getMaxBorrowDays() != null ? reader.getMaxBorrowDays() : 30;
        
        // 创建借阅记录
        Borrow borrow = new Borrow();
        borrow.setBookId(bookId);
        borrow.setReaderId(readerId);
        borrow.setBorrowDate(new Date());
        
        // 设置归还日期
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, borrowDays);
        borrow.setDueDate(calendar.getTime());
        
        borrow.setStatus(1); // 已借出
        borrow.setOperatorId(operatorId);
        
        // 插入借阅记录
        int result = borrowMapper.insert(borrow);
        
        // 更新图书状态为已借出
        if (result > 0) {
            bookMapper.updateStatus(bookId, 0); // 更新为已借出
        }
        
        return result;
    }

    @Override
    @Transactional
    public int returnBook(Long borrowId, Long operatorId) {
        // 获取借阅记录
        Borrow borrow = borrowMapper.selectById(borrowId);
        if (borrow == null) {
            return -1; // 借阅记录不存在
        }
        
        // 只有在借状态才能还书
        if (borrow.getStatus() != 1 && borrow.getStatus() != 3) {
            return -2; // 状态不正确
        }
        
        Date returnDate = new Date();
        int status = 2; // 已归还
        
        // 计算罚款（如果逾期）
        BigDecimal fine = BigDecimal.ZERO;
        if (returnDate.after(borrow.getDueDate())) {
            double fineAmount = calculateFine(borrow.getDueDate(), returnDate);
            fine = BigDecimal.valueOf(fineAmount);
        }
        
        // 更新借阅记录
        int result = borrowMapper.updateReturnInfo(borrowId, returnDate, status, fine);
        
        // 更新图书状态为可借
        if (result > 0) {
            bookMapper.updateStatus(borrow.getBookId(), 1); // 更新为可借
        }
        
        return result;
    }

    @Override
    @Transactional
    public int renewBook(Long borrowId, Long operatorId) {
        // 获取借阅记录
        Borrow borrow = borrowMapper.selectById(borrowId);
        if (borrow == null) {
            return -1; // 借阅记录不存在
        }
        
        // 只有在借状态才能续借
        if (borrow.getStatus() != 1) {
            return -2; // 状态不正确
        }
        
        // 逾期不能续借
        if (new Date().after(borrow.getDueDate())) {
            return -3; // 已逾期
        }
        
        // 设置新的归还日期（从当前归还日期起延长30天）
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(borrow.getDueDate());
        calendar.add(Calendar.DAY_OF_MONTH, 30);
        Date newDueDate = calendar.getTime();
        
        // 更新借阅记录
        borrow.setDueDate(newDueDate);
        borrow.setStatus(3); // 已续借
        
        return borrowMapper.update(borrow);
    }

    @Override
    public Borrow getById(Long borrowId) {
        return borrowMapper.selectById(borrowId);
    }

    @Override
    public List<Borrow> getByReader(Long readerId) {
        return borrowMapper.selectByReaderId(readerId);
    }

    @Override
    public List<Borrow> getByBook(Long bookId) {
        return borrowMapper.selectByBookId(bookId);
    }

    @Override
    public PageInfo<Borrow> page(Borrow borrow, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Borrow> list = borrowMapper.selectByCondition(borrow);
        return new PageInfo<>(list);
    }

    @Override
    public List<Borrow> getAll() {
        return borrowMapper.selectAll();
    }

    @Override
    public List<Borrow> getOverdue() {
        return borrowMapper.selectOverdue();
    }

    @Override
    public boolean canBorrow(Long readerId) {
        // 获取读者信息
        Reader reader = readerMapper.selectById(readerId);
        if (reader == null || reader.getStatus() != 1) {
            return false; // 读者不存在或状态不正常
        }
        
        // 获取读者已借阅的图书数量
        List<Borrow> borrowList = borrowMapper.selectByReaderId(readerId);
        int borrowedCount = 0;
        for (Borrow borrow : borrowList) {
            if (borrow.getStatus() == 1 || borrow.getStatus() == 3) {
                borrowedCount++;
            }
        }
        
        // 检查是否超过最大借阅数量
        int maxBorrowNum = reader.getMaxBorrowNum() != null ? reader.getMaxBorrowNum() : 5;
        return borrowedCount < maxBorrowNum;
    }

    @Override
    public boolean isBookAvailable(Long bookId) {
        Book book = bookMapper.selectById(bookId);
        return book != null && book.getStatus() == 1;
    }

    @Override
    public double calculateFine(Date dueDate, Date returnDate) {
        if (dueDate == null || returnDate == null || returnDate.before(dueDate)) {
            return 0.0;
        }
        
        long diff = returnDate.getTime() - dueDate.getTime();
        long days = diff / (1000 * 60 * 60 * 24);
        
        // 罚款规则：每天1元
        return days * 1.0;
    }

    @Override
    public List<Borrow> search(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return new ArrayList<>();
        }
        
        // 尝试按ID查询
        try {
            Long borrowId = Long.parseLong(keyword);
            Borrow borrow = borrowMapper.selectById(borrowId);
            if (borrow != null) {
                List<Borrow> result = new ArrayList<>();
                result.add(borrow);
                return result;
            }
        } catch (NumberFormatException e) {
            // 不是数字，继续其他查询
        }
        
        // 按图书名称、读者姓名、借书证号等查询
        return borrowMapper.search(keyword);
    }
    
    @Override
    public List<Borrow> searchByReader(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return new ArrayList<>();
        }
        
        return borrowMapper.searchByReader(keyword);
    }
}