package com.example.springboot.service;

import com.example.springboot.entity.Borrow;
import com.github.pagehelper.PageInfo;

import java.util.Date;
import java.util.List;

/**
 * 借阅服务接口
 */
public interface BorrowService {
    /**
     * 借书
     */
    int borrowBook(Long bookId, Long readerId, Long operatorId);

    /**
     * 还书
     */
    int returnBook(Long borrowId, Long operatorId);

    /**
     * 续借
     */
    int renewBook(Long borrowId, Long operatorId);

    /**
     * 获取借阅记录详情
     */
    Borrow getById(Long borrowId);

    /**
     * 获取读者的借阅记录
     */
    List<Borrow> getByReader(Long readerId);

    /**
     * 获取图书的借阅记录
     */
    List<Borrow> getByBook(Long bookId);

    /**
     * 分页查询借阅记录
     */
    PageInfo<Borrow> page(Borrow borrow, Integer pageNum, Integer pageSize);

    /**
     * 查询所有借阅记录
     */
    List<Borrow> getAll();

    /**
     * 查询逾期未还记录
     */
    List<Borrow> getOverdue();

    /**
     * 检查读者是否可以借书
     */
    boolean canBorrow(Long readerId);

    /**
     * 检查图书是否可借
     */
    boolean isBookAvailable(Long bookId);

    /**
     * 计算罚款金额
     */
    double calculateFine(Date dueDate, Date returnDate);

    /**
     * 根据关键字搜索借阅记录
     */
    List<Borrow> search(String keyword);

    /**
     * 根据读者信息搜索借阅记录
     */
    List<Borrow> searchByReader(String keyword);
} 