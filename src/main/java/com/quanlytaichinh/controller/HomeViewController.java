package com.quanlytaichinh.controller;

import com.quanlytaichinh.model.GiaoDichModel;
import com.quanlytaichinh.dao.GiaoDichDao;
import com.quanlytaichinh.model.LoginModel;
import java.util.List;
import java.util.ArrayList;

public class HomeViewController {  
    private GiaoDichDao giaoDichDao;
    private LoginModel loginModel;
//    private GiaoDichModel giaoDichModel;

    public HomeViewController() {
        giaoDichDao = new GiaoDichDao();
    }
    
    public void deleteGiaoDich(int user){
        giaoDichDao.deleteGiaoDich(user);
    }
    
    public void addGiaoDichThu(GiaoDichModel giaoDichModel){
        giaoDichDao.addGiaoDichThu(giaoDichModel);
    }
    
    public ArrayList<GiaoDichModel> searchTienGiaoDich(String tu, String den, int accountId){
        return giaoDichDao.searchTienGiaoDich(tu, den, accountId);
    }  
    
    public ArrayList<GiaoDichModel> searchTenGiaoDich(String ten, int accountId){
        return giaoDichDao.searchTenGiaoDich(ten, accountId);
    }  
    
    public ArrayList<GiaoDichModel> searchThoiGianGiaoDich(String tu, String den, int accountId){
        return giaoDichDao.searchThoiGianGiaoDich(tu, den, accountId);
    }   
    
    public List<GiaoDichModel> getAllInforUser(int accountId){
        return giaoDichDao.getAllInforUser(accountId);
    }
    public List<GiaoDichModel> getListByMoney(int accountId){
        return giaoDichDao.getListByMoney(accountId);
    }
}
