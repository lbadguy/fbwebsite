package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.entity.Book;
import com.example.springboot.service.BookService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 图书控制器
 */
@RestController
@RequestMapping("/book")
public class BookController {
    @Resource
    private BookService bookService;

    /**
     * 添加图书
     */
    @PostMapping
    public Result add(@RequestBody Book book) {
        int result = bookService.add(book);
        if (result > 0) {
            return Result.success("添加图书成功");
        } else {
            return Result.error("添加图书失败");
        }
    }

    /**
     * 修改图书
     */
    @PutMapping
    public Result update(@RequestBody Book book) {
        int result = bookService.update(book);
        if (result > 0) {
            return Result.success("修改图书成功");
        } else {
            return Result.error("修改图书失败");
        }
    }

    /**
     * 删除图书
     */
    @DeleteMapping("/{bookId}")
    public Result delete(@PathVariable Long bookId) {
        int result = bookService.delete(bookId);
        if (result > 0) {
            return Result.success("删除图书成功");
        } else {
            return Result.error("删除图书失败");
        }
    }

    /**
     * 获取图书详情
     */
    @GetMapping("/{bookId}")
    public Result getById(@PathVariable Long bookId) {
        Book book = bookService.getById(bookId);
        if (book != null) {
            return Result.success(book);
        } else {
            return Result.error("图书不存在");
        }
    }

    /**
     * 根据ISBN查询图书
     */
    @GetMapping("/isbn/{isbn}")
    public Result getByIsbn(@PathVariable String isbn) {
        Book book = bookService.getByIsbn(isbn);
        if (book != null) {
            return Result.success(book);
        } else {
            return Result.error("图书不存在");
        }
    }

    /**
     * 分页查询图书
     */
    @GetMapping("/page")
    public Result page(Book book, 
                      @RequestParam(defaultValue = "1") Integer pageNum, 
                      @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Book> pageInfo = bookService.page(book, pageNum, pageSize);
        return Result.success(pageInfo);
    }

    /**
     * 获取所有图书
     */
    @GetMapping("/list")
    public Result list() {
        List<Book> list = bookService.getAll();
        return Result.success(list);
    }

    /**
     * 更新图书状态
     */
    @PutMapping("/status/{bookId}/{status}")
    public Result updateStatus(@PathVariable Long bookId, @PathVariable Integer status) {
        int result = bookService.updateStatus(bookId, status);
        if (result > 0) {
            return Result.success("更新图书状态成功");
        } else {
            return Result.error("更新图书状态失败");
        }
    }

    /**
     * 根据分类获取图书
     */
    @GetMapping("/category/{categoryId}")
    public Result getByCategory(@PathVariable Long categoryId) {
        List<Book> list = bookService.getByCategory(categoryId);
        return Result.success(list);
    }
    
    /**
     * 获取可借阅图书
     */
    @GetMapping("/available")
    public Result getAvailableBooks() {
        List<Book> allBooks = bookService.getAll();
        List<Book> availableBooks = allBooks.stream()
                .filter(book -> book.getStatus() != null && book.getStatus() == 1)
                .collect(Collectors.toList());
        return Result.success(availableBooks);
    }
}