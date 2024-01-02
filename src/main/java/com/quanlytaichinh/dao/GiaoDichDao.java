
package com.quanlytaichinh.dao;

import com.quanlytaichinh.model.GiaoDichModel;
import com.quanlytaichinh.model.GiaoDichThuModel;
import com.quanlytaichinh.model.LoginModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GiaoDichDao {
//    private GiaoDichModel giaoDichModel;
    public GiaoDichModel giaoDichModel;
    public GiaoDichThuModel giaoDichThuModel;
    public LoginModel loginModel;
    
    public void addGiaoDichChi(GiaoDichModel giaoDichModel){
        Connection connection = JDBCConnection.getJDBCConecction();
        String sql = "INSERT INTO giaodichchi (ngayChi, matHangChi, thanhTienChi, ghiChuChi, hangMuc, account_id) VALUES (?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1, giaoDichModel.getId());
            preparedStatement.setString(1, giaoDichModel.getDate());
            preparedStatement.setString(2, giaoDichModel.getMatHang());
            preparedStatement.setDouble(3, giaoDichModel.getThanhTien());
            preparedStatement.setString(4, giaoDichModel.getGhiChu());
            preparedStatement.setString(5, giaoDichModel.getHangMuc());
            preparedStatement.setInt(6, giaoDichModel.getAccountId());
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException ex) { 
            ex.printStackTrace();
        }
    }
    
    public void addGiaoDichThu(GiaoDichThuModel giaoDichModel){
        Connection connection = JDBCConnection.getJDBCConecction();
        String sql = "INSERT INTO giaodichthu (ngayThu, thanhTienThu, ghiChuThu, hangMucThu, account_id) VALUES (?, ?, ?, ?, ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1, giaoDichModel.getId());
            preparedStatement.setString(1, giaoDichModel.getDate());
            preparedStatement.setDouble(2, giaoDichModel.getThanhTien());
            preparedStatement.setString(3, giaoDichModel.getGhiChu());
            preparedStatement.setString(4, giaoDichModel.getHangMuc());
            preparedStatement.setInt(5, giaoDichModel.getAccountId());
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException ex) { 
            ex.printStackTrace();
        }
    }
    
    public void deleteGiaoDichChi(int id){
        Connection connection = JDBCConnection.getJDBCConecction();
        String sql = "DELETE FROM giaodichchi WHERE chiId = ? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }  
    
    public void deleteGiaoDichThu(int id){
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
    
    public void deleteAllGiaoDichChi() {
        Connection connection = JDBCConnection.getJDBCConecction();
        String sql = "DELETE FROM giaodichchi";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void deleteAllGiaoDichThu() {
        Connection connection = JDBCConnection.getJDBCConecction();
        String sql = "DELETE FROM giaodichthu";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void updateGiaoDichChi(GiaoDichModel giaoDichModel) {
        Connection connection = JDBCConnection.getJDBCConecction();
        String sql = "UPDATE giaodichchi SET ngaychi = ?, matHangChi = ?, thanhTienChi = ?, ghiChuChi = ?, hangMuc = ? WHERE chiId = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, giaoDichModel.getDate());
            preparedStatement.setString(2, giaoDichModel.getMatHang());
            preparedStatement.setDouble(3, giaoDichModel.getThanhTien());
            preparedStatement.setString(4, giaoDichModel.getGhiChu());
            preparedStatement.setString(5, giaoDichModel.getHangMuc());            
            preparedStatement.setInt(6,giaoDichModel.getId()); // ID của giao dịch cần cập nhật
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void updateGiaoDichThu(GiaoDichThuModel giaoDichModel) {
        Connection connection = JDBCConnection.getJDBCConecction();
        String sql = "UPDATE giaodichthu SET ngayThu = ?, thanhTienThu = ?, ghiChuThu = ?, hangMucThu = ? WHERE thuId = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, giaoDichModel.getDate());
            preparedStatement.setDouble(2, giaoDichModel.getThanhTien());
            preparedStatement.setString(3, giaoDichModel.getGhiChu());
            preparedStatement.setString(4, giaoDichModel.getHangMuc());            
            preparedStatement.setInt(5,giaoDichModel.getId()); // ID của giao dịch cần cập nhật
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<GiaoDichModel> searchTienGiaoDich(String tu, String den, int accountId){
        ArrayList<GiaoDichModel> infor = new ArrayList<GiaoDichModel>();
        Connection connection = JDBCConnection.getJDBCConecction();
        String sql = "SELECT * FROM giaodichchi WHERE thanhTienChi BETWEEN ? AND ? AND account_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, tu);
            preparedStatement.setString(2, den);
            preparedStatement.setInt(3, accountId);
            ResultSet rs = preparedStatement.executeQuery();
            GiaoDichModel giaoDichModel;
            while (rs.next()){
                giaoDichModel = new GiaoDichModel(rs.getInt("chiId"), rs.getString("ngayChi")
                , rs.getString("matHangChi"), rs.getDouble("thanhTienChi")
                , rs.getString("ghiChuChi"), rs.getString("hangMuc"));
                infor.add(giaoDichModel);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }   
        return infor;
    }
    
    public ArrayList<GiaoDichModel> searchTienGiaoDichThuChi(String tu, String den, int accountId) {
    ArrayList<GiaoDichModel> infor = new ArrayList<>();
    Connection connection = JDBCConnection.getJDBCConecction();
    String sql = "SELECT chiId as id, ngayChi as date, thanhTienChi as thanhTien, matHangChi as matHang, ghiChuChi as ghiChu, hangMuc as hangMuc, account_id " +
                 "FROM giaoDichChi " +
                 "WHERE thanhTienChi BETWEEN ? AND ? AND account_id = ? " +
                 "UNION " +
                 "SELECT thuId as id, ngayThu as date, thanhTienThu as thanhTien, NULL as matHang, ghiChuThu as ghiChu, hangMucThu as hangMuc, account_id " +
                 "FROM giaoDichThu " +
                 "WHERE thanhTienThu BETWEEN ? AND ? AND account_id = ?";

    try {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, tu);
        preparedStatement.setString(2, den);
        preparedStatement.setInt(3, accountId);
        preparedStatement.setString(4, tu);
        preparedStatement.setString(5, den);
        preparedStatement.setInt(6, accountId);
        ResultSet rs = preparedStatement.executeQuery();
        GiaoDichModel giaoDichModel;
        while (rs.next()) {
            giaoDichModel = new GiaoDichModel(
                rs.getInt("id"),
                rs.getString("date"),
                rs.getString("matHang"),
                rs.getDouble("thanhTien"),
                rs.getString("ghiChu"),
                rs.getString("hangMuc"),
                rs.getInt("account_id")
            );
            infor.add(giaoDichModel);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    } finally {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return infor;
}

    
    public ArrayList<GiaoDichModel> searchTenGiaoDich(String ten, int accountId){
        ArrayList<GiaoDichModel> infor = new ArrayList<GiaoDichModel>();
        Connection connection = JDBCConnection.getJDBCConecction();
        String sql = "SELECT * FROM giaodichchi WHERE matHangChi LIKE ? AND account_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ten);
            preparedStatement.setInt(2, accountId);
            ResultSet rs = preparedStatement.executeQuery();
            GiaoDichModel giaoDichModel;
            while (rs.next()){
//                giaoDichModel = new GiaoDichModel(rs.getInt("thuId"));
                giaoDichModel = new GiaoDichModel(rs.getInt("chiId"), rs.getString("ngayChi")
                , rs.getString("matHangChi"), rs.getDouble("thanhTienChi")
                , rs.getString("ghiChuChi"), rs.getString("hangMuc"));
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
        String sql = "SELECT * FROM giaodichchi WHERE ngayChi BETWEEN ? AND ? AND account_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, tu);
            preparedStatement.setString(2, den);
            preparedStatement.setInt(3, accountId);
            ResultSet rs = preparedStatement.executeQuery();
            GiaoDichModel giaoDichModel;
            while (rs.next()){
                giaoDichModel = new GiaoDichModel(rs.getInt("chiId"), rs.getString("ngayChi")
                , rs.getString("matHangChi"), rs.getDouble("thanhTienChi")
                , rs.getString("ghiChuChi"), rs.getString("hangMuc"));
                infor.add(giaoDichModel);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }   
        return infor;
    }
    
    public ArrayList<GiaoDichModel> searchThoiGianGiaoDichThuChi(String tu, String den, int accountId) {
        ArrayList<GiaoDichModel> infor = new ArrayList<GiaoDichModel>();
        Connection connection = JDBCConnection.getJDBCConecction();

        String sql = "SELECT chiId as id, ngayChi as ngay, thanhTienChi as thanhTien, matHangChi as matHang, ghiChuChi as ghiChu, hangMuc as hangMuc, account_id " +
                     "FROM giaoDichChi " +
                     "WHERE ngayChi BETWEEN ? AND ? AND account_id = ? " +
                     "UNION " +
                     "SELECT thuId as id, ngayThu as ngay, thanhTienThu as thanhTien, NULL as matHang, ghiChuThu as ghiChu, hangMucThu as hangMuc, account_id " +
                     "FROM giaoDichThu " +
                     "WHERE ngayThu BETWEEN ? AND ? AND account_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, tu);
            preparedStatement.setString(2, den);
            preparedStatement.setInt(3, accountId);
            preparedStatement.setString(4, tu);
            preparedStatement.setString(5, den);
            preparedStatement.setInt(6, accountId);

            ResultSet rs = preparedStatement.executeQuery();
            GiaoDichModel giaoDichModel;

            while (rs.next()) {
                giaoDichModel = new GiaoDichModel(rs.getInt("id"), rs.getString("ngay"),
                    rs.getString("matHang"), rs.getDouble("thanhTien"),
                    rs.getString("ghiChu"), rs.getString("hangMuc"));
                giaoDichModel.setAccountId(rs.getInt("account_id"));
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
        String sql = "SELECT * FROM giaodichchi WHERE account_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                GiaoDichModel giaoDichModel = new GiaoDichModel();
                giaoDichModel.setId(rs.getInt("chiId"));
                giaoDichModel.setDate(rs.getString("ngayChi"));
                giaoDichModel.setMatHang(rs.getString("matHangChi"));
                giaoDichModel.setThanhTien(rs.getDouble("thanhTienChi"));
                giaoDichModel.setGhiChu(rs.getString("ghiChuChi"));
                giaoDichModel.setHangMuc(rs.getString("hangMuc"));
                giaoDichModel.setAccountId(rs.getInt("account_id"));                
                infor.add(giaoDichModel);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }   
        return infor;
    }
    
    public List<GiaoDichThuModel> getAllInforUserThu(int accountId){
        loginModel = new LoginModel();
        List<GiaoDichThuModel> infor = new ArrayList<GiaoDichThuModel>();
        Connection connection = JDBCConnection.getJDBCConecction();
//        String sql = "SELECT * FROM giaodichthu WHERE account_id = " + String.valueOf(loginModel.getAccount_id());
        String sql = "SELECT * FROM giaodichthu WHERE account_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                GiaoDichThuModel giaoDichThuModel = new GiaoDichThuModel();
                giaoDichThuModel.setId(rs.getInt("thuId"));
                giaoDichThuModel.setDate(rs.getString("ngayThu"));
                giaoDichThuModel.setThanhTien(rs.getDouble("thanhTienThu"));
                giaoDichThuModel.setGhiChu(rs.getString("ghiChuThu"));
                giaoDichThuModel.setHangMuc(rs.getString("hangMucThu"));
                giaoDichThuModel.setAccountId(rs.getInt("account_id"));                
                infor.add(giaoDichThuModel);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }   
        return infor;
    }
    
    public List<GiaoDichModel> getListByMoney(int accountId) {
        List<GiaoDichModel> infor = new ArrayList<GiaoDichModel>();
        Connection connection = JDBCConnection.getJDBCConecction();
        String sql = "SELECT YEAR(ngayChi) AS Year, MONTH(ngayChi) AS Month, " +
                     "COALESCE(SUM(thanhTienChi), 0) AS TotalMoney " +
                     "FROM giaoDichChi " +
                     "WHERE account_id = ? " + // Thêm điều kiện cho accountId
                     "GROUP BY YEAR(ngayChi), MONTH(ngayChi) " +
                     "ORDER BY YEAR(ngayChi), MONTH(ngayChi)";

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

    public List<GiaoDichModel> getListByMoneyYear(int accountId, int year) {
        List<GiaoDichModel> infor = new ArrayList<GiaoDichModel>();
        Connection connection = JDBCConnection.getJDBCConecction();
        String sql = "SELECT YEAR(ngayChi) AS Year, MONTH(ngayChi) AS Month, " +
                     "COALESCE(SUM(thanhTienChi), 0) AS TotalMoney " +
                     "FROM giaoDichChi " +
                     "WHERE account_id = ? AND YEAR(ngayChi) = ? " + // Add condition for the selected year
                     "GROUP BY YEAR(ngayChi), MONTH(ngayChi) " +
                     "ORDER BY YEAR(ngayChi), MONTH(ngayChi)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountId); // Set the value for accountId
            preparedStatement.setInt(2, year); // Set the value for the selected year
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
    
    public GiaoDichModel getInforUser(int accountId){
        Connection connection = JDBCConnection.getJDBCConecction();
//        String sql = "SELECT * FROM giaodichthu WHERE account_id = " + String.valueOf(loginModel.getAccount_id());
        String sql = "SELECT * FROM giaodichchi WHERE chiId = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                GiaoDichModel giaoDichModel = new GiaoDichModel();
                giaoDichModel.setId(rs.getInt("chiId"));
                giaoDichModel.setDate(rs.getString("ngayChi"));
                giaoDichModel.setMatHang(rs.getString("matHangChi"));
                giaoDichModel.setThanhTien(rs.getDouble("thanhTienChi"));
                giaoDichModel.setGhiChu(rs.getString("ghiChuChi"));
                giaoDichModel.setHangMuc(rs.getString("hangMuc"));
                giaoDichModel.setAccountId(rs.getInt("account_id"));                
                
                return giaoDichModel;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }   
        return null;
    }
    public GiaoDichThuModel getInforUserThu(int accountId){
        Connection connection = JDBCConnection.getJDBCConecction();
//        String sql = "SELECT * FROM giaodichthu WHERE account_id = " + String.valueOf(loginModel.getAccount_id());
        String sql = "SELECT * FROM giaodichthu WHERE thuId = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                GiaoDichThuModel giaoDichThuModel = new GiaoDichThuModel();
                
                giaoDichThuModel.setId(rs.getInt("thuId"));
                giaoDichThuModel.setDate(rs.getString("ngayThu"));
                giaoDichThuModel.setThanhTien(rs.getDouble("thanhTienThu"));
                giaoDichThuModel.setGhiChu(rs.getString("ghiChuThu"));
                giaoDichThuModel.setHangMuc(rs.getString("hangMucThu"));
                giaoDichThuModel.setAccountId(rs.getInt("account_id"));                
                
                return giaoDichThuModel;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }   
        return null;
    }
    
    public List<GiaoDichModel> getAllInforUserThuChi(int accountId){
        List<GiaoDichModel> infor = new ArrayList<>();
        Connection connection = JDBCConnection.getJDBCConecction();
        String sql = "SELECT chiId as id, ngayChi as date, thanhTienChi as thanhTien, matHangChi as matHang, ghiChuChi as ghiChu, hangMuc as hangMuc, account_id " +
                     "FROM giaoDichChi " +
                     "WHERE account_id = ? " +
                     "UNION " +
                     "SELECT thuId as id, ngayThu as date, thanhTienThu as thanhTien, NULL as matHang, ghiChuThu as ghiChu, hangMucThu as hangMuc, account_id " +
                     "FROM giaoDichThu " +
                     "WHERE account_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountId);
            preparedStatement.setInt(2, accountId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                GiaoDichModel giaoDichModel = new GiaoDichModel();
                giaoDichModel.setId(rs.getInt("id"));
                giaoDichModel.setDate(rs.getString("date"));
                giaoDichModel.setMatHang(rs.getString("matHang"));
                giaoDichModel.setThanhTien(rs.getDouble("thanhTien"));
                giaoDichModel.setGhiChu(rs.getString("ghiChu"));
                giaoDichModel.setHangMuc(rs.getString("hangMuc"));
                giaoDichModel.setAccountId(rs.getInt("account_id"));
                infor.add(giaoDichModel);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return infor;
    }

}

