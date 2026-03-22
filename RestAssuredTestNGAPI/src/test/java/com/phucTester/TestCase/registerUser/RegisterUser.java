package com.phucTester.TestCase.registerUser;

import globals.ConfigsGlobal;
import globals.EndPointGlobal;
import helpers.PropertiesHelper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RegisterUser {
    @Test
    public void registerUserSuccess() {
        String fileloginData = "src/test/resources/data/registerUser.json";
        String requestBody = PropertiesHelper.getJsonValue(fileloginData, "success");
        RequestSpecification request = given();
        request.baseUri(ConfigsGlobal.URI)
            .basePath(EndPointGlobal.EP_REGISTER)
            .accept("application/json")
            .contentType("application/json")
            .body(requestBody);
        Response response = request.when().post();
        response.prettyPrint();
        response.then().statusCode(200);
        JsonPath jsonPath = response.jsonPath();
        String getUser = jsonPath.get("response.username");
        String getPassword = PropertiesHelper.getJsonFieldValue(fileloginData, "success", "password");
        System.out.println("--------------------");

        // login sau khi register
        String body = "{\n" +
            "  \"username\": \"" + getUser + "\",\n" +
            "  \"password\": \"" + getPassword + "\"\n" +
            "}";
        RequestSpecification requestLogin = given();
        requestLogin.baseUri(ConfigsGlobal.URI)
            .basePath(EndPointGlobal.EP_REGISTER)
            .accept("application/json")
            .contentType("application/json")
            .body(body);
        Response responseLogin = requestLogin.when().post();
        System.out.println(body);
        responseLogin.prettyPrint();
        responseLogin.then().statusCode(200);
    }
}
