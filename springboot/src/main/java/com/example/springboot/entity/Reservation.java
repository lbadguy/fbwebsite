package com.example.springboot.entity;

import java.util.Date;

/**
 * 图书预约实体类
 */
public class Reservation {
    private Long reservationId;   // 预约ID
    private Long bookId;          // 图书ID
    private Long readerId;        // 读者ID
    private Date reservationDate; // 预约日期
    private Date expirationDate;  // 过期日期
    private Integer status;       // 状态：1-预约中，2-已取消，3-已借出，4-已过期

    // 额外字段，不在数据库中
    private String bookTitle;     // 图书标题
    private String isbn;          // ISBN
    private String readerName;    // 读者姓名
    private String cardId;        // 证件号

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getReaderId() {
        return readerId;
    }

    public void setReaderId(Long readerId) {
        this.readerId = readerId;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getReaderName() {
        return readerName;
    }

    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
} 