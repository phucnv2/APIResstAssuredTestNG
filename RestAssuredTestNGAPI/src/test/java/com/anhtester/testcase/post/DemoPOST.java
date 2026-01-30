package com.anhtester.testcase.post;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DemoPOST {
    @Test
    public void testLoginUser() {
        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
            .accept("application/json")
            .contentType("application/json")
            .body("{\n" +
                "  \"username\": \"anhtester\",\n" +
                "  \"password\": \"Demo@123\"\n" +
                "}");

        //Thực hiện phương thức post() để gửi dữ liệu đi
        Response response = request.when().post("/login");
        response.prettyPrint();

        response.then().statusCode(200);
    }
    @Test
    public void testRegisterUser() {
        String username = "phucnv05";
        String firstName = "nguyen";
        String lastName = "phuc";
        String email = username + "@gmail.com";
        String password = "phucnv04";
        String phone = "0989876767";
        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
            .accept("application/json")
            .contentType("application/json")
            .body("{\n" +
                " \"username\": \" " + username + "\", \n" +
                "  \"firstName\": \" " + firstName + "\",\n" +
                "  \"lastName\": \" " + lastName + "\",\n" +
                "  \"email\": \" " + email + "\",\n" +
                "  \"password\": \" " + password + "\",\n" +
                "  \"phone\": \" " + phone + "\",\n" +
                "  \"userStatus\": 1\n" +
                "}");

        //Thực hiện phương thức post() để gửi dữ liệu đi
        Response response = request.when().post("/register");
        response.prettyPrint();
        response.then().statusCode(200);

        JsonPath jsonPath = response.jsonPath();
        jsonPath.get("response.username");
        Assert.assertEquals(jsonPath.get("response.username"), username, "The user name not match!");
        Assert.assertEquals(jsonPath.get("response.firstName"), firstName, "The first name not match!");
        Assert.assertEquals(jsonPath.get("response.lastName"), lastName, "The last name not match!");
        Assert.assertEquals(jsonPath.get("response.email"), email, "The email not match!");
        Assert.assertEquals(jsonPath.get("response.phone"), phone, "The phone not match!");
    }
}
