package com.quanlytaichinh.controller;

import com.quanlytaichinh.model.GiaoDichModel;
import com.quanlytaichinh.dao.GiaoDichDao;
import java.util.List;
import java.util.ArrayList;



public class HomeViewController {  
    private GiaoDichDao giaoDichDao;
//    private GiaoDichModel giaoDichModel;

    public HomeViewController() {
        giaoDichDao = new GiaoDichDao();
    }
    
    public List<GiaoDichModel> getAllInfor(){
        return giaoDichDao.getAllInfor();
    }
    
    public void deleteGiaoDich(int user){
        giaoDichDao.deleteGiaoDich(user);
    }
    
    public void addGiaoDichThu(GiaoDichModel giaoDichModel){
        giaoDichDao.addGiaoDichThu(giaoDichModel);
    }
    
    public ArrayList<GiaoDichModel> searchTienGiaoDich(String tu, String den){
        return giaoDichDao.searchTienGiaoDich(tu, den);
    }  
    
    public ArrayList<GiaoDichModel> searchTenGiaoDich(String ten){
        return giaoDichDao.searchTenGiaoDich(ten);
    }  
    
    public ArrayList<GiaoDichModel> searchThoiGianGiaoDich(String tu, String den){
        return giaoDichDao.searchThoiGianGiaoDich(tu, den);
    }   
    
    public List<GiaoDichModel> getAllInforUser(){
        return giaoDichDao.getAllInforUser();
    }
}
