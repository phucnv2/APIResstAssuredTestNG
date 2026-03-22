package com.phucTester.TestCase.loginUser;

import com.phucTester.models.request_response.SpecBuilder;
import globals.ConfigsGlobal;
import globals.DataGlobal;
import globals.EndPointGlobal;
import helpers.PropertiesHelper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class Login {
    @Test
    public void loginSuccess() {
//        String fileloginData ="src/test/resources/data/login.json";
//        String requestBody = PropertiesHelper.getJsonValue(fileloginData, "success");
//        RequestSpecification request = given();
//        request.baseUri(ConfigsGlobal.URI)
//            .basePath("/login")
//            .accept("application/json")
//            .contentType("application/json")
//            .body(requestBody);
//        Response response = request.when().post();
//        response.prettyPrint();
//        response.then().statusCode(200);

        given()
            .spec(SpecBuilder.getRequestNotAuthSpecBuilder())
            .body(SpecBuilder.getRequestBody(DataGlobal.LOGIN_DATA, "success"))
            .when()
            .post(EndPointGlobal.EP_LOGIN)
            .then()
            .spec(SpecBuilder.getResponseSpecBuilder())
            .statusCode(200);
    }

    @Test
    public void loginUnsuccessWrongMethod() {
        String fileloginData = "src/test/resources/data/login.json";
        RequestSpecification request = given();
        request.baseUri(ConfigsGlobal.URI)
            .basePath("/login")
            .accept("application/json")
            .contentType("application/json")
            .body(new File(fileloginData));
        Response response = request.when().get();
        Assert.assertEquals(response.getStatusCode(), 405, "Status code wrong!");
        JsonPath jsonPath = response.jsonPath();
        String responseMessage = jsonPath.get("message");
        Assert.assertEquals(responseMessage, ConfigsGlobal.messageWrongMethod, "Message not match!");
    }

    @Test
    public void loginUnsuccessUsernameNull() {
        String fileloginData = "src/test/resources/data/login.json";
        RequestSpecification request = given();
        request.baseUri(ConfigsGlobal.URI)
            .basePath("/login")
            .accept("application/json")
            .contentType("application/json")
            .body("{\n" +
                "  \"username\": null,\n" +
                "  \"password\": \"Demo@123\"\n" +
                "}");
        Response response = request.when().post();
        Assert.assertEquals(response.getStatusCode(), 422, "Status code not match!");
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.get("message"), "The username field is required.", "Message not match!");
        Assert.assertEquals(jsonPath.get("errors.username[0]"), "The username field is required.", "error.username not match!");
    }

    @Test
    public void loginUnsuccessUsernameEmpty() {
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
        Assert.assertEquals(response.getStatusCode(), 422, "Satatus code not match!");
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.get("message"), "The username field is required.", "Message not match!");
        Assert.assertEquals(jsonPath.get("errors.username[0]"), "The username field is required.", "error.username not match!");
    }

    @Test
    public void loginUnsuccessUsernameSpace() {
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
        Assert.assertEquals(response.getStatusCode(), 422, "Satatus code not match!");
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.get("message"), "The username field is required.", "Message not match!");
        Assert.assertEquals(jsonPath.get("errors.username[0]"), "The username field is required.", "error.username not match!");
    }

    @Test
    public void loginUnsuccessUsernameNotExsitUser() {
        String fileloginData = "src/test/resources/data/login.json";
        String requestBody = PropertiesHelper.getJsonValue(fileloginData, "unsuccessUsernameExist");
        RequestSpecification request = given();
        request.baseUri(ConfigsGlobal.URI)
            .basePath("/login")
            .accept("application/json")
            .contentType("application/json")
            .body(requestBody);
        Response response = request.when().post();
        Assert.assertEquals(response.getStatusCode(), 200, "Satatus code not match!");
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.get("message"), "Login failed", "Message not match!");
        Assert.assertEquals(jsonPath.get("errors"), "User name not found", "Error not match!");
    }

    @Test
    public void loginUnsuccessPasswordNull() {
        String fileloginData = "src/test/resources/data/login.json";
        String requestBody = PropertiesHelper.getJsonValue(fileloginData, "unsuccessPasswordNull");
        RequestSpecification request = given();
        request.baseUri(ConfigsGlobal.URI)
            .basePath("/login")
            .accept("application/json")
            .contentType("application/json")
            .body(requestBody);
        Response response = request.when().post();
        Assert.assertEquals(response.getStatusCode(), 422, "Satatus code not match!");
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.get("message"), "The password field is required.", "Message not match!");
        Assert.assertEquals(jsonPath.get("errors.password[0]"), "The password field is required.", "Error not match!");
    }

    @Test
    public void loginUnsuccessPasswordEmpty() {
        String fileloginData = "src/test/resources/data/login.json";
        String requestBody = PropertiesHelper.getJsonValue(fileloginData, "unsuccessPasswordEmpty");
        RequestSpecification request = given();
        request.baseUri(ConfigsGlobal.URI)
            .accept("application/json")
            .contentType("application/json")
            .basePath("/login")
            .body(requestBody);
        Response response = request.when().post();
        Assert.assertEquals(response.getStatusCode(), 422, "Satatus code not match!");
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(response.path("message"), "The password field is required.", "Message not match!");
        Assert.assertEquals(jsonPath.get("errors.password[0]"), "The password field is required.", "Error not match!");
    }

    @Test
    public void loginUnsuccessPasswordSpace() {
        String fileloginData = "src/test/resources/data/login.json";
        String requestBody = PropertiesHelper.getJsonValue(fileloginData, "unsuccessPasswordSpace");
        RequestSpecification request = given();
        request.baseUri(ConfigsGlobal.URI)
            .basePath("/login")
            .accept("application/json")
            .contentType("application/json")
            .body(requestBody);
        Response response = request.when().post();
        Assert.assertEquals(response.getStatusCode(), 422, "Satatus code not match!");
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.get("message"), "The password field is required.", "Message not match!");
        Assert.assertEquals(jsonPath.get("errors.password[0]"), "The password field is required.", "Error not match!");
    }

    @Test
    public void loginUnsuccessPasswordWrong() {
        String fileloginData = "src/test/resources/data/login.json";
        String requestBody = PropertiesHelper.getJsonValue(fileloginData, "unsuccessPasswordWrong");
        RequestSpecification request = given();
        request.baseUri(ConfigsGlobal.URI)
            .basePath("/login")
            .accept("application/json")
            .contentType("application/json")
            .body(requestBody);
        Response response = request.when().post();
        Assert.assertEquals(response.getStatusCode(), 200, "Satatus code not match!");
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.get("message"), "Login failed", "Message not match!");
        Assert.assertEquals(jsonPath.get("errors"), "Password is incorrect", "Error not match!");
    }

    @Test
    public void loginUnsuccessUsernamePasswordNull() {
        String fileloginData = "src/test/resources/data/login.json";
        String requestBody = PropertiesHelper.getJsonValue(fileloginData, "UsernamePasswordNull");
        RequestSpecification request = given();
        request.baseUri(ConfigsGlobal.URI)
            .basePath("/login")
            .accept("application/json")
            .contentType("application/json")
            .body(requestBody);
        Response response = request.when().post();
        Assert.assertEquals(response.getStatusCode(), 422, "Satatus code not match!");
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.get("message"), "The username field is required. (and 1 more error)", "Message not match!");
        Assert.assertEquals(jsonPath.get("errors.username[0]"), "The username field is required.", "error.username not match!");
        Assert.assertEquals(jsonPath.get("errors.password[0]"), "The password field is required.", "error.password not match!");
    }

    @Test
    public void loginUnsuccessUsernamePasswordSpace() {
        String fileloginData = "src/test/resources/data/login.json";
        String requestBody = PropertiesHelper.getJsonValue(fileloginData, "UsernamePasswordSpace");
        RequestSpecification request = given();
        request.baseUri(ConfigsGlobal.URI)
            .basePath("/login")
            .accept("application/json")
            .contentType("application/json")
            .body(requestBody);
        Response response = request.when().post();
        Assert.assertEquals(response.getStatusCode(), 422, "Satatus code not match!");
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.get("message"), "The username field is required. (and 1 more error)", "Message not match!");
        Assert.assertEquals(jsonPath.get("errors.username[0]"), "The username field is required.", "error.username not match!");
        Assert.assertEquals(jsonPath.get("errors.password[0]"), "The password field is required.", "error.password not match!");
    }

    @Test
    public void loginUnsuccessUsernamePasswordEmpty() {
        String fileloginData = "src/test/resources/data/login.json";
        String requestBody = PropertiesHelper.getJsonValue(fileloginData, "UsernamePasswordEmpty");
        RequestSpecification request = given();
        request.baseUri(ConfigsGlobal.URI)
            .basePath("/login")
            .accept("application/json")
            .contentType("application/json")
            .body(requestBody);
        Response response = request.when().post();
        Assert.assertEquals(response.getStatusCode(), 422, "Satatus code not match!");
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.get("message"), "The username field is required. (and 1 more error)", "Message not match!");
        Assert.assertEquals(jsonPath.get("errors.username[0]"), "The username field is required.", "error.username not match!");
        Assert.assertEquals(jsonPath.get("errors.password[0]"), "The password field is required.", "error.password not match!");
    }

    @Test
    public void loginUnsuccessUsernamePasswordWrong() {
        String fileloginData = "src/test/resources/data/login.json";
        String requestBody = PropertiesHelper.getJsonValue(fileloginData, "UsernamePasswordWrong");
        RequestSpecification request = given();
        request.baseUri(ConfigsGlobal.URI)
            .basePath("/login")
            .accept("application/json")
            .contentType("application/json")
            .body(requestBody);
        Response response = request.when().post();
        Assert.assertEquals(response.getStatusCode(), 200, "Satatus code not match!");
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.get("message"), "Login failed", "Message not match!");
        Assert.assertEquals(jsonPath.get("errors"), "User name not found", "error.username not match!");
    }
}
