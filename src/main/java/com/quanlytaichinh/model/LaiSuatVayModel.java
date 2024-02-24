/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlytaichinh.model;

/**
 *
 * @author x1 gen6
 */
public class LaiSuatVayModel {
    private int laiSuatVayId;
    private String tenNganHangLSV;
    private double giaTriBatDongSan;
    private double soTienVay;
    private double thoiGianVay;
    private double laiSuat;
    private String ngayGiaiNgan;
    private double soTienPhaiTraHangThang;
    private double tongLaiPhaiTra;
    private int accountId;
    
    private int year;
    private int month;
    
    public LaiSuatVayModel(){}

    public LaiSuatVayModel(int laiSuatVayId, String tenNganHangLSV, double giaTriBatDongSan, double soTienVay, double thoiGianVay, double laiSuat, String ngayGiaiNgan, double soTienPhaiTraHangThang, double tongLaiPhaiTra, int accountId) {
        this.laiSuatVayId = laiSuatVayId;
        this.tenNganHangLSV = tenNganHangLSV;
        this.giaTriBatDongSan = giaTriBatDongSan;
        this.soTienVay = soTienVay;
        this.thoiGianVay = thoiGianVay;
        this.laiSuat = laiSuat;
        this.ngayGiaiNgan = ngayGiaiNgan;
        this.soTienPhaiTraHangThang = soTienPhaiTraHangThang;
        this.tongLaiPhaiTra = tongLaiPhaiTra;
        this.accountId = accountId;
    }

    public int getLaiSuatVayId() {
        return laiSuatVayId;
    }

    public void setLaiSuatVayId(int laiSuatVayId) {
        this.laiSuatVayId = laiSuatVayId;
    }

    public String getTenNganHangLSV() {
        return tenNganHangLSV;
    }

    public void setTenNganHangLSV(String tenNganHangLSV) {
        this.tenNganHangLSV = tenNganHangLSV;
    }

    public double getGiaTriBatDongSan() {
        return giaTriBatDongSan;
    }

    public void setGiaTriBatDongSan(double giaTriBatDongSan) {
        this.giaTriBatDongSan = giaTriBatDongSan;
    }

    public double getSoTienVay() {
        return soTienVay;
    }

    public void setSoTienVay(double soTienVay) {
        this.soTienVay = soTienVay;
    }

    public double getThoiGianVay() {
        return thoiGianVay;
    }

    public void setThoiGianVay(double thoiGianVay) {
        this.thoiGianVay = thoiGianVay;
    }

    public double getLaiSuat() {
        return laiSuat;
    }

    public void setLaiSuat(double laiSuat) {
        this.laiSuat = laiSuat;
    }

    public String getNgayGiaiNgan() {
        return ngayGiaiNgan;
    }

    public void setNgayGiaiNgan(String ngayGiaiNgan) {
        this.ngayGiaiNgan = ngayGiaiNgan;
    }

    public double getSoTienPhaiTraHangThang() {
        return soTienPhaiTraHangThang;
    }

    public void setSoTienPhaiTraHangThang(double soTienPhaiTraHangThang) {
        this.soTienPhaiTraHangThang = soTienPhaiTraHangThang;
    }

    public double getTongLaiPhaiTra() {
        return tongLaiPhaiTra;
    }

    public void setTongLaiPhaiTra(double tongLaiPhaiTra) {
        this.tongLaiPhaiTra = tongLaiPhaiTra;
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
    
    

}
