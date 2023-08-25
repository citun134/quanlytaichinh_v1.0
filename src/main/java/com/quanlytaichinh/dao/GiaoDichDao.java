package com.quanlytaichinh.dao;

import com.quanlytaichinh.model.GiaoDichModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GiaoDichDao {
//    private GiaoDichModel giaoDichModel;
    
    public List<GiaoDichModel> getAllInfor(){
        List<GiaoDichModel> infor = new ArrayList<GiaoDichModel>();
        Connection connection = JDBCConnection.getJDBCConecction();
        String sql = "SELECT * FROM giaodichthu";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                GiaoDichModel giaoDichModel = new GiaoDichModel();
                giaoDichModel.setId(rs.getInt("thuId"));
                giaoDichModel.setDate(rs.getString("ngayThu"));
                giaoDichModel.setMatHang(rs.getString("matHangThu"));
                giaoDichModel.setThanhTien(rs.getString("thanhTienThu"));
                giaoDichModel.setGhiChu(rs.getString("ghiChuThu"));
                infor.add(giaoDichModel);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }   
        return infor;
    }
    
    public void addGiaoDich(GiaoDichModel giaoDichModel){
        Connection connection = JDBCConnection.getJDBCConecction();
        String sql = "INSERT INTO giaodich (ngay, matHang, thanhTien, ghiChu) VALUES (?, ?, ?, ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1, giaoDichModel.getId());
            preparedStatement.setString(1, giaoDichModel.getDate());
            preparedStatement.setString(2, giaoDichModel.getMatHang());
            preparedStatement.setString(3, giaoDichModel.getThanhTien());
            preparedStatement.setString(4, giaoDichModel.getGhiChu());
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void addGiaoDichThu(GiaoDichModel giaoDichModel){
        Connection connection = JDBCConnection.getJDBCConecction();
        String sql = "INSERT INTO giaodichThu (ngayThu, matHangThu, thanhTienThu, ghiChuThu) VALUES (?, ?, ?, ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1, giaoDichModel.getId());
            preparedStatement.setString(1, giaoDichModel.getDate());
            preparedStatement.setString(2, giaoDichModel.getMatHang());
            preparedStatement.setString(3, giaoDichModel.getThanhTien());
            preparedStatement.setString(4, giaoDichModel.getGhiChu());
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
  
//    
//    public void addGiaoDich(GiaoDichModel user){
//        Connection connection = JDBCConnection.getJDBCConecction();
//        String sql = "INSERT INTO giaodich (mat_hang, thanh_tien, ghi_chu) VALUES (?, ?, ?);";
//        PreparedStatement preparedStatement;
//        try {
//            preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, user.getMatHang());
//            preparedStatement.setInt(2, user.getThanhTien());
//            preparedStatement.setString(3, user.getGhiChu());
//            preparedStatement.executeUpdate();
//            
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    }
//    
    public void deleteGiaoDich(int id){
        Connection connection = JDBCConnection.getJDBCConecction();
        String sql = "DELETE FROM giaodichthu WHERE thuId = ? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }  
}
