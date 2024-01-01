package com.quanlytaichinh.model;

public class GiaoDichModel {
    private int id;
    private String date;
    private String matHang;
    private double thanhTien;
    private String ghiChu;
    private String hangMuc;
    private int accountId;
    
    private int year;
    private int month;
    private int totalMoney;

    public GiaoDichModel() {
//        new GiaoDichModel();
    }
    
    public GiaoDichModel(int id, String date, String matHang, double thanhTien, String ghiChu, String hangMuc) {
        this.id = id;
        this.date = date;
        this.matHang = matHang;
        this.thanhTien = thanhTien;
        this.ghiChu = ghiChu;
        this.hangMuc = hangMuc;
    }
    
    public GiaoDichModel(int id, String date, String matHang, double thanhTien, String ghiChu, String hangMuc, int accountId) {
        this.id = id;
        this.date = date;
        this.matHang = matHang;
        this.thanhTien = thanhTien;
        this.ghiChu = ghiChu;
        this.hangMuc = hangMuc;
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

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getHangMuc() {
        return hangMuc;
    }

    public void setHangMuc(String hangMuc) {
        this.hangMuc = hangMuc;
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
