package com.example.springboot.service.impl;

import com.example.springboot.Mapper.ReaderMapper;
import com.example.springboot.entity.Reader;
import com.example.springboot.service.ReaderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 读者服务实现类
 */
@Service
public class ReaderServiceImpl implements ReaderService {
    @Resource
    private ReaderMapper readerMapper;

    @Override
    public int add(Reader reader) {
        if (reader.getStatus() == null) {
            reader.setStatus(1); // 默认正常状态
        }
        if (reader.getMaxBorrowNum() == null) {
            reader.setMaxBorrowNum(5); // 默认最大借阅数量
        }
        if (reader.getMaxBorrowDays() == null) {
            reader.setMaxBorrowDays(30); // 默认最大借阅天数
        }
        reader.setCreateTime(new Date());
        reader.setUpdateTime(new Date());
        return readerMapper.insert(reader);
    }

    @Override
    public int update(Reader reader) {
        reader.setUpdateTime(new Date());
        return readerMapper.update(reader);
    }

    @Override
    public int delete(Long readerId) {
        return readerMapper.deleteById(readerId);
    }

    @Override
    public Reader getById(Long readerId) {
        return readerMapper.selectById(readerId);
    }

    @Override
    public Reader getByCardId(String cardId) {
        return readerMapper.selectByCardId(cardId);
    }

    @Override
    public PageInfo<Reader> page(Reader reader, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Reader> list = readerMapper.selectByCondition(reader);
        return new PageInfo<>(list);
    }

    @Override
    public List<Reader> getAll() {
        return readerMapper.selectAll();
    }

    @Override
    public int updateStatus(Long readerId, Integer status) {
        return readerMapper.updateStatus(readerId, status);
    }
} 