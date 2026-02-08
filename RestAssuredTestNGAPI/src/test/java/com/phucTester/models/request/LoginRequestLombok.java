package com.phucTester.models.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                  // Tự động tạo getter, setter, toString, equals, hashCode
@Builder               // Tạo builder pattern
@NoArgsConstructor     // Tạo constructor không tham số
@AllArgsConstructor
public class LoginRequestLombok {
    private String username;
    private String password;
}
