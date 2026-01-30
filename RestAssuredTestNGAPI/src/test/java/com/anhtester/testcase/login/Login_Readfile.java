package com.anhtester.testcase.login;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class Login_Readfile {
    @Test
    public void testLoginUser() {
        //Read Json file path
        String filePath = "src/test/resources/data/login.json";

        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
            .accept("application/json")
            .contentType("application/json")
            .body(new File(filePath));

        Response response = request.when().post("/login");
        response.prettyPrint();
        response.then().statusCode(200);

    }
}
