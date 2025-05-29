package com.example.springboot.service;

import cn.hutool.core.util.StrUtil;
import com.example.springboot.Mapper.AdminMapper;
import com.example.springboot.entity.Account;
import com.example.springboot.entity.Admin;
import com.example.springboot.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Resource
    private AdminMapper adminMapper;

    public void add(Admin admin) {
        String username = admin.getUsername();//  获取用户名
        Admin dbAdmin = adminMapper.selectByUsername(username);
        if (dbAdmin != null) {
            throw new CustomException("500", "账号已存在");
        }
        if (StrUtil.isBlank(admin.getPassword())) {
            admin.setPassword("admin");
        }
        if (StrUtil.isBlank(admin.getName())) {
            admin.setName(admin.getUsername());
        }
        admin.setRole("ADM");
        adminMapper.insert(admin);

    }

    public void deleteById(Integer id) {
        adminMapper.deleteById(id);
    }

    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            this.deleteById(id);
        }
    }

    public void update(Admin admin) {
        adminMapper.updateById(admin);
    }

    public Admin selectById(Integer id) {
        return adminMapper.selectById(id);
    }


    public List<Admin> selectAll(Admin admin) {

        return adminMapper.selectAll(admin);
    }


    public PageInfo<Admin> selectPage(Admin admin, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Admin> list = adminMapper.selectAll(admin);
        return PageInfo.of(list);
    }


    public Admin login(Account account) {
        String username = account.getUsername();//  获取用户名
        Admin dbAdmin = adminMapper.selectByUsername(username);
        if (dbAdmin == null) {
            throw new CustomException("500", "账号不存在");
        }
        String password = account.getPassword();//  获取密码
        if (!dbAdmin.getPassword().equals(password)) {
            throw new CustomException("500", "账户或密码错误");
        }
        return dbAdmin;
    }

    public void updatePassword(Account account) {
        Integer id = account.getId();
        Admin admin = this.selectById(id);
        if (!admin.getPassword().equals(account.getPassword())) {
            throw new CustomException("500", "原密码错误");
        }
        admin.setPassword(account.getNewPassword());
        this.update(admin);
    }
}
