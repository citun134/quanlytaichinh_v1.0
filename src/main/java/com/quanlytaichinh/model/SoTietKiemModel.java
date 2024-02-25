/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlytaichinh.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

/**
 *
 * @author x1 gen6
 */
public class SoTietKiemModel {
    private int tietKiemId;
    private String ngayGui;
    private String tenNganHang;
    private double soTienGui;
    private double laiSuatGui;
    private double soTienLaiNhanDuoc;
    private double tongTienNhanDuoc;
    private double kyHan;
    private int accountId;
    
    private int day;
    private int year;
    private int month;
    
    public SoTietKiemModel(){}
    
    
    public SoTietKiemModel(int tietKiemId, String ngayGui,String tenNganHang, 
            double soTienGui, double laiSuatGui,double soTienLaiNhanDuoc, 
            double tongTienNhanDuoc, double kyHan, int accountId){
        this.tietKiemId = tietKiemId;
        this.tenNganHang = tenNganHang;
        this.soTienGui = soTienGui;
        this.laiSuatGui = laiSuatGui;
        this.soTienLaiNhanDuoc = soTienLaiNhanDuoc;
        this.tongTienNhanDuoc = tongTienNhanDuoc;
        this.ngayGui = ngayGui;
        this.kyHan = kyHan;
        this.accountId = accountId;
    }

    public int getTietKiemId() {
        return tietKiemId;
    }

    public void setTietKiemId(int tietKiemId) {
        this.tietKiemId = tietKiemId;
    }

    public String getNgayGui() {
        return ngayGui;
    }

    public void setNgayGui(String ngayGui) {
        this.ngayGui = ngayGui;
    }

    public String getTenNganHang() {
        return tenNganHang;
    }

    public void setTenNganHang(String tenNganHang) {
        this.tenNganHang = tenNganHang;
    }

    public double getSoTienGui() {
        return soTienGui;
    }

    public void setSoTienGui(double soTienGui) {
        this.soTienGui = soTienGui;
    }

    public double getLaiSuatGui() {
        return laiSuatGui;
    }

    public void setLaiSuatGui(double laiSuatGui) {
        this.laiSuatGui = laiSuatGui;
    }

    public double getSoTienLaiNhanDuoc() {
        return soTienLaiNhanDuoc;
    }

    public void setSoTienLaiNhanDuoc(double soTienLaiNhanDuoc) {
        this.soTienLaiNhanDuoc = soTienLaiNhanDuoc;
    }

    public double getTongTienNhanDuoc() {
        return tongTienNhanDuoc;
    }

    public void setTongTienNhanDuoc(double tongTienNhanDuoc) {
        this.tongTienNhanDuoc = tongTienNhanDuoc;
    }

    public double getKyHan() {
        return kyHan;
    }

    public void setKyHan(double kyHan) {
        this.kyHan = kyHan;
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

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    
    
    


   
    
}
