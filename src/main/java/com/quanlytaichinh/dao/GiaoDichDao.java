package com.quanlytaichinh.dao;

import com.quanlytaichinh.model.GiaoDichModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

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

    public ArrayList<GiaoDichModel> searchTienGiaoDich(String tu, String den){
        ArrayList<GiaoDichModel> infor = new ArrayList<GiaoDichModel>();
        Connection connection = JDBCConnection.getJDBCConecction();
        String sql = "SELECT * FROM giaodichthu WHERE thanhTienThu BETWEEN ? AND ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, tu);
            preparedStatement.setString(2, den);
            ResultSet rs = preparedStatement.executeQuery();
            GiaoDichModel giaoDichModel;
            while (rs.next()){
                giaoDichModel = new GiaoDichModel(rs.getInt("thuId"), rs.getString("ngayThu")
                , rs.getString("matHangThu"), rs.getString("thanhTienThu"), rs.getString("ghiChuThu"));
                infor.add(giaoDichModel);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }   
        return infor;
    }
    
    public ArrayList<GiaoDichModel> searchTenGiaoDich(String ten){
        ArrayList<GiaoDichModel> infor = new ArrayList<GiaoDichModel>();
        Connection connection = JDBCConnection.getJDBCConecction();
        String sql = "SELECT * FROM giaodichthu WHERE matHangThu LIKE '"+ten +"'";
//        String sql = "SELECT * FROM giaodichthu WHERE matHangThu = ?";

        
        //CONCAT(`thuId`, `ngayThu`, `matHangThu`, `thanhTienThu`, `ghiChuThu`)
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, ten);
            ResultSet rs = preparedStatement.executeQuery();
            GiaoDichModel giaoDichModel;
            while (rs.next()){
//                giaoDichModel = new GiaoDichModel(rs.getInt("thuId"));
                giaoDichModel = new GiaoDichModel(rs.getInt("thuId"), rs.getString("ngayThu")
                , rs.getString("matHangThu"), rs.getString("thanhTienThu"), rs.getString("ghiChuThu"));
//                giaoDichModel.setId(rs.getInt("thuId"));
//                giaoDichModel.setDate(rs.getString("ngayThu"));
//                giaoDichModel.setMatHang(rs.getString("matHangThu"));
//                giaoDichModel.setThanhTien(rs.getString("thanhTienThu"));
//                giaoDichModel.setGhiChu(rs.getString("ghiChuThu"));
                infor.add(giaoDichModel);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }   
        return infor;
    }
    
//    public List<GiaoDichModel> searchTenGiaoDich(String ten){
//        List<GiaoDichModel> infor = new ArrayList<GiaoDichModel>();
//        Connection connection = JDBCConnection.getJDBCConecction();
//        String sql = "SELECT * FROM giaodichthu WHERE matHangThu = ?";
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, ten);
//            ResultSet rs = preparedStatement.executeQuery();
//            while (rs.next()){
//                int idThu = rs.getInt("thuId");
//                String ngayThu= rs.getString("ngayThu");
//                String matHangThu =rs.getString("matHangThu");
//                String thanhTienThu =rs.getString("thanhTienThu");
//                String ghiChuThu =rs.getString("ghiChuThu");
//                
//                GiaoDichModel giaoDichModel = new GiaoDichModel(idThu, ngayThu, matHangThu, thanhTienThu, ghiChuThu);
////                giaoDichModel.setId(idThu);
////                giaoDichModel.setDate(ngayThu);
////                giaoDichModel.setMatHang(matHangThu);
////                giaoDichModel.setThanhTien(thanhTienThu);
////                giaoDichModel.setGhiChu(ghiChuThu);
//                infor.add(giaoDichModel);
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }   
//        return infor;
//    }
}
