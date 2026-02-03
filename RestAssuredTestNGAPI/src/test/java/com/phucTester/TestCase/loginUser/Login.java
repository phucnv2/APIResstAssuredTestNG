package com.phucTester.TestCase.loginUser;

import globals.ConfigsGlobal;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class Login {
    @Test
    public void loginSuccess() {
        String fileDataLogin = "src/test/resources/data/login.json";
        RequestSpecification request = given();
        request.baseUri(ConfigsGlobal.URI)
            .basePath("/login")
            .accept("application/json")
            .contentType("application/json")
            .body(new File(fileDataLogin));

        Response response = request.when().post();

        response.prettyPrint();
        response.then().statusCode(200);
    }
}
