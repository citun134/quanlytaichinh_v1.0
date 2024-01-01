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
    
    public void deleteGiaoDichChi(int user){
        giaoDichDao.deleteGiaoDichChi(user);
    }
    
    public void deleteAllGiaoDichChi(){
        giaoDichDao.deleteAllGiaoDichChi();
    }
    public void updateGiaoDichChi(GiaoDichModel giaoDichModel) {
        giaoDichDao.updateGiaoDichChi(giaoDichModel);
    }
    
    public void addGiaoDichChi(GiaoDichModel giaoDichModel){
        giaoDichDao.addGiaoDichChi(giaoDichModel);
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
    
    public List<GiaoDichModel> getListByMoneyYear(int accountId, int year){
        return giaoDichDao.getListByMoneyYear(accountId, year);
    }
    
    public GiaoDichModel getInforUser(int accountId){
        return giaoDichDao.getInforUser(accountId);
    }
}
