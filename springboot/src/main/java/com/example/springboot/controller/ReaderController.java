package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.entity.Reader;
import com.example.springboot.service.ReaderService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * 读者控制器
 */
@RestController
@RequestMapping("/reader")
public class ReaderController {
    @Resource
    private ReaderService readerService;

    /**
     * 添加读者
     */
    @PostMapping("/add")
    public Result add(@RequestBody Reader reader) {
        int result = readerService.add(reader);
        if (result > 0) {
            return Result.success("添加读者成功");
        } else {
            return Result.error("添加读者失败");
        }
    }

    /**
     * 修改读者
     */
    @PutMapping("/update")
    public Result update(@RequestBody Reader reader) {
        int result = readerService.update(reader);
        if (result > 0) {
            return Result.success("修改读者成功");
        } else {
            return Result.error("修改读者失败");
        }
    }

    /**
     * 删除读者
     */
    @DeleteMapping("/{readerId}")
    public Result delete(@PathVariable Long readerId) {
        int result = readerService.delete(readerId);
        if (result > 0) {
            return Result.success("删除读者成功");
        } else {
            return Result.error("删除读者失败");
        }
    }

    /**
     * 获取读者详情
     */
    @GetMapping("/{readerId}")
    public Result getById(@PathVariable Long readerId) {
        Reader reader = readerService.getById(readerId);
        if (reader != null) {
            return Result.success(reader);
        } else {
            return Result.error("读者不存在");
        }
    }

    /**
     * 根据证件号获取读者
     */
    @GetMapping("/card/{cardId}")
    public Result getByCardId(@PathVariable String cardId) {
        Reader reader = readerService.getByCardId(cardId);
        if (reader != null) {
            return Result.success(reader);
        } else {
            return Result.error("读者不存在");
        }
    }

    /**
     * 分页查询读者
     */
    @GetMapping("/page")
    public Result page(Reader reader, 
                      @RequestParam(defaultValue = "1") Integer pageNum, 
                      @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Reader> pageInfo = readerService.page(reader, pageNum, pageSize);
        return Result.success(pageInfo);
    }

    /**
     * 获取所有读者
     */
    @GetMapping("/list")
    public Result list() {
        List<Reader> list = readerService.getAll();
        return Result.success(list);
    }

    /**
     * 更新读者状态
     */
    @PutMapping("/status/{readerId}/{status}")
    public Result updateStatus(@PathVariable Long readerId, @PathVariable Integer status) {
        int result = readerService.updateStatus(readerId, status);
        if (result > 0) {
            return Result.success("更新读者状态成功");
        } else {
            return Result.error("更新读者状态失败");
        }
    }
} 