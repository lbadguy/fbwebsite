package com.example.springboot.entity;

import java.util.Date;

/**
 * 读者实体类
 */
public class Reader {
    private Long readerId;    // 读者ID
    private Long userId;      // 用户ID
    private String name;      // 姓名
    private String gender;    // 性别
    private String cardId;    // 证件号
    private String type;      // 读者类型：学生、教师、其他
    private Integer maxBorrowNum; // 最大借阅数量
    private Integer maxBorrowDays; // 最大借阅天数
    private Integer status;   // 状态：1-正常，0-禁用
    private Date createTime;  // 创建时间
    private Date updateTime;  // 更新时间

    // 额外字段，不在数据库中
    private String username;  // 用户名
    private String email;     // 邮箱
    private String phone;     // 电话

    public Long getReaderId() {
        return readerId;
    }

    public void setReaderId(Long readerId) {
        this.readerId = readerId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getMaxBorrowNum() {
        return maxBorrowNum;
    }

    public void setMaxBorrowNum(Integer maxBorrowNum) {
        this.maxBorrowNum = maxBorrowNum;
    }

    public Integer getMaxBorrowDays() {
        return maxBorrowDays;
    }

    public void setMaxBorrowDays(Integer maxBorrowDays) {
        this.maxBorrowDays = maxBorrowDays;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
} 