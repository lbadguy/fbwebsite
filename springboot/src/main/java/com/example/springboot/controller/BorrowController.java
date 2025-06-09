package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.entity.Borrow;
import com.example.springboot.service.BorrowService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * 借阅控制器
 */
@RestController
@RequestMapping("/borrow")
public class BorrowController {
    @Resource
    private BorrowService borrowService;

    /**
     * 借书
     */
    @PostMapping("/borrow")
    public Result borrowBook(@RequestParam Long bookId, 
                           @RequestParam Long readerId,
                           @RequestParam Long operatorId) {
        int result = borrowService.borrowBook(bookId, readerId, operatorId);
        if (result > 0) {
            return Result.success("借书成功");
        } else if (result == -1) {
            return Result.error("图书不可借");
        } else if (result == -2) {
            return Result.error("读者不可借书");
        } else {
            return Result.error("借书失败");
        }
    }
    
    /**
     * 添加借阅记录
     */
    @PostMapping("/add")
    public Result add(@RequestBody Borrow borrow) {
        int result = borrowService.borrowBook(borrow.getBookId(), borrow.getReaderId(), borrow.getOperatorId());
        if (result > 0) {
            return Result.success("借阅成功");
        } else if (result == -1) {
            return Result.error("图书不可借");
        } else if (result == -2) {
            return Result.error("读者不可借书");
        } else {
            return Result.error("借阅失败");
        }
    }

    /**
     * 还书
     */
    @PostMapping("/return")
    public Result returnBook(@RequestParam Long borrowId, 
                           @RequestParam Long operatorId) {
        int result = borrowService.returnBook(borrowId, operatorId);
        if (result > 0) {
            return Result.success("还书成功");
        } else if (result == -1) {
            return Result.error("借阅记录不存在");
        } else if (result == -2) {
            return Result.error("状态不正确");
        } else {
            return Result.error("还书失败");
        }
    }

    /**
     * 续借
     */
    @PostMapping("/renew")
    public Result renewBook(@RequestParam Long borrowId, 
                          @RequestParam Long operatorId) {
        int result = borrowService.renewBook(borrowId, operatorId);
        if (result > 0) {
            return Result.success("续借成功");
        } else if (result == -1) {
            return Result.error("借阅记录不存在");
        } else if (result == -2) {
            return Result.error("状态不正确");
        } else if (result == -3) {
            return Result.error("已逾期，不能续借");
        } else {
            return Result.error("续借失败");
        }
    }

    /**
     * 获取借阅记录详情
     */
    @GetMapping("/{borrowId}")
    public Result getById(@PathVariable Long borrowId) {
        Borrow borrow = borrowService.getById(borrowId);
        if (borrow != null) {
            return Result.success(borrow);
        } else {
            return Result.error("借阅记录不存在");
        }
    }

    /**
     * 获取读者的借阅记录
     */
    @GetMapping("/reader/{readerId}")
    public Result getByReader(@PathVariable Long readerId) {
        List<Borrow> list = borrowService.getByReader(readerId);
        return Result.success(list);
    }

    /**
     * 获取图书的借阅记录
     */
    @GetMapping("/book/{bookId}")
    public Result getByBook(@PathVariable Long bookId) {
        List<Borrow> list = borrowService.getByBook(bookId);
        return Result.success(list);
    }

    /**
     * 分页查询借阅记录
     */
    @GetMapping("/page")
    public Result page(Borrow borrow, 
                      @RequestParam(defaultValue = "1") Integer pageNum, 
                      @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Borrow> pageInfo = borrowService.page(borrow, pageNum, pageSize);
        return Result.success(pageInfo);
    }

    /**
     * 查询所有借阅记录
     */
    @GetMapping("/list")
    public Result list() {
        List<Borrow> list = borrowService.getAll();
        return Result.success(list);
    }

    /**
     * 查询逾期未还记录
     */
    @GetMapping("/overdue")
    public Result getOverdue() {
        List<Borrow> list = borrowService.getOverdue();
        return Result.success(list);
    }

    /**
     * 检查读者是否可以借书
     */
    @GetMapping("/check/reader/{readerId}")
    public Result canBorrow(@PathVariable Long readerId) {
        boolean canBorrow = borrowService.canBorrow(readerId);
        return Result.success(canBorrow);
    }

    /**
     * 检查图书是否可借
     */
    @GetMapping("/check/book/{bookId}")
    public Result isBookAvailable(@PathVariable Long bookId) {
        boolean isAvailable = borrowService.isBookAvailable(bookId);
        return Result.success(isAvailable);
    }
    
    /**
     * 根据关键字搜索借阅记录
     */
    @GetMapping("/search")
    public Result search(@RequestParam String keyword) {
        List<Borrow> list = borrowService.search(keyword);
        if (list != null && !list.isEmpty()) {
            return Result.success(list.get(0));
        } else {
            return Result.error("未找到借阅记录");
        }
    }
    
    /**
     * 根据读者信息搜索借阅记录
     */
    @GetMapping("/reader/search")
    public Result searchByReader(@RequestParam String keyword) {
        List<Borrow> list = borrowService.searchByReader(keyword);
        return Result.success(list);
    }
}