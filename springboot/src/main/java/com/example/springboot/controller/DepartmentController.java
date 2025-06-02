package com.example.springboot.controller;


import com.example.springboot.common.Result;
import com.example.springboot.entity.Department;
import com.example.springboot.service.DepartmentService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Resource
    private DepartmentService departmentService;

    @PostMapping("/add")
    public Result add(@RequestBody Department department) {
        departmentService.add(department);
        return Result.success();
    }

    //    删除单个数据
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable Integer id) {
        departmentService.deleteById(id);
        return Result.success();
    }

    //删除多行数据,必须用RequestBody接收数组
    @DeleteMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        departmentService.deleteBatch(ids);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody Department department) {
        departmentService.update(department);
        return Result.success();
    }

    @GetMapping("/selectAll")
    public Result selectAll(Department department) {
        List<Department> list = departmentService.selectAll(department);
        return Result.success(list);
    }

    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Department department = departmentService.selectById(id);
        return Result.success(department);
    }

    @GetMapping("/selectOne")
    public Result selectOne(@RequestParam Integer id) {
        Department department = departmentService.selectById(id);
        return Result.success(department);
    }

    @GetMapping("/selectPage")
    public Result selectPage(Department department,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Department> pageInfo = departmentService.selectPage(department, pageNum, pageSize); // 这里可能需要传入 pageNum 和 pageSize
        return Result.success(pageInfo);  // 这里的 list 可能未定义，可能需要替换为实际的数据
    }
}
