package com.example.springboot.service;

import com.example.springboot.entity.Book;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 图书服务接口
 */
public interface BookService {
    /**
     * 添加图书
     */
    int add(Book book);

    /**
     * 修改图书
     */
    int update(Book book);

    /**
     * 删除图书
     */
    int delete(Long bookId);

    /**
     * 查询图书详情
     */
    Book getById(Long bookId);

    /**
     * 根据ISBN查询图书
     */
    Book getByIsbn(String isbn);

    /**
     * 分页查询图书
     */
    PageInfo<Book> page(Book book, Integer pageNum, Integer pageSize);

    /**
     * 获取所有图书
     */
    List<Book> getAll();

    /**
     * 更新图书状态
     */
    int updateStatus(Long bookId, Integer status);

    /**
     * 根据分类获取图书
     */
    List<Book> getByCategory(Long categoryId);
} 