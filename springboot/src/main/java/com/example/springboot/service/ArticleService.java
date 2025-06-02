package com.example.springboot.service;

import cn.hutool.core.date.DateUtil;
import com.example.springboot.Mapper.ArticleMapper;
import com.example.springboot.entity.Article;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    @Resource
    private ArticleMapper articleMapper;

    public void add(Article article) {
        article.setTime(DateUtil.now());//获取当前时间字符串
        articleMapper.insert(article);

    }

    public void deleteById(Integer id) {
        articleMapper.deleteById(id);
    }

    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            this.deleteById(id);
        }
    }

    public void update(Article article) {
        articleMapper.updateById(article);
    }

    public Article selectById(Integer id) {
        return articleMapper.selectById(id);
    }


    public List<Article> selectAll(Article article) {

        return articleMapper.selectAll(article);
    }


    public PageInfo<Article> selectPage(Article article, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Article> list = articleMapper.selectAll(article);
        return PageInfo.of(list);
    }

    public Integer selectCountByDate(String date) {
        return articleMapper.selectCountByDate(date);
    }
}
