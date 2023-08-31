package com.quanlytaichinh.model;

public class LoginModel {
    private String user;
    private String password;
    public int account_id;
    
    public LoginModel(){
    }
    
    public LoginModel(int account_id) {
        this.account_id  = account_id;
    }
    
    public LoginModel(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public LoginModel(String user, String password, int account_id) {
        this.user = user;
        this.password = password;
        this.account_id = account_id;
    }
    
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }
}
