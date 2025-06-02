package com.example.springboot.controller;


import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.springboot.Mapper.DepartmentMapper;
import com.example.springboot.common.Result;
import com.example.springboot.entity.Department;
import com.example.springboot.entity.Employee;
import com.example.springboot.exception.CustomException;
import com.example.springboot.service.EmployeeService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Resource
    private EmployeeService employeeService;
    @Resource
    private DepartmentMapper departmentMapper;
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

    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        //1.拿到数据
        List<Employee> employeeList = employeeService.selectAll(null);
        // 2.构建ExcelWriter,写到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //3.设置中文表头
        writer.addHeaderAlias("username", "账号");
        writer.addHeaderAlias("name", "名称");
        writer.addHeaderAlias("sex", "性别");
        writer.addHeaderAlias("age", "年龄");
        writer.addHeaderAlias("no", "工号");
        writer.addHeaderAlias("description", "个人介绍");
        writer.addHeaderAlias("departmentName", "部门");
        writer.setOnlyAlias(true);
        // 4.写入数据
        writer.write(employeeList, true);
        // 5.输出到浏览器
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("员工信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
        // 6.关闭流
        ServletOutputStream os = response.getOutputStream();
        writer.flush(os);
        writer.close();
    }

    @PostMapping("/import")
    public Result importData(MultipartFile file) throws Exception {
        // 1.获取Excel数据
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        // 2.读取数据
        // 2.读取数据
        reader.addHeaderAlias("账号", "username");
        reader.addHeaderAlias("名称", "name");
        reader.addHeaderAlias("性别", "sex");
        reader.addHeaderAlias("年龄", "age");
        reader.addHeaderAlias("工号", "no");
        reader.addHeaderAlias("个人介绍", "description");
        reader.addHeaderAlias("部门", "departmentName");

        List<Employee> employeeList = reader.readAll(Employee.class);
        for (Employee employee : employeeList) {
            Department department = departmentMapper.selectByName(employee.getDepartmentName());
            if (department == null) {
                throw new CustomException("500", "部门不存在：" + employee.getDepartmentName());
            }
            employee.setDepartmentId(department.getId()); // 设置正确的部门ID
        }
        //      3.写入
        for (Employee employee : employeeList) {
            employeeService.add(employee);
        }
        return  Result.success();
    }
}
