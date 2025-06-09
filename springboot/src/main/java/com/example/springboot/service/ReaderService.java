package com.example.springboot.service;

import com.example.springboot.entity.Reader;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 读者服务接口
 */
public interface ReaderService {
    /**
     * 添加读者
     */
    int add(Reader reader);

    /**
     * 修改读者
     */
    int update(Reader reader);

    /**
     * 删除读者
     */
    int delete(Long readerId);

    /**
     * 获取读者详情
     */
    Reader getById(Long readerId);

    /**
     * 根据证件号获取读者
     */
    Reader getByCardId(String cardId);

    /**
     * 分页查询读者
     */
    PageInfo<Reader> page(Reader reader, Integer pageNum, Integer pageSize);

    /**
     * 获取所有读者
     */
    List<Reader> getAll();

    /**
     * 更新读者状态
     */
    int updateStatus(Long readerId, Integer status);
} 