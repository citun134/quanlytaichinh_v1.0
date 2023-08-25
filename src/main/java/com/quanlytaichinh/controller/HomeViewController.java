package com.quanlytaichinh.controller;

import com.quanlytaichinh.model.GiaoDichModel;
import com.quanlytaichinh.dao.GiaoDichDao;
import java.util.List;


public class HomeViewController {  
    private GiaoDichDao giaoDichDao;
//    private GiaoDichModel giaoDichModel;

    public HomeViewController() {
        giaoDichDao = new GiaoDichDao();
    }
    
    public List<GiaoDichModel> getAllInfor(){
        return giaoDichDao.getAllInfor();
    }
    
    public void addGiaoDich(GiaoDichModel giaoDichModel){
        giaoDichDao.addGiaoDich(giaoDichModel);
    }
    
    public void deletGiaoDich(GiaoDichModel giaoDichModel){
        giaoDichDao.deleteGiaoDich(giaoDichModel);
    }
    
    public void addGiaoDichThu(GiaoDichModel giaoDichModel){
        giaoDichDao.addGiaoDichThu(giaoDichModel);
    }
}
