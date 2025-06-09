package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.entity.Category;
import com.example.springboot.service.CategoryService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * 图书分类控制器
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    /**
     * 添加分类
     */
    @PostMapping("/add")
    public Result add(@RequestBody Category category) {
        int result = categoryService.add(category);
        if (result > 0) {
            return Result.success("添加分类成功");
        } else {
            return Result.error("添加分类失败");
        }
    }

    /**
     * 修改分类
     */
    @PutMapping("/update")
    public Result update(@RequestBody Category category) {
        int result = categoryService.update(category);
        if (result > 0) {
            return Result.success("修改分类成功");
        } else {
            return Result.error("修改分类失败");
        }
    }

    /**
     * 删除分类
     */
    @DeleteMapping("/{categoryId}")
    public Result delete(@PathVariable Long categoryId) {
        int result = categoryService.delete(categoryId);
        if (result > 0) {
            return Result.success("删除分类成功");
        } else {
            return Result.error("删除分类失败");
        }
    }

    /**
     * 获取分类详情
     */
    @GetMapping("/{categoryId}")
    public Result getById(@PathVariable Long categoryId) {
        Category category = categoryService.getById(categoryId);
        if (category != null) {
            return Result.success(category);
        } else {
            return Result.error("分类不存在");
        }
    }

    /**
     * 分页查询分类
     */
    @GetMapping("/page")
    public Result page(Category category, 
                      @RequestParam(defaultValue = "1") Integer pageNum, 
                      @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Category> pageInfo = categoryService.page(category, pageNum, pageSize);
        return Result.success(pageInfo);
    }

    /**
     * 获取所有分类
     */
    @GetMapping("/list")
    public Result list() {
        List<Category> list = categoryService.getAll();
        return Result.success(list);
    }

    /**
     * 获取子分类
     */
    @GetMapping("/children/{parentId}")
    public Result getChildren(@PathVariable Long parentId) {
        List<Category> list = categoryService.getChildren(parentId);
        return Result.success(list);
    }
} 