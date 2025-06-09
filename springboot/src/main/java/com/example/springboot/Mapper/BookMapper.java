package com.example.springboot.Mapper;

import com.example.springboot.entity.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 图书Mapper接口
 */
public interface BookMapper {
    /**
     * 添加图书
     */
    int insert(Book book);

    /**
     * 更新图书
     */
    int update(Book book);

    /**
     * 删除图书
     */
    int deleteById(Long bookId);

    /**
     * 根据ID查询图书
     */
    Book selectById(Long bookId);

    /**
     * 根据ISBN查询图书
     */
    Book selectByIsbn(String isbn);

    /**
     * 条件查询图书
     */
    List<Book> selectByCondition(Book book);

    /**
     * 查询所有图书
     */
    List<Book> selectAll();

    /**
     * 更新图书状态
     */
    int updateStatus(@Param("bookId") Long bookId, @Param("status") Integer status);

    /**
     * 根据分类ID查询图书
     */
    List<Book> selectByCategoryId(Long categoryId);
} 