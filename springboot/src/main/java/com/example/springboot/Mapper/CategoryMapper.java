package com.example.springboot.Mapper;

import com.example.springboot.entity.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 图书分类Mapper接口
 */
public interface CategoryMapper {
    /**
     * 添加分类
     */
    int insert(Category category);

    /**
     * 更新分类
     */
    int update(Category category);

    /**
     * 删除分类
     */
    int deleteById(Long categoryId);

    /**
     * 根据ID查询分类
     */
    Category selectById(Long categoryId);

    /**
     * 根据名称查询分类
     */
    Category selectByName(String name);

    /**
     * 根据父ID查询分类
     */
    List<Category> selectByParentId(Long parentId);

    /**
     * 条件查询分类
     */
    List<Category> selectByCondition(Category category);

    /**
     * 查询所有分类
     */
    List<Category> selectAll();
} 