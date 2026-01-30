package com.anhtester.testcase.login;

import com.anhtester.model.LoginPOJO;
import com.anhtester.model.LoginPOJO_Lombok;
import com.google.gson.Gson;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class loginTestPOJO_lombok {
    @Test
    public void testLoginUser() {

        //Khởi tạo giá trị cho các fields thông qua hàm xây dựng
        LoginPOJO loginPOJO = new LoginPOJO("phucnv06", "Demo@123");

        //Dùng thư viện Gson để chuyển class POJO về dạng JSON
        Gson gson = new Gson();

        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
            .accept("application/json")
            .contentType("application/json")
            .body(gson.toJson(loginPOJO));

        Response response = request.when().post("/login");
//        response.prettyPrint();
        response.then().statusCode(200);
        String token = response.getBody().path("token");
        System.out.println(token);

        LoginPOJO_Lombok loginPOJOLombok1 = new LoginPOJO_Lombok();
        loginPOJOLombok1.getUsername();

        LoginPOJO_Lombok loginPOJOLombok2 = new LoginPOJO_Lombok("","");
    }
}
