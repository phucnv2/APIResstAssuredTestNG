package com.phucTester.datatest;

import com.phucTester.models.request.LoginRequestLombok;

public class LoginDataTest {
    // Test data constants
    public static final LoginRequestLombok VALID_USER = LoginRequestLombok.builder()
        .username("admin")
        .password("12345")
        .build();

    public static final LoginRequestLombok EMPTY_USERNAME = LoginRequestLombok.builder()
        .username("")
        .password("Demo@123")
        .build();

    public static final LoginRequestLombok EMPTY_PASSWORD = LoginRequestLombok.builder()
        .username("admin")
        .password("")
        .build();

    public static final LoginRequestLombok INVALID_USER = LoginRequestLombok.builder()
        .username("invaliduser")
        .password("wrongpass")
        .build();
}
