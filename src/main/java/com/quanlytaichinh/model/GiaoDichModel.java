package com.quanlytaichinh.model;

public class GiaoDichModel {
    private int id;
    private String date;
    private String matHang;
    private String thanhTien;
    private String ghiChu;
    private int accountId;
    private int year;
    private int month;
    private int totalMoney;

    public GiaoDichModel() {
//        new GiaoDichModel();
    }
    
    public GiaoDichModel(int id, String date, String matHang, String thanhTien, String ghiChu) {
        this.id = id;
        this.date = date;
        this.matHang = matHang;
        this.thanhTien = thanhTien;
        this.ghiChu = ghiChu;
    }
    
    public GiaoDichModel(int id, String date, String matHang, String thanhTien, String ghiChu, int accountId) {
        this.id = id;
        this.date = date;
        this.matHang = matHang;
        this.thanhTien = thanhTien;
        this.ghiChu = ghiChu;
        this.accountId = accountId;
    }    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMatHang() {
        return matHang;
    }

    public void setMatHang(String matHang) {
        this.matHang = matHang;
    }

    public String getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(String thanhTien) {
        this.thanhTien = thanhTien;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }
    

}
