package com.quanlytaichinh.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
    public static Connection getJDBCConecction(){
        final String url = "jdbc:mysql://localhost:3306/quanlytaichinh_final";
        final String user = "root";
        final String password = "csdl2021";
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    
    public static void main(String[] args) {
        Connection connection = getJDBCConecction();
        if(connection != null){
            System.out.println("Successful!");
        } else {
            System.out.println("Erorr!");
        }
    }
}
