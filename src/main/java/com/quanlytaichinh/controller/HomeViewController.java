package com.quanlytaichinh.controller;

import com.quanlytaichinh.model.GiaoDichModel;
import com.quanlytaichinh.dao.GiaoDichDao;
import com.quanlytaichinh.model.GiaoDichThuModel;
import com.quanlytaichinh.model.LaiSuatVayModel;
import com.quanlytaichinh.model.LoginModel;
import com.quanlytaichinh.model.SoTietKiemModel;
import java.util.List;
import java.util.ArrayList;

public class HomeViewController {  
    private GiaoDichDao giaoDichDao;
    private LoginModel loginModel;
    private SoTietKiemModel soTietKiemModel;
    private LaiSuatVayModel laiSuatVayModel;
//    private GiaoDichModel giaoDichModel;

    public HomeViewController() {
        giaoDichDao = new GiaoDichDao();
    }
    
    public void deleteGiaoDichChi(int user){
        giaoDichDao.deleteGiaoDichChi(user);
    }
    
    public void deleteGiaoDichThu(int user){
        giaoDichDao.deleteGiaoDichThu(user);
    }
    
    public void deleteSoTietKiem(int user){
        giaoDichDao.deleteSoTietKiem(user);
    }
    
    public void deleteLaiSuatVay(int user){
        giaoDichDao.deleteLaiSuatVay(user);
    }
    
    public void deleteAllGiaoDichChi(int user){
        giaoDichDao.deleteAllGiaoDichChi(user);
    }
    
    public void deleteAllGiaoDichThu(int user){
        giaoDichDao.deleteAllGiaoDichThu(user);
    }
    
    public void deleteAllSoTietKiem(int user){
        giaoDichDao.deleteAllSoTietKiem(user);
    }
    
    public void deleteAllLaiSuatVay(int user){
        giaoDichDao.deleteAllLaiSuatVay(user);
    }
    
    public void updateGiaoDichChi(GiaoDichModel giaoDichModel) {
        giaoDichDao.updateGiaoDichChi(giaoDichModel);
    }
    
    public void updateGiaoDichThu(GiaoDichThuModel giaoDichModel) {
        giaoDichDao.updateGiaoDichThu(giaoDichModel);
    }
    
    public void updateSoTietKiem(SoTietKiemModel giaoDichModel) {
        giaoDichDao.updateSoTietKiem(giaoDichModel);
    }
    
    public void updateLaiSuatVay(LaiSuatVayModel giaoDichModel) {
        giaoDichDao.updateLaiSuatVay(giaoDichModel);
    }
    
    public void addGiaoDichChi(GiaoDichModel giaoDichModel){
        giaoDichDao.addGiaoDichChi(giaoDichModel);
    }
    
    public void addGiaoDichThu(GiaoDichThuModel giaoDichModel){
        giaoDichDao.addGiaoDichThu(giaoDichModel);
    }
    
    public void addGiaoDichSTK(SoTietKiemModel giaoDichModel){
        giaoDichDao.addGiaoDichSTK(giaoDichModel);
    }
    
    public void addGiaoDichLSV(LaiSuatVayModel giaoDichModel){
        giaoDichDao.addGiaoDichLSV(giaoDichModel);
    }
    
    public ArrayList<GiaoDichModel> searchTienGiaoDich(String tu, String den, int accountId){
        return giaoDichDao.searchTienGiaoDich(tu, den, accountId);
    }  
    
    public ArrayList<GiaoDichModel> searchTienGiaoDichThuChi(String tu, String den, int accountId){
        return giaoDichDao.searchTienGiaoDichThuChi(tu, den, accountId);
    }
    
    public ArrayList<GiaoDichModel> searchTenGiaoDich(String ten, int accountId){
        return giaoDichDao.searchTenGiaoDich(ten, accountId);
    }  
    
    public ArrayList<GiaoDichModel> searchThoiGianGiaoDich(String tu, String den, int accountId){
        return giaoDichDao.searchThoiGianGiaoDich(tu, den, accountId);
    }   
    
    public ArrayList<GiaoDichModel> searchThoiGianGiaoDichThuChi(String tu, String den, int accountId){
        return giaoDichDao.searchThoiGianGiaoDichThuChi(tu, den, accountId);
    }  
    
    public List<GiaoDichModel> getAllInforUser(int accountId){
        return giaoDichDao.getAllInforUser(accountId);
    }
    
    public List<GiaoDichThuModel> getAllInforUserThu(int accountId){
        return giaoDichDao.getAllInforUserThu(accountId);
    }
    
    public List<GiaoDichModel> getAllInforUserThuChi(int accountId){
        return giaoDichDao.getAllInforUserThuChi(accountId);
    }
    
    public List<SoTietKiemModel> getAllInforUserSTK(int accountId){
        return giaoDichDao.getAllInforUserSTK(accountId);
    }
    
    public List<LaiSuatVayModel> getAllInforUserLSV(int accountId){
        return giaoDichDao.getAllInforUserLSV(accountId);
    }
    
    public List<GiaoDichModel> getListByMoney(int accountId){
        return giaoDichDao.getListByMoney(accountId);
    }
    
    public List<GiaoDichModel> thongKeGiaoDichChi(int accountId, int year){
        return giaoDichDao.thongKeGiaoDichChi(accountId, year);
    }
    
    public List<GiaoDichModel> thongKeGiaoDichChiSua(int accountId, String tu, String den){
        return giaoDichDao.thongKeGiaoDichChiSua(accountId, tu, den);
    }
    
    public List<GiaoDichModel> thongKeGiaoDichThuSua(int accountId, String tu, String den){
        return giaoDichDao.thongKeGiaoDichThuSua(accountId, tu, den);
    }
    
    public List<GiaoDichModel> thongKeGiaoDichThuChiSua(int accountId, String tu, String den){
        return giaoDichDao.thongKeGiaoDichThuChiSua(accountId, tu, den);
    }
    
    public List<GiaoDichModel> thongKeGiaoDichThu(int accountId, int year){
        return giaoDichDao.thongKeGiaoDichThu(accountId, year);
    }
    
    public List<GiaoDichModel> thongKeGiaoDichThuChi(int accountId, int year){
        return giaoDichDao.thongKeGiaoDichThuChi(accountId, year);
    }
    
    public GiaoDichModel getInforUser(int accountId){
        return giaoDichDao.getInforUser(accountId);
    }
    
    public GiaoDichThuModel getInforUserThu(int accountId){
        return giaoDichDao.getInforUserThu(accountId);
    }
    
    public SoTietKiemModel getInforUserSTK(int accountId){
        return giaoDichDao.getInforUserSTK(accountId);
    }
    
    public LaiSuatVayModel getInforUserLSV(int accountId){
        return giaoDichDao.getInforUserLSV(accountId);
    }
    
    public List<SoTietKiemModel> layDanhSachSoTietKiem(int accountId, String tu, String den){
        return giaoDichDao.layDanhSachSoTietKiem(accountId, tu, den);
    }
    
    public List<SoTietKiemModel> layDanhSachSoTietKiemToanBo(int accountId){
        return giaoDichDao.layDanhSachSoTietKiemToanBo(accountId);
    }
    
    public List<LaiSuatVayModel> layDanhSachLaiSuatVayToanBo(int accountId){
        return giaoDichDao.layDanhSachLaiSuatVayToanBo(accountId);
    }
}
