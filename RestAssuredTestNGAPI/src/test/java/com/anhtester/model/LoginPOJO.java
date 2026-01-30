package com.anhtester.model;

public class LoginPOJO {
    private String username;// cái này của api B
    private String password;// cái này của api B

    //Tạo constructer có tham số
    public LoginPOJO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // constructer không có tham số
    public LoginPOJO() {

    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
