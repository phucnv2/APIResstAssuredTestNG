package com.phucTester.TestCase.loginUser;

import com.phucTester.datatest.LoginDataTest;
import globals.ConfigsGlobal;
import helpers.PropertiesHelper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.io.File;

import com.phucTester.datatest.LoginDataTest.*;
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

    @Test
    public void loginUnsuccessWrongMethod(){
        String fileloginData ="src/test/resources/data/login.json";
        RequestSpecification request = given();
        request.baseUri(ConfigsGlobal.URI)
            .basePath("/login")
            .accept("application/json")
            .contentType("application/json")
            .body(new File(fileloginData));
        Response response = request.when().get();
        Assert.assertEquals(response.getStatusCode(),405,"Status code wrong!");
        JsonPath jsonPath = response.jsonPath();
        String responseMessage = jsonPath.get("message");
        Assert.assertEquals(responseMessage,ConfigsGlobal.messageWrongMethod,"Message not match!");
    }
    @Test
    public void loginUnsuccessUsernameNull(){
        String fileloginData ="src/test/resources/data/login.json";
        RequestSpecification request = given();
        request.baseUri(ConfigsGlobal.URI)
            .basePath("/login")
            .accept("application/json")
            .contentType("application/json")
            .body(new File(fileloginData));
        Response response = request.when().post();
        Assert.assertEquals(response.getStatusCode(),422,"Status code not match!");
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.get("message"),"The username field is required.","Message not match!");
        Assert.assertEquals(jsonPath.get("errors.username[0]"),"The username field is required.","error.username not match!");
    }

    @Test
    public void loginUnsuccessUsernameEmpty(){
        RequestSpecification request = given();
        request.baseUri(ConfigsGlobal.URI)
            .basePath("/login")
            .accept("application/json")
            .contentType("application/json")
            .body("{\n" +
                "  \"username\": \"\",\n" +
                "  \"password\": \"Demo@123\"\n" +
                "}");
        Response response = request.when().post();
        Assert.assertEquals(response.getStatusCode(),422,"Satatus code not match!");
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.get("message"),"The username field is required.","Message not match!");
        Assert.assertEquals(jsonPath.get("errors.username[0]"),"The username field is required.","error.username not match!");
    }
    @Test
    public void loginUnsuccessUsernameSpace(){
        RequestSpecification request = given();
        request.baseUri(ConfigsGlobal.URI)
            .basePath("/login")
            .accept("application/json")
            .contentType("application/json")
            .body("{\n" +
                "  \"username\": \" \",\n" +
                "  \"password\": \"Demo@123\"\n" +
                "}");
        Response response = request.when().post();
        Assert.assertEquals(response.getStatusCode(),422,"Satatus code not match!");
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.get("message"),"The username field is required.","Message not match!");
        Assert.assertEquals(jsonPath.get("errors.username[0]"),"The username field is required.","error.username not match!");
    }
    @Test
    public void loginUnsuccessUsernameNotExsitUser(){
        String fileloginData ="src/test/resources/data/login.json";
        String requestBody = PropertiesHelper.getJsonValue(fileloginData, "unsuccessUsernameExist");
        RequestSpecification request = given();
        request.baseUri(ConfigsGlobal.URI)
            .basePath("/login")
            .accept("application/json")
            .contentType("application/json")
            .body(requestBody);
        Response response = request.when().post();
        Assert.assertEquals(response.getStatusCode(),200,"Satatus code not match!");
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.get("message"),"Login failed","Message not match!");
        Assert.assertEquals(jsonPath.get("errors"),"User name not found","Error not match!");
    }
}
