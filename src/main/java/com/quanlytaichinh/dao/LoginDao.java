package com.quanlytaichinh.dao;

import com.quanlytaichinh.model.LoginModel;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginDao {
    public List<LoginModel> getAllUsers() {
        List<LoginModel> users = new ArrayList<LoginModel>();
        Connection connection = JDBCConnection.getJDBCConecction();
        String sql = "SELECT * FROM accounts";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                LoginModel user = new LoginModel();
                user.setUser(rs.getString("user"));
                user.setPassword(rs.getString("password"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return users;        
    }
//    
//    public void getUserFromDao(LoginModel user){
//        Connection connection = JDBCConnection.getJDBCConecction();
//        String sql = "SElECT * FROM accounts WHERE username = ? AND password = ?";
//        PreparedStatement preparedStatement;
//        try {
//            preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, user.getUser());
//            preparedStatement.setString(2, user.getPassword());            
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    }
    public boolean getUserFromDao(LoginModel user) {
    Connection connection = JDBCConnection.getJDBCConecction();
    String sql = "SELECT * FROM accounts WHERE user = ? AND password = ?";
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try {
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getUser());
        preparedStatement.setString(2, user.getPassword());

        resultSet = preparedStatement.executeQuery();

        // Nếu có kết quả từ truy vấn, người dùng hợp lệ
        if (resultSet.next()) {
            return true;
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    } finally {
        // Đóng tài nguyên
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    return false;
    }



    public void addUser(LoginModel user) {
        Connection connection = JDBCConnection.getJDBCConecction();
        String sql = "INSERT INTO accounts (username, password) values (?, ?);";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUser());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
        
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
