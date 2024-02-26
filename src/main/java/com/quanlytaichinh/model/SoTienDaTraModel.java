/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlytaichinh.model;

/**
 *
 * @author x1 gen6
 */
public class SoTienDaTraModel {
    private int account_id;
    private int soTienDaTraId;
    private String ngayTra;
    private double soTien;
    private int laiSuatVayId;

    private int year;
    private int month;
    
    public int getSoTienDaTraId() {
        return soTienDaTraId;
    }

    public void setSoTienDaTraId(int soTienDaTraId) {
        this.soTienDaTraId = soTienDaTraId;
    }

    public String getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(String ngayTra) {
        this.ngayTra = ngayTra;
    }

    public double getSoTien() {
        return soTien;
    }

    public void setSoTien(double soTien) {
        this.soTien = soTien;
    }

    public int getLaiSuatVayId() {
        return laiSuatVayId;
    }

    public void setLaiSuatVayId(int laiSuatVayId) {
        this.laiSuatVayId = laiSuatVayId;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
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
