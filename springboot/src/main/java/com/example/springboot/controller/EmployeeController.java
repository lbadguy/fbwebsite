package com.example.springboot.controller;


import com.example.springboot.common.Result;
import com.example.springboot.entity.Employee;
import com.example.springboot.service.EmployeeService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Resource
    private EmployeeService employeeService;

    @PostMapping("/add")
    public Result add(@RequestBody Employee employee) {
        employeeService.add(employee);
        return Result.success();
    }

    //    删除单个数据
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable Integer id) {
        employeeService.deleteById(id);
        return Result.success();
    }

    //删除多行数据,必须用RequestBody接收数组
    @DeleteMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        employeeService.deleteBatch(ids);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody Employee employee) {
        employeeService.update(employee);
        return Result.success();
    }

    @GetMapping("/selectAll")
    public Result selectAll(Employee employee) {
        List<Employee> list = employeeService.selectAll(employee);
        return Result.success(list);
    }

    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Employee employee = employeeService.selectById(id);
        return Result.success(employee);
    }

    @GetMapping("/selectOne")
    public Result selectOne(@RequestParam Integer id) {
        Employee employee = employeeService.selectById(id);
        return Result.success(employee);
    }

    @GetMapping("/selectPage")
    public Result selectPage(Employee employee,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Employee> pageInfo = employeeService.selectPage(employee, pageNum, pageSize); // 这里可能需要传入 pageNum 和 pageSize
        return Result.success(pageInfo);  // 这里的 list 可能未定义，可能需要替换为实际的数据
    }
}
