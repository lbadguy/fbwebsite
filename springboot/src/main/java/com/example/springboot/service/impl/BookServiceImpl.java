package com.example.springboot.service.impl;

import com.example.springboot.Mapper.BookMapper;
import com.example.springboot.entity.Book;
import com.example.springboot.service.BookService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 图书服务实现类
 */
@Service
public class BookServiceImpl implements BookService {
    @Resource
    private BookMapper bookMapper;

    @Override
    public int add(Book book) {
        if (book.getStatus() == null) {
            book.setStatus(1); // 默认可借
        }
        return bookMapper.insert(book);
    }

    @Override
    public int update(Book book) {
        return bookMapper.update(book);
    }

    @Override
    public int delete(Long bookId) {
        return bookMapper.deleteById(bookId);
    }

    @Override
    public Book getById(Long bookId) {
        return bookMapper.selectById(bookId);
    }

    @Override
    public Book getByIsbn(String isbn) {
        return bookMapper.selectByIsbn(isbn);
    }

    @Override
    public PageInfo<Book> page(Book book, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Book> list = bookMapper.selectByCondition(book);
        return new PageInfo<>(list);
    }

    @Override
    public List<Book> getAll() {
        return bookMapper.selectAll();
    }

    @Override
    public int updateStatus(Long bookId, Integer status) {
        return bookMapper.updateStatus(bookId, status);
    }

    @Override
    public List<Book> getByCategory(Long categoryId) {
        return bookMapper.selectByCategoryId(categoryId);
    }
}