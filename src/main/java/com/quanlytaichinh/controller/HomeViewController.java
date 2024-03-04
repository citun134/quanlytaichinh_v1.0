package com.quanlytaichinh.controller;

import com.quanlytaichinh.model.GiaoDichModel;
import com.quanlytaichinh.dao.GiaoDichDao;
import com.quanlytaichinh.dao.LoginDao;
import com.quanlytaichinh.model.GiaoDichThuModel;
import com.quanlytaichinh.model.LaiSuatVayModel;
import com.quanlytaichinh.model.LoginModel;
import com.quanlytaichinh.model.SoTietKiemModel;
import com.quanlytaichinh.model.SoTienDaTraModel;
import java.util.List;
import java.util.ArrayList;

public class HomeViewController {  
    private GiaoDichDao giaoDichDao;
    private LoginModel loginModel;
    private SoTietKiemModel soTietKiemModel;
    private LaiSuatVayModel laiSuatVayModel;
    private LoginDao loginDao;
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
    
    public void addGiaoDichSTDT(SoTienDaTraModel soTienDaTraModel){
        giaoDichDao.addGiaoDichSTDT(soTienDaTraModel);
    }
    
    public List<SoTienDaTraModel> getAllInforUserSTDT(int accountId){
        return giaoDichDao.getAllInforUserSTDT(accountId);
    }
    
    public List<SoTienDaTraModel> layDanhSTDTToanBo(int accountId){
        return giaoDichDao.layDanhSTDTToanBo(accountId);
    }
    
    public void deleteSTDT(int user){
        giaoDichDao.deleteSTDT(user);
    }
    
    public void updateSTDT(SoTienDaTraModel giaoDichModel) {
        giaoDichDao.updateSTDT(giaoDichModel);
    }

    public double getTongChi(int accountId){
        return giaoDichDao.getTongChi(accountId);
    }
    
    public double getTongChiThangHienTai(int accountId){
        return giaoDichDao.getTongChiThangHienTai(accountId);
    }
    
    public double getTongThu(int accountId){
        return giaoDichDao.getTongThu(accountId);
    }
    
    public double getTongThuThangHienTai(int accountId){
        return giaoDichDao.getTongThuThangHienTai(accountId);
    }
    
    public List<GiaoDichModel> getHangMucChi(int accountId, String hangMuc){
        return giaoDichDao.getHangMucChi(accountId, hangMuc);
    }
    
    public List<GiaoDichThuModel> getHangMucThu(int accountId, String hangMuc){
        return giaoDichDao.getHangMucThu(accountId, hangMuc);
    }
    
    public double getTongMatHangChi(int accountId, String matHang){
        return giaoDichDao.getTongMatHangChi(accountId, matHang);
    }
    
    public double getTongHangMucChi(int accountId, String matHang){
        return giaoDichDao.getTongHangMucChi(accountId, matHang);
    }
    
    public double getTongHangMucThu(int accountId, String matHang){
        return giaoDichDao.getTongHangMucThu(accountId, matHang);
    }
    
    public double getTongNgayChi(int accountId, String tu, String den){
        return giaoDichDao.getTongNgayChi(accountId, tu, den);
    }
    
    public double getTongNgayThu(int accountId, String tu, String den){
        return giaoDichDao.getTongNgayThu(accountId, tu, den);
    }
    
    public double getTongTienLai(int accountId){
        return giaoDichDao.getTongTienLai(accountId);
    }
    
    public double getTongSoTienGui(int accountId){
        return giaoDichDao.getTongSoTienGui(accountId);
    }
    
    public double getTongSoTienVay(int accountId){
        return giaoDichDao.getTongSoTienVay(accountId);
    }
    
    public double getTongSoTienTra(int accountId){
        return giaoDichDao.getTongSoTienTra(accountId);
    }
    
    
    public void updatePassword(String newPass, String oldPass) {
        giaoDichDao.updatePassword(newPass, oldPass);
    }
    
    
}
