package com.example.springboot.Mapper;

import com.example.springboot.entity.Borrow;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 借阅Mapper接口
 */
public interface BorrowMapper {
    /**
     * 添加借阅记录
     */
    int insert(Borrow borrow);

    /**
     * 更新借阅记录
     */
    int update(Borrow borrow);

    /**
     * 删除借阅记录
     */
    int deleteById(Long borrowId);

    /**
     * 根据ID查询借阅记录
     */
    Borrow selectById(Long borrowId);

    /**
     * 根据图书ID查询借阅记录
     */
    List<Borrow> selectByBookId(Long bookId);

    /**
     * 根据读者ID查询借阅记录
     */
    List<Borrow> selectByReaderId(Long readerId);

    /**
     * 根据状态查询借阅记录
     */
    List<Borrow> selectByStatus(Integer status);

    /**
     * 条件查询借阅记录
     */
    List<Borrow> selectByCondition(Borrow borrow);

    /**
     * 查询所有借阅记录
     */
    List<Borrow> selectAll();

    /**
     * 更新借阅状态
     */
    int updateStatus(@Param("borrowId") Long borrowId, @Param("status") Integer status);

    /**
     * 更新归还日期和状态
     */
    int updateReturnInfo(@Param("borrowId") Long borrowId, @Param("returnDate") Date returnDate, 
                        @Param("status") Integer status, @Param("fine") BigDecimal fine);

    /**
     * 查询逾期未还的借阅记录
     */
    List<Borrow> selectOverdue();

    /**
     * 根据当前日期查询逾期未还记录
     */
    List<Borrow> selectOverdueByDate(Date currentDate);
    
    /**
     * 根据关键字搜索借阅记录
     */
    List<Borrow> search(@Param("keyword") String keyword);
    
    /**
     * 根据读者信息搜索借阅记录
     */
    List<Borrow> searchByReader(@Param("keyword") String keyword);
}