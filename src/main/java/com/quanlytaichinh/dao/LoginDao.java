package com.quanlytaichinh.dao;

import com.quanlytaichinh.model.GiaoDichModel;
import com.quanlytaichinh.model.LoginModel;
import java.beans.Statement;
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
