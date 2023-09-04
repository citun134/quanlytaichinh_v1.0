package com.quanlytaichinh.dao;

import com.quanlytaichinh.model.GiaoDichModel;
import com.quanlytaichinh.model.LoginModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class GiaoDichDao {
//    private GiaoDichModel giaoDichModel;
    public GiaoDichModel giaoDichModel;
    public LoginModel loginModel;
    
    public void addGiaoDichThu(GiaoDichModel giaoDichModel){
        Connection connection = JDBCConnection.getJDBCConecction();
        String sql = "INSERT INTO giaodichThu (ngayThu, matHangThu, thanhTienThu, ghiChuThu, account_id) VALUES (?, ?, ?, ?, ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1, giaoDichModel.getId());
            preparedStatement.setString(1, giaoDichModel.getDate());
            preparedStatement.setString(2, giaoDichModel.getMatHang());
            preparedStatement.setDouble(3, giaoDichModel.getThanhTien());
            preparedStatement.setString(4, giaoDichModel.getGhiChu());
            preparedStatement.setInt(5, giaoDichModel.getAccountId());
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

    public ArrayList<GiaoDichModel> searchTienGiaoDich(String tu, String den, int accountId){
        ArrayList<GiaoDichModel> infor = new ArrayList<GiaoDichModel>();
        Connection connection = JDBCConnection.getJDBCConecction();
//        String sql = "SELECT * FROM giaodichthu WHERE thanhTienThu BETWEEN ? AND ?";
        String sql = "SELECT * FROM giaodichthu WHERE thanhTienThu BETWEEN ? AND ? AND account_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, tu);
            preparedStatement.setString(2, den);
            preparedStatement.setInt(3, accountId);
            ResultSet rs = preparedStatement.executeQuery();
            GiaoDichModel giaoDichModel;
            while (rs.next()){
                giaoDichModel = new GiaoDichModel(rs.getInt("thuId"), rs.getString("ngayThu")
                , rs.getString("matHangThu"), rs.getDouble("thanhTienThu"), rs.getString("ghiChuThu"));
                infor.add(giaoDichModel);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }   
        return infor;
    }
    
    public ArrayList<GiaoDichModel> searchTenGiaoDich(String ten, int accountId){
        ArrayList<GiaoDichModel> infor = new ArrayList<GiaoDichModel>();
        Connection connection = JDBCConnection.getJDBCConecction();
//        String sql = "SELECT * FROM giaodichthu WHERE matHangThu LIKE '"+ten +"'";
        String sql = "SELECT * FROM giaodichthu WHERE matHangThu LIKE ? AND account_id = ?";

//        String sql = "SELECT * FROM giaodichthu WHERE matHangThu = ?";

        
        //CONCAT(`thuId`, `ngayThu`, `matHangThu`, `thanhTienThu`, `ghiChuThu`)
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ten);
            preparedStatement.setInt(2, accountId);
            ResultSet rs = preparedStatement.executeQuery();
            GiaoDichModel giaoDichModel;
            while (rs.next()){
//                giaoDichModel = new GiaoDichModel(rs.getInt("thuId"));
                giaoDichModel = new GiaoDichModel(rs.getInt("thuId"), rs.getString("ngayThu")
                , rs.getString("matHangThu"), rs.getDouble("thanhTienThu"), rs.getString("ghiChuThu"));
                infor.add(giaoDichModel);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }   
        return infor;
    }
    
    public ArrayList<GiaoDichModel> searchThoiGianGiaoDich(String tu, String den, int accountId){
        ArrayList<GiaoDichModel> infor = new ArrayList<GiaoDichModel>();
        Connection connection = JDBCConnection.getJDBCConecction();
//        String sql = "SELECT * FROM giaodichthu WHERE ngayThu BETWEEN ? AND ?";
        String sql = "SELECT * FROM giaodichthu WHERE ngayThu BETWEEN ? AND ? AND account_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, tu);
            preparedStatement.setString(2, den);
            preparedStatement.setInt(3, accountId);
            ResultSet rs = preparedStatement.executeQuery();
            GiaoDichModel giaoDichModel;
            while (rs.next()){
                giaoDichModel = new GiaoDichModel(rs.getInt("thuId"), rs.getString("ngayThu")
                , rs.getString("matHangThu"), rs.getDouble("thanhTienThu"), rs.getString("ghiChuThu"));
                infor.add(giaoDichModel);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }   
        return infor;
    }
    
    public List<GiaoDichModel> getAllInforUser(int accountId){
        loginModel = new LoginModel();
        List<GiaoDichModel> infor = new ArrayList<GiaoDichModel>();
        Connection connection = JDBCConnection.getJDBCConecction();
//        String sql = "SELECT * FROM giaodichthu WHERE account_id = " + String.valueOf(loginModel.getAccount_id());
        String sql = "SELECT * FROM giaodichthu WHERE account_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                GiaoDichModel giaoDichModel = new GiaoDichModel();
                giaoDichModel.setId(rs.getInt("thuId"));
                giaoDichModel.setDate(rs.getString("ngayThu"));
                giaoDichModel.setMatHang(rs.getString("matHangThu"));
                giaoDichModel.setThanhTien(rs.getDouble("thanhTienThu"));
                giaoDichModel.setGhiChu(rs.getString("ghiChuThu"));
                giaoDichModel.setAccountId(rs.getInt("account_id"));                
                infor.add(giaoDichModel);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }   
        return infor;
    }
    
    public List<GiaoDichModel> getListByMoney(int accountId) {
        List<GiaoDichModel> infor = new ArrayList<GiaoDichModel>();
        Connection connection = JDBCConnection.getJDBCConecction();
        String sql = "SELECT YEAR(ngayThu) AS Year, MONTH(ngayThu) AS Month, " +
                     "COALESCE(SUM(thanhTienThu), 0) AS TotalMoney " +
                     "FROM giaoDichThu " +
                     "WHERE account_id = ? " + // Thêm điều kiện cho accountId
                     "GROUP BY YEAR(ngayThu), MONTH(ngayThu) " +
                     "ORDER BY YEAR(ngayThu), MONTH(ngayThu)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountId); // Đặt giá trị cho accountId
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                GiaoDichModel giaoDichModel = new GiaoDichModel();
                giaoDichModel.setYear(rs.getInt("Year"));
                giaoDichModel.setMonth(rs.getInt("Month"));
                giaoDichModel.setTotalMoney(rs.getInt("TotalMoney"));
                infor.add(giaoDichModel);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return infor;
    }


}
