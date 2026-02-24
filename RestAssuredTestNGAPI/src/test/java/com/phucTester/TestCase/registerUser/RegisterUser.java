package com.phucTester.TestCase.registerUser;

import globals.ConfigsGlobal;
import helpers.PropertiesHelper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RegisterUser {
    @Test
    public void registerUserSuccess() {
        String fileloginData = "src/test/resources/data/registerUser.json";
        String requestBody = PropertiesHelper.getJsonValue(fileloginData, "success");
        RequestSpecification request = given();
        request.baseUri(ConfigsGlobal.URI)
            .basePath("/register")
            .accept("application/json")
            .contentType("application/json")
            .body(requestBody);
        Response response = request.when().post();
        response.prettyPrint();
        response.then().statusCode(200);
        JsonPath jsonPath = response.jsonPath();
        String getUser = jsonPath.get("username");
        String getPassword = PropertiesHelper.getJsonFieldValue(fileloginData, "success", "password");
    // login sau khi register
        RequestSpecification requestLogin = given();
        requestLogin.baseUri(ConfigsGlobal.URI)
            .basePath("/login")
            .accept("application/json")
            .contentType("application/json")
            .body("");
    }
}
