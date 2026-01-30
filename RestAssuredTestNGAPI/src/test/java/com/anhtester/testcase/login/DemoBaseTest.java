package com.anhtester.testcase.login;

import com.anhtester.data.UserPOJO_Lombok_Builder;
import com.anhtester.model.LoginPOJO_Lombok;
import com.anhtester.model.RegisterUserPOJO_Lombok;
import com.google.gson.Gson;
import globals.ConfigsGlobal;
import globals.TokenGlobal;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.datafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class DemoBaseTest extends BaseTest {
    @Test
    public void testUpdateUser_PATCH() {

//        Faker faker = new Faker(new Locale("vi"));
//        String phoneNumber = faker.phoneNumber().cellPhone();
//        phoneNumber = phoneNumber.replace(" ", "");
//
//        RegisterUserPOJO_Lombok registerUserPOJO_lombok = new RegisterUserPOJO_Lombok();
//        registerUserPOJO_lombok.setFirstName(faker.name().firstName());
//        registerUserPOJO_lombok.setLastName(faker.name().lastName());
//        registerUserPOJO_lombok.setEmail(faker.internet().emailAddress());
//        registerUserPOJO_lombok.setPhone(phoneNumber);
//        registerUserPOJO_lombok.setUserStatus(0);
        RegisterUserPOJO_Lombok registerUserPOJO_lombok = UserPOJO_Lombok_Builder.getUserData();

        Gson gson = new Gson();

        RequestSpecification request = given();
        request.baseUri(ConfigsGlobal.URI)
            .accept("application/json")
            .contentType("application/json")
            .header("Authorization", "Bearer " + TokenGlobal.TOKEN)
            .body(gson.toJson(registerUserPOJO_lombok));

        Response response = request.when().patch("/user/63");
        response.prettyPrint();

        response.then().statusCode(200);

        String message = response.getBody().path("message");
        Assert.assertEquals(message, "Success", "The message not match.");
    }
}
