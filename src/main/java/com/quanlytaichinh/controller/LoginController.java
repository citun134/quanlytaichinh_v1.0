package com.quanlytaichinh.controller;

import com.quanlytaichinh.dao.LoginDao;
import com.quanlytaichinh.model.LoginModel;
import java.util.List;

public class LoginController {
    private LoginDao userDao; 
    
    public LoginController() {
        userDao = new LoginDao();
    }
    
    public List<LoginModel> getAllUsers(){
        return userDao.getAllUsers();
    }
    
    public void addUser(LoginModel user){
        userDao.addUser(user);
    }
    
    public void getUserFromDao(LoginModel user){
        userDao.getUserFromDao(user);
    }
    

}
