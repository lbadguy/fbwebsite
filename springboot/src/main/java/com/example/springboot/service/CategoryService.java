package com.example.springboot.service;

import com.example.springboot.entity.Category;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 图书分类服务接口
 */
public interface CategoryService {
    /**
     * 添加分类
     */
    int add(Category category);

    /**
     * 修改分类
     */
    int update(Category category);

    /**
     * 删除分类
     */
    int delete(Long categoryId);

    /**
     * 获取分类详情
     */
    Category getById(Long categoryId);

    /**
     * 分页查询分类
     */
    PageInfo<Category> page(Category category, Integer pageNum, Integer pageSize);

    /**
     * 获取所有分类
     */
    List<Category> getAll();

    /**
     * 获取子分类
     */
    List<Category> getChildren(Long parentId);
} 