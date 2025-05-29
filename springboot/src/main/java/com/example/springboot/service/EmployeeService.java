package com.example.springboot.service;

import cn.hutool.core.util.StrUtil;
import com.example.springboot.Mapper.EmployeeMapper;
import com.example.springboot.entity.Account;
import com.example.springboot.entity.Admin;
import com.example.springboot.entity.Employee;
import com.example.springboot.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

import java.util.List;

@Service
public class EmployeeService {
    @Resource
    private EmployeeMapper employeeMapper;

    public void add(Employee employee) {
        String username = employee.getUsername();//  获取用户名
        Employee dbEmployee = employeeMapper.selectByUsername(username);
        if (dbEmployee != null) {
            throw new CustomException("500", "账号已存在");
        }

        Employee dbEmployee1 = employeeMapper.selectByNo(employee.getNo());
        if (dbEmployee1 != null) {
            throw new CustomException("500", "工号已存在，请再次确认是否正确");
        }

        if (StrUtil.isBlank(employee.getPassword())) {
            employee.setPassword("123");
        }
        if (StrUtil.isBlank(employee.getName())) {
            employee.setName(employee.getUsername());
        }
        employee.setRole("EMP");
        employeeMapper.insert(employee);

    }

    public void deleteById(Integer id) {
        employeeMapper.deleteById(id);
    }

    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            this.deleteById(id);
        }
    }

    public void update(Employee employee) {
        employeeMapper.updateById(employee);
    }

    public Employee selectById(Integer id) {
        return employeeMapper.selectById(id);
    }


    public List<Employee> selectAll(Employee employee) {

        return employeeMapper.selectAll(employee);
    }


    public PageInfo<Employee> selectPage(Employee employee, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Employee> list = employeeMapper.selectAll(employee);
        return PageInfo.of(list);
    }


    public Employee login(Account account) {
        String username = account.getUsername();//  获取用户名
        Employee dbEmployee = employeeMapper.selectByUsername(username);
        if (dbEmployee == null) {
            throw new CustomException("500", "账号不存在");
        }
        String password = account.getPassword();//  获取密码
        if (!dbEmployee.getPassword().equals(password)) {
            throw new CustomException("500", "账户或密码错误");
        }
        return dbEmployee;
    }

    public void register(Employee employee) {
        this.add(employee);
    }

    public void updatePassword(Account account) {
        Integer id = account.getId();
        Employee employee = this.selectById(id);
        if (!employee.getPassword().equals(account.getPassword())) {
            throw new CustomException("500", "原密码错误");
        }
        employee.setPassword(account.getNewPassword());
        this.update(employee);
    }
}
