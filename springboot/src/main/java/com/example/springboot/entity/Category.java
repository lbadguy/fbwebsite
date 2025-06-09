package com.example.springboot.entity;

/**
 * 图书分类实体类
 */
public class Category {
    private Long categoryId;     // 分类ID
    private String name;         // 分类名称
    private Long parentId;       // 父分类ID
    private String description;  // 描述
    
    // 额外字段，不在数据库中
    private String parentName;   // 父分类名称

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
} 