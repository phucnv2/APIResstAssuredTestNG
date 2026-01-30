package com.anhtester.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class RegisterUserPOJO_Lombok {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private int userStatus;

    public RegisterUserPOJO_Lombok(String username, String firstName, String lastName, String email, String password, String phone, int userStatus) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.userStatus = userStatus;
    }

    public RegisterUserPOJO_Lombok() {
    }

}
