package com.anhtester.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginPOJO_Lombok {

    private String username;// cái này của api B
    private String password;// cái này của api B

    //Tạo constructer có tham số
//    public LoginPOJO_Lombok(String username, String password) {
//        this.username = username;
//        this.password = password;
//    }

    // constructer không có tham số
    public LoginPOJO_Lombok() {

    }
}
