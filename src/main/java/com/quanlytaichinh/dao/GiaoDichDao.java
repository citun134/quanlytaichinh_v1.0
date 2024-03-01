
package com.quanlytaichinh.dao;

import com.quanlytaichinh.model.GiaoDichModel;
import com.quanlytaichinh.model.GiaoDichThuModel;
import com.quanlytaichinh.model.LaiSuatVayModel;
import com.quanlytaichinh.model.SoTietKiemModel;
import com.quanlytaichinh.model.SoTienDaTraModel;
import com.quanlytaichinh.model.LoginModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class GiaoDichDao {
//    private GiaoDichModel giaoDichModel;
    public GiaoDichModel giaoDichModel;
    public GiaoDichThuModel giaoDichThuModel;
    public SoTietKiemModel soTietKiemModel; 
    public LaiSuatVayModel laiSuatVayModel;
    public LoginModel loginModel;
    public SoTienDaTraModel soTienDaTraModel;
    
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
    
    public void addGiaoDichSTK(SoTietKiemModel giaoDichModel) {
        Connection connection = JDBCConnection.getJDBCConecction();
        String sql = "INSERT INTO soTietKiem (ngayGui, tenNganHang, soTienGui, laiSuatGui, soTienLaiNhanDuoc, tongTienNhanDuoc, kyHan, account_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, giaoDichModel.getNgayGui());
            preparedStatement.setString(2, giaoDichModel.getTenNganHang());
            preparedStatement.setDouble(3, giaoDichModel.getSoTienGui());
            preparedStatement.setDouble(4, giaoDichModel.getLaiSuatGui());
            preparedStatement.setDouble(5, giaoDichModel.getSoTienLaiNhanDuoc());
            preparedStatement.setDouble(6, giaoDichModel.getTongTienNhanDuoc());
            preparedStatement.setDouble(7, giaoDichModel.getKyHan());
            preparedStatement.setInt(8, giaoDichModel.getAccountId());
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void addGiaoDichLSV(LaiSuatVayModel giaoDichModel) {
        Connection connection = JDBCConnection.getJDBCConecction();
        String sql = "INSERT INTO LaiSuatVay (tenNganHangLSV, giaTriBatDongSan,"
                + "soTienVay, thoiGianVay, laiSuat, ngayGiaiNgan, "
                + "soTienPhaiTraHangThang, tongLaiPhaiTra, account_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, giaoDichModel.getTenNganHangLSV());
            preparedStatement.setDouble(2, giaoDichModel.getGiaTriBatDongSan());
            preparedStatement.setDouble(3, giaoDichModel.getSoTienVay());
            preparedStatement.setDouble(4, giaoDichModel.getThoiGianVay());
            preparedStatement.setDouble(5, giaoDichModel.getLaiSuat());
            preparedStatement.setString(6, giaoDichModel.getNgayGiaiNgan());
            preparedStatement.setDouble(7, giaoDichModel.getSoTienPhaiTraHangThang());
            preparedStatement.setDouble(8, giaoDichModel.getTongLaiPhaiTra());
            preparedStatement.setInt(9, giaoDichModel.getAccountId());
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
    
    public void deleteSoTietKiem(int id){
        Connection connection = JDBCConnection.getJDBCConecction();
        String sql = "DELETE FROM sotietkiem WHERE tietKiemId = ? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }  
    
    public void deleteLaiSuatVay(int id){
        Connection connection = JDBCConnection.getJDBCConecction();
        String sql = "DELETE FROM LaiSuatVay WHERE laiSuatVayId = ? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }  
    
    public void deleteAllGiaoDichChi(int id) {
        Connection connection = JDBCConnection.getJDBCConecction();
        String sql = "DELETE FROM giaodichchi Where account_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void deleteAllGiaoDichThu(int id) {
        Connection connection = JDBCConnection.getJDBCConecction();
        String sql = "DELETE FROM giaodichthu Where account_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void deleteAllSoTietKiem(int id) {
        Connection connection = JDBCConnection.getJDBCConecction();
        String sql = "DELETE FROM sotietkiem Where account_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void deleteAllLaiSuatVay(int id) {
        Connection connection = JDBCConnection.getJDBCConecction();
        String sql = "DELETE FROM LaiSuatVay Where account_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
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
    
    public void updateSoTietKiem(SoTietKiemModel giaoDichModel) {
        Connection connection = JDBCConnection.getJDBCConecction();
        String sql = "UPDATE soTietKiem SET ngayGui = ?, tenNganHang = ?,"
                + " soTienGui = ?, laiSuatGui = ?, soTienLaiNhanDuoc = ?,"
                + " tongTienNhanDuoc = ?, kyHan = ? WHERE tietKiemId = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, giaoDichModel.getNgayGui());
            preparedStatement.setString(2, giaoDichModel.getTenNganHang());
            preparedStatement.setDouble(3, giaoDichModel.getSoTienGui());
            preparedStatement.setDouble(4, giaoDichModel.getLaiSuatGui());
            preparedStatement.setDouble(5, giaoDichModel.getSoTienLaiNhanDuoc());
            preparedStatement.setDouble(6, giaoDichModel.getTongTienNhanDuoc());
            preparedStatement.setDouble(7, giaoDichModel.getKyHan());
            preparedStatement.setInt(8, giaoDichModel.getTietKiemId());
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void updateLaiSuatVay(LaiSuatVayModel giaoDichModel) {
        Connection connection = JDBCConnection.getJDBCConecction();
        String sql = "UPDATE LaiSuatVay SET tenNganHangLSV = ?, giaTriBatDongSan = ?,"
                + " soTienVay = ?, thoiGianVay = ?, laiSuat = ?, ngayGiaiNgan = ?,"
                + " soTienPhaiTraHangThang = ?, tongLaiPhaiTra = ? WHERE laiSuatVayId = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setString(1, giaoDichModel.getTenNganHangLSV());
            preparedStatement.setDouble(2, giaoDichModel.getGiaTriBatDongSan());
            preparedStatement.setDouble(3, giaoDichModel.getSoTienVay());
            preparedStatement.setDouble(4, giaoDichModel.getThoiGianVay());
            preparedStatement.setDouble(5, giaoDichModel.getLaiSuat());
            preparedStatement.setString(6, giaoDichModel.getNgayGiaiNgan());
            preparedStatement.setDouble(7, giaoDichModel.getSoTienPhaiTraHangThang());
            preparedStatement.setDouble(8, giaoDichModel.getTongLaiPhaiTra());
            preparedStatement.setInt(9, giaoDichModel.getLaiSuatVayId());
            
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
        String sql = "SELECT * FROM giaodichchi WHERE matHangChi LIKE ? AND account_id = ? ORDER BY ngayChi ASC";

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
                 "WHERE ngayThu BETWEEN ? AND ? AND account_id = ? " +
                 "ORDER BY ngay ASC"; // Sắp xếp theo ngày tăng dần

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
    } finally {
        // Đóng kết nối sau khi sử dụng
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
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
    
    public List<SoTietKiemModel> getAllInforUserSTK(int accountId){
        soTietKiemModel = new SoTietKiemModel();
        List<SoTietKiemModel> infor = new ArrayList<SoTietKiemModel>();
        Connection connection = JDBCConnection.getJDBCConecction();
//        String sql = "SELECT * FROM giaodichthu WHERE account_id = " + String.valueOf(loginModel.getAccount_id());
        String sql = "SELECT * FROM sotietkiem WHERE account_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                SoTietKiemModel soTietKiemModel = new SoTietKiemModel();
                soTietKiemModel.setTietKiemId(rs.getInt("tietKiemId"));
                soTietKiemModel.setNgayGui(rs.getString("ngayGui"));
                soTietKiemModel.setTenNganHang(rs.getString("tenNganHang"));
                soTietKiemModel.setSoTienGui(rs.getDouble("soTienGui"));
                soTietKiemModel.setLaiSuatGui(rs.getDouble("laiSuatGui"));
                soTietKiemModel.setSoTienLaiNhanDuoc(rs.getDouble("soTienLaiNhanDuoc"));
                soTietKiemModel.setTongTienNhanDuoc(rs.getDouble("tongTienNhanDuoc"));
                soTietKiemModel.setKyHan(rs.getDouble("kyHan"));
                soTietKiemModel.setAccountId(rs.getInt("account_id"));                
                infor.add(soTietKiemModel);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }   
        return infor;
    }
    
    public List<LaiSuatVayModel> getAllInforUserLSV(int accountId){
        laiSuatVayModel = new LaiSuatVayModel();
        List<LaiSuatVayModel> infor = new ArrayList<LaiSuatVayModel>();
        
        Connection connection = JDBCConnection.getJDBCConecction();
//        String sql = "SELECT * FROM giaodichthu WHERE account_id = " + String.valueOf(loginModel.getAccount_id());
        String sql = "SELECT * FROM LaiSuatVay WHERE account_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                LaiSuatVayModel laiSuatVayModel = new LaiSuatVayModel();
                laiSuatVayModel.setLaiSuatVayId(rs.getInt("laiSuatVayId"));
                laiSuatVayModel.setTenNganHangLSV(rs.getString("tenNganHangLSV"));
                laiSuatVayModel.setGiaTriBatDongSan(rs.getDouble("giaTriBatDongSan"));
                laiSuatVayModel.setSoTienVay(rs.getDouble("soTienVay"));
                laiSuatVayModel.setThoiGianVay(rs.getDouble("thoiGianVay"));
                laiSuatVayModel.setLaiSuat(rs.getDouble("laiSuat"));
                laiSuatVayModel.setNgayGiaiNgan(rs.getString("ngayGiaiNgan"));
                laiSuatVayModel.setSoTienPhaiTraHangThang(rs.getDouble("soTienPhaiTraHangThang"));
                laiSuatVayModel.setTongLaiPhaiTra(rs.getDouble("tongLaiPhaiTra"));
                laiSuatVayModel.setAccountId(rs.getInt("account_id"));                
                infor.add(laiSuatVayModel);
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

    public List<GiaoDichModel> thongKeGiaoDichChi(int accountId, int year) {
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
    
    public List<GiaoDichModel> thongKeGiaoDichChiSua(int accountId, String tu, String den) {
        List<GiaoDichModel> infor = new ArrayList<GiaoDichModel>();
        Connection connection = JDBCConnection.getJDBCConecction();
        String sql = "SELECT YEAR(ngayChi) AS Year, MONTH(ngayChi) AS Month, " +
                     "COALESCE(SUM(thanhTienChi), 0) AS TotalMoney " +
                     "FROM giaoDichChi " +
                     "WHERE account_id = ? " +
                     "AND ngayChi BETWEEN ? AND ?" + // Add condition for the selected year
                     "GROUP BY YEAR(ngayChi), MONTH(ngayChi) " +
                     "ORDER BY YEAR(ngayChi), MONTH(ngayChi)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountId); // Set the value for accountId
            preparedStatement.setString(2, tu);
            preparedStatement.setString(3, den);

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
    
    public List<GiaoDichModel> thongKeGiaoDichThuSua(int accountId, String tu, String den) {
        List<GiaoDichModel> infor = new ArrayList<GiaoDichModel>();
        Connection connection = JDBCConnection.getJDBCConecction();
        String sql = "SELECT YEAR(ngayThu) AS Year, MONTH(ngayThu) AS Month, " +
                     "COALESCE(SUM(thanhTienThu), 0) AS TotalMoney " +
                     "FROM giaoDichThu " +
                     "WHERE account_id = ? " +
                     "AND ngayThu BETWEEN ? AND ?" + // Add condition for the selected year
                     "GROUP BY YEAR(ngayThu), MONTH(ngayThu) " +
                     "ORDER BY YEAR(ngayThu), MONTH(ngayThu)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountId); // Set the value for accountId
            preparedStatement.setString(2, tu);
            preparedStatement.setString(3, den);

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
    
    public List<GiaoDichModel> thongKeGiaoDichThuChiSua(int accountId, String fromYear, String toYear) {
        List<GiaoDichModel> infor = new ArrayList<>();
        Connection connection = JDBCConnection.getJDBCConecction();

        String sql = "SELECT Year, Month, " +
                     "COALESCE(SUM(thanhTienThu) + SUM(thanhTienChi), 0) AS TotalMoney " +
                     "FROM ( " +
                     "    SELECT YEAR(ngayThu) AS Year, MONTH(ngayThu) AS Month, " +
                     "           thanhTienThu, 0 AS thanhTienChi " +
                     "    FROM giaoDichThu " +
                     "    WHERE account_id = ? AND (ngayThu) BETWEEN ? AND ? " +
                     "    UNION ALL " +
                     "    SELECT YEAR(ngayChi) AS Year, MONTH(ngayChi) AS Month, " +
                     "           0 AS thanhTienThu, thanhTienChi " +
                     "    FROM giaoDichChi " +
                     "    WHERE account_id = ? AND (ngayChi) BETWEEN ? AND ? " +
                     ") AS combined " +
                     "GROUP BY Year, Month " +
                     "ORDER BY Year, Month";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountId);
            preparedStatement.setString(2, fromYear);
            preparedStatement.setString(3, toYear);
            preparedStatement.setInt(4, accountId);
            preparedStatement.setString(5, fromYear);
            preparedStatement.setString(6, toYear);
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
        } finally {
            // Đóng kết nối sau khi sử dụng
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
    
    public List<GiaoDichModel> thongKeGiaoDichThu(int accountId, int year) {
        List<GiaoDichModel> infor = new ArrayList<GiaoDichModel>();
        Connection connection = JDBCConnection.getJDBCConecction();
        String sql = "SELECT YEAR(ngayThu) AS Year, MONTH(ngayThu) AS Month, " +
                     "COALESCE(SUM(thanhTienThu), 0) AS TotalMoney " +
                     "FROM giaoDichThu " +
                     "WHERE account_id = ? AND YEAR(ngayThu) = ? " + // Add condition for the selected year
                     "GROUP BY YEAR(ngayThu), MONTH(ngayThu) " +
                     "ORDER BY YEAR(ngayThu), MONTH(ngayThu)";

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
    
    public List<GiaoDichModel> thongKeGiaoDichThuChi(int accountId, int year) {
        List<GiaoDichModel> infor = new ArrayList<>();
        Connection connection = JDBCConnection.getJDBCConecction();
        String sql = "SELECT Year, Month, " +
                     "COALESCE(SUM(thanhTienThu) + SUM(thanhTienChi), 0) AS TotalMoney " +
                     "FROM ( " +
                     "    SELECT YEAR(ngayThu) AS Year, MONTH(ngayThu) AS Month, " +
                     "           thanhTienThu, 0 AS thanhTienChi " +
                     "    FROM giaoDichThu " +
                     "    WHERE account_id = ? AND YEAR(ngayThu) = ? " +
                     "    UNION ALL " +
                     "    SELECT YEAR(ngayChi) AS Year, MONTH(ngayChi) AS Month, " +
                     "           0 AS thanhTienThu, thanhTienChi " +
                     "    FROM giaoDichChi " +
                     "    WHERE account_id = ? AND YEAR(ngayChi) = ? " +
                     ") AS combined " +
                     "GROUP BY Year, Month " +
                     "ORDER BY Year, Month";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountId);
            preparedStatement.setInt(2, year);
            preparedStatement.setInt(3, accountId);
            preparedStatement.setInt(4, year);
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
    
    public SoTietKiemModel getInforUserSTK(int accountId){
        Connection connection = JDBCConnection.getJDBCConecction();
//        String sql = "SELECT * FROM giaodichthu WHERE account_id = " + String.valueOf(loginModel.getAccount_id());
        String sql = "SELECT * FROM sotietkiem WHERE tietKiemId = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                SoTietKiemModel soTietKiemModel = new SoTietKiemModel();
                
                soTietKiemModel.setTietKiemId(rs.getInt("tietKiemId"));
                soTietKiemModel.setNgayGui(rs.getString("ngayGui"));
                soTietKiemModel.setTenNganHang(rs.getString("tenNganHang"));
                soTietKiemModel.setSoTienGui(rs.getDouble("soTienGui"));
                soTietKiemModel.setLaiSuatGui(rs.getDouble("laiSuatGui"));
                soTietKiemModel.setSoTienLaiNhanDuoc(rs.getDouble("soTienLaiNhanDuoc"));
                soTietKiemModel.setTongTienNhanDuoc(rs.getDouble("tongTienNhanDuoc"));
                soTietKiemModel.setKyHan(rs.getDouble("kyHan"));                
                soTietKiemModel.setAccountId(rs.getInt("account_id"));                            

                return soTietKiemModel;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }   
        return null;
    }
    
    public LaiSuatVayModel getInforUserLSV(int accountId){
        Connection connection = JDBCConnection.getJDBCConecction();
//        String sql = "SELECT * FROM giaodichthu WHERE account_id = " + String.valueOf(loginModel.getAccount_id());
        String sql = "SELECT * FROM laisuatvay WHERE laiSuatVayId = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                LaiSuatVayModel laiSuatVayModel = new LaiSuatVayModel();
                
                laiSuatVayModel.setLaiSuatVayId(rs.getInt("laiSuatVayId"));
                laiSuatVayModel.setTenNganHangLSV(rs.getString("tenNganHangLSV"));
                laiSuatVayModel.setGiaTriBatDongSan(rs.getDouble("giaTriBatDongSan"));
                laiSuatVayModel.setSoTienVay(rs.getDouble("soTienVay"));
                laiSuatVayModel.setThoiGianVay(rs.getDouble("thoiGianVay"));
                laiSuatVayModel.setLaiSuat(rs.getDouble("laiSuat"));
                laiSuatVayModel.setNgayGiaiNgan(rs.getString("ngayGiaiNgan"));
                laiSuatVayModel.setSoTienPhaiTraHangThang(rs.getDouble("soTienPhaiTraHangThang"));
                laiSuatVayModel.setTongLaiPhaiTra(rs.getDouble("tongLaiPhaiTra"));
                laiSuatVayModel.setLaiSuatVayId(rs.getInt("laiSuatVayId"));                            

                return laiSuatVayModel;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }   
        return null;
    }
    
    public List<SoTietKiemModel> layDanhSachSoTietKiem(int accountId, String tu, String den) {
        List<SoTietKiemModel> infor = new ArrayList<SoTietKiemModel>();
        Connection connection = JDBCConnection.getJDBCConecction();
        String sql = "SELECT MONTH(ngayGui) AS Month, YEAR(ngayGui) AS Year, tongTienNhanDuoc, kyHan " +
                     "FROM soTietKiem "+
                     "WHERE account_id = ? " +
                     "AND ngayGui BETWEEN ? AND ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountId); // Set the value for accountId
            preparedStatement.setString(2, tu);
            preparedStatement.setString(3, den);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                SoTietKiemModel soTietKiemModel = new SoTietKiemModel();
                soTietKiemModel.setYear(rs.getInt("Year"));
                soTietKiemModel.setMonth(rs.getInt("Month"));
                soTietKiemModel.setTongTienNhanDuoc(rs.getInt("tongTienNhanDuoc"));
                soTietKiemModel.setKyHan(rs.getDouble("kyHan"));
                infor.add(soTietKiemModel);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return infor;
    }
    
    public List<SoTietKiemModel> layDanhSachSoTietKiemToanBo(int accountId) {
        List<SoTietKiemModel> infor = new ArrayList<SoTietKiemModel>();
        Connection connection = JDBCConnection.getJDBCConecction();
        String sql = "SELECT DAY(ngayGui) AS Day, MONTH(ngayGui) AS Month, YEAR(ngayGui) AS Year, soTienLaiNhanDuoc, kyHan " +
                     "FROM soTietKiem "+
                     "WHERE account_id = ? " +
                     "AND ngayGui " +
                     "ORDER BY YEAR(ngayGui), MONTH(ngayGui);";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountId); // Set the value for accountId

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                SoTietKiemModel soTietKiemModel = new SoTietKiemModel();
                soTietKiemModel.setYear(rs.getInt("Year"));
                soTietKiemModel.setMonth(rs.getInt("Month"));
                soTietKiemModel.setDay(rs.getInt("Day"));
                soTietKiemModel.setSoTienLaiNhanDuoc(rs.getInt("soTienLaiNhanDuoc"));
                soTietKiemModel.setKyHan(rs.getDouble("kyHan"));
                infor.add(soTietKiemModel);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return infor;
    }
    
    public List<LaiSuatVayModel> layDanhSachLaiSuatVayToanBo(int accountId) {
        List<LaiSuatVayModel> infor = new ArrayList<LaiSuatVayModel>();
        Connection connection = JDBCConnection.getJDBCConecction();
        String sql = "SELECT MONTH(ngayGiaiNgan) AS Month, YEAR(ngayGiaiNgan) AS Year, soTienPhaiTraHangThang, thoiGianVay " +
                     "FROM laiSuatVay "+
                     "WHERE account_id = ? " +
                     "AND thoiGianVay " +
                     "ORDER BY YEAR(ngayGiaiNgan), MONTH(ngayGiaiNgan);";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountId); // Set the value for accountId

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                LaiSuatVayModel laiSuatVayModel = new LaiSuatVayModel();
                laiSuatVayModel.setYear(rs.getInt("Year"));
                laiSuatVayModel.setMonth(rs.getInt("Month"));
                laiSuatVayModel.setSoTienPhaiTraHangThang(rs.getInt("soTienPhaiTraHangThang"));
                laiSuatVayModel.setThoiGianVay(rs.getDouble("thoiGianVay"));
                infor.add(laiSuatVayModel);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return infor;
    }
    
    
    
     public void addGiaoDichSTDT(SoTienDaTraModel soTienDaTraModel) {
        Connection connection = JDBCConnection.getJDBCConecction();
        String sql = "INSERT INTO soTienDaTraAccount (ngayTra, soTien, laiSuatVayId, account_id) VALUES (?, ?, ?, ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, soTienDaTraModel.getNgayTra());
            preparedStatement.setDouble(2, soTienDaTraModel.getSoTien());
            preparedStatement.setInt(3, soTienDaTraModel.getLaiSuatVayId());
            preparedStatement.setInt(4, soTienDaTraModel.getAccount_id());
            
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
     
    public List<SoTienDaTraModel> getAllInforUserSTDT(int accountId){
        soTienDaTraModel = new SoTienDaTraModel();
        List<SoTienDaTraModel> infor = new ArrayList<SoTienDaTraModel>();
        Connection connection = JDBCConnection.getJDBCConecction();
//        String sql = "SELECT * FROM giaodichthu WHERE account_id = " + String.valueOf(loginModel.getAccount_id());
        String sql = "SELECT * FROM soTienDaTraAccount WHERE laiSuatVayId = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                SoTienDaTraModel soTienDaTraModel = new SoTienDaTraModel();
                soTienDaTraModel.setSoTienDaTraId(rs.getInt("soTienDaTraId"));
                soTienDaTraModel.setNgayTra(rs.getString("ngayTra"));
                soTienDaTraModel.setSoTien(rs.getDouble("soTien"));
                soTienDaTraModel.setLaiSuatVayId(rs.getInt("laiSuatVayId"));                
                infor.add(soTienDaTraModel);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }   
        return infor;
    }
    
    public List<SoTienDaTraModel> layDanhSTDTToanBo(int accountId) {
        List<SoTienDaTraModel> infor = new ArrayList<SoTienDaTraModel>();
        Connection connection = JDBCConnection.getJDBCConecction();
        String sql = "SELECT MONTH(ngayTra) AS Month, YEAR(ngayTra) AS Year, soTien " +
                     "FROM soTienDaTraAccount "+
                     "WHERE account_id = ? " +
                     "ORDER BY YEAR(ngayTra), MONTH(ngayTra);";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountId); // Set the value for accountId

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                SoTienDaTraModel soTienDaTraModel = new SoTienDaTraModel();
                soTienDaTraModel.setYear(rs.getInt("Year"));
                soTienDaTraModel.setMonth(rs.getInt("Month"));
                soTienDaTraModel.setSoTien(rs.getInt("soTien"));
                infor.add(soTienDaTraModel);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return infor;
    }
    
    public void deleteSTDT(int id) {
        Connection connection = JDBCConnection.getJDBCConecction();
        String sql = "DELETE FROM soTienDaTraAccount Where soTienDaTraId = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void updateSTDT(SoTienDaTraModel giaoDichModel) {
        Connection connection = JDBCConnection.getJDBCConecction();
        String sql = "UPDATE sotiendatraaccount SET ngayTra = ?, soTien = ? "
                + "WHERE sotiendatraid = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setString(1, giaoDichModel.getNgayTra());
            preparedStatement.setDouble(2, giaoDichModel.getSoTien());
            preparedStatement.setDouble(3, giaoDichModel.getSoTienDaTraId());
            
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
    public double getTongChi(int accountId) {
        double tongThanhTien = 0.0;
        Connection connection = JDBCConnection.getJDBCConecction();
        String sql = "SELECT SUM(thanhTienChi) AS tongThanhTien FROM giaoDichChi WHERE account_id = ?;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, accountId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                tongThanhTien = rs.getDouble("tongThanhTien");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return tongThanhTien;
    }
    
    public double getTongChiThangHienTai(int accountId) {
    double tongThanhTien = 0.0;
    Connection connection = JDBCConnection.getJDBCConecction();

    // Lấy tháng và năm hiện tại
    Calendar cal = Calendar.getInstance();
    int currentMonth = cal.get(Calendar.MONTH) + 1; // Tháng tính từ 0 (tháng 1) đến 11 (tháng 12)
    int currentYear = cal.get(Calendar.YEAR);

    String sql = "SELECT SUM(thanhTienChi) AS tongThanhTien FROM giaoDichChi " +
                 "WHERE account_id = ? AND MONTH(ngayChi) = ? AND YEAR(ngayChi) = ?;";

    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        preparedStatement.setInt(1, accountId);
        preparedStatement.setInt(2, currentMonth);
        preparedStatement.setInt(3, currentYear);

        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next()) {
            tongThanhTien = rs.getDouble("tongThanhTien");
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
    }

    return tongThanhTien;
}

    public double getTongThu(int accountId) {
        double tongThanhTien = 0.0;
        Connection connection = JDBCConnection.getJDBCConecction();
        String sql = "SELECT SUM(thanhTienThu) AS tongThanhTien FROM giaoDichThu WHERE account_id = ?;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, accountId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                tongThanhTien = rs.getDouble("tongThanhTien");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return tongThanhTien;
    }
    
    public double getTongThuThangHienTai(int accountId) {
    double tongThanhTien = 0.0;
    Connection connection = JDBCConnection.getJDBCConecction();

    // Lấy tháng và năm hiện tại
    Calendar cal = Calendar.getInstance();
    int currentMonth = cal.get(Calendar.MONTH) + 1; // Tháng tính từ 0 (tháng 1) đến 11 (tháng 12)
    int currentYear = cal.get(Calendar.YEAR);

    String sql = "SELECT SUM(thanhTienThu) AS tongThanhTien FROM giaoDichThu " +
                 "WHERE account_id = ? AND MONTH(ngayThu) = ? AND YEAR(ngayThu) = ?;";

    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        preparedStatement.setInt(1, accountId);
        preparedStatement.setInt(2, currentMonth);
        preparedStatement.setInt(3, currentYear);

        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next()) {
            tongThanhTien = rs.getDouble("tongThanhTien");
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
    }

    return tongThanhTien;
}


    public List<GiaoDichModel> getHangMucChi(int accountId, String hangMuc){
        loginModel = new LoginModel();
        List<GiaoDichModel> infor = new ArrayList<GiaoDichModel>();
        Connection connection = JDBCConnection.getJDBCConecction();
//        String sql = "SELECT * FROM giaodichthu WHERE account_id = " + String.valueOf(loginModel.getAccount_id());
        String sql = "SELECT * FROM giaodichchi WHERE account_id = ? AND hangMuc = ? ORDER BY ngayChi ASC";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountId);
            preparedStatement.setString(2, hangMuc);
            
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
    
     public List<GiaoDichThuModel> getHangMucThu(int accountId, String hangMuc){
        loginModel = new LoginModel();
        List<GiaoDichThuModel> infor = new ArrayList<GiaoDichThuModel>();
        Connection connection = JDBCConnection.getJDBCConecction();
//        String sql = "SELECT * FROM giaodichthu WHERE account_id = " + String.valueOf(loginModel.getAccount_id());
        String sql = "SELECT * FROM giaodichthu WHERE account_id = ? and hangMucThu = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountId);
            preparedStatement.setString(2, hangMuc);
            
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
     
     public double getTongMatHangChi(int accountId, String matHangChi) {
        double tongThanhTien = 0.0;
        Connection connection = JDBCConnection.getJDBCConecction();
        String sql = "SELECT SUM(thanhTienChi) AS tongThanhTien FROM giaoDichChi WHERE account_id = ? AND matHangChi = ?;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, accountId);
            preparedStatement.setString(2, matHangChi);
            
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                tongThanhTien = rs.getDouble("tongThanhTien");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return tongThanhTien;
    }
     public double getTongHangMucChi(int accountId, String matHangChi) {
        double tongThanhTien = 0.0;
        Connection connection = JDBCConnection.getJDBCConecction();
        String sql = "SELECT SUM(thanhTienChi) AS tongThanhTien FROM giaoDichChi WHERE account_id = ? AND hangMuc = ?;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, accountId);
            preparedStatement.setString(2, matHangChi);
            
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                tongThanhTien = rs.getDouble("tongThanhTien");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return tongThanhTien;
    }
     
     
      public double getTongHangMucThu(int accountId, String matHangChi) {
        double tongThanhTien = 0.0;
        Connection connection = JDBCConnection.getJDBCConecction();
        String sql = "SELECT SUM(thanhTienThu) AS tongThanhTien FROM giaoDichThu WHERE account_id = ? and hangMucThu = ?;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, accountId);
            preparedStatement.setString(2, matHangChi);
            
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                tongThanhTien = rs.getDouble("tongThanhTien");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return tongThanhTien;
    }
    
    
     public double getTongNgayChi(int accountId, String tu, String den) {
        double tongThanhTien = 0.0;
        Connection connection = JDBCConnection.getJDBCConecction();
        String sql = "SELECT SUM(thanhTienChi) AS tongThanhTien FROM giaoDichChi WHERE account_id = ? " +
                    "and ngayChi between ? and ?;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, accountId);
            preparedStatement.setString(2, tu);
            preparedStatement.setString(3, den);
            
            
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                tongThanhTien = rs.getDouble("tongThanhTien");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return tongThanhTien;
    }
    
      public double getTongNgayThu(int accountId, String tu, String den) {
        double tongThanhTien = 0.0;
        Connection connection = JDBCConnection.getJDBCConecction();
        String sql = "SELECT SUM(thanhTienThu) AS tongThanhTien FROM giaoDichThu WHERE account_id = ? " +
                    "and ngayThu between ? and ?;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, accountId);
            preparedStatement.setString(2, tu);
            preparedStatement.setString(3, den);
            
            
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                tongThanhTien = rs.getDouble("tongThanhTien");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return tongThanhTien;
    }
      
        public double getTongTienLai(int accountId) {
        double tongThanhTien = 0.0;
        Connection connection = JDBCConnection.getJDBCConecction();
        String sql = "SELECT SUM(sotienlainhanduoc) AS tongThanhTien FROM sotietkiem WHERE account_id = ?;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, accountId);
            
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                tongThanhTien = rs.getDouble("tongThanhTien");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return tongThanhTien;
    }
        
        public double getTongSoTienGui(int accountId) {
        double tongThanhTien = 0.0;
        Connection connection = JDBCConnection.getJDBCConecction();
        String sql = "SELECT SUM(sotiengui) AS tongThanhTien FROM sotietkiem WHERE account_id = ?;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, accountId);
            
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                tongThanhTien = rs.getDouble("tongThanhTien");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return tongThanhTien;
    }
     
        
     public double getTongSoTienVay(int accountId) {
        double tongThanhTien = 0.0;
        Connection connection = JDBCConnection.getJDBCConecction();
        String sql = "SELECT SUM(sotienvay) AS tongThanhTien FROM laisuatvay WHERE account_id = ?;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, accountId);
            
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                tongThanhTien = rs.getDouble("tongThanhTien");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return tongThanhTien;
    }
     
      public double getTongSoTienTra(int accountId) {
        double tongThanhTien = 0.0;
        Connection connection = JDBCConnection.getJDBCConecction();
        String sql = "SELECT SUM(tonglaiphaitra) AS tongThanhTien FROM laisuatvay WHERE account_id = ?;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, accountId);
            
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                tongThanhTien = rs.getDouble("tongThanhTien");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return tongThanhTien;
    }
     
     
     
    
}

