package com.example.springboot.Mapper;

import com.example.springboot.entity.Reader;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 读者Mapper接口
 */
public interface ReaderMapper {
    /**
     * 添加读者
     */
    int insert(Reader reader);

    /**
     * 更新读者
     */
    int update(Reader reader);

    /**
     * 删除读者
     */
    int deleteById(Long readerId);

    /**
     * 根据ID查询读者
     */
    Reader selectById(Long readerId);

    /**
     * 根据证件号查询读者
     */
    Reader selectByCardId(String cardId);

    /**
     * 根据用户ID查询读者
     */
    Reader selectByUserId(Long userId);

    /**
     * 条件查询读者
     */
    List<Reader> selectByCondition(Reader reader);

    /**
     * 查询所有读者
     */
    List<Reader> selectAll();

    /**
     * 更新读者状态
     */
    int updateStatus(@Param("readerId") Long readerId, @Param("status") Integer status);
} 