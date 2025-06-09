package com.example.springboot.service.impl;

import com.example.springboot.Mapper.CategoryMapper;
import com.example.springboot.entity.Category;
import com.example.springboot.service.CategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * 图书分类服务实现类
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public int add(Category category) {
        return categoryMapper.insert(category);
    }

    @Override
    public int update(Category category) {
        return categoryMapper.update(category);
    }

    @Override
    public int delete(Long categoryId) {
        return categoryMapper.deleteById(categoryId);
    }

    @Override
    public Category getById(Long categoryId) {
        return categoryMapper.selectById(categoryId);
    }

    @Override
    public PageInfo<Category> page(Category category, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Category> list = categoryMapper.selectByCondition(category);
        return new PageInfo<>(list);
    }

    @Override
    public List<Category> getAll() {
        return categoryMapper.selectAll();
    }

    @Override
    public List<Category> getChildren(Long parentId) {
        return categoryMapper.selectByParentId(parentId);
    }
} 