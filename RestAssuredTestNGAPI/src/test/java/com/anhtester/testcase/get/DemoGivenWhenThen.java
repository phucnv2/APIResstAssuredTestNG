package com.anhtester.testcase.get;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DemoGivenWhenThen {
    @Test
    public void testGetUsers() {
        given().baseUri("https://api.anhtester.com/api")
            .accept("application/json")
            .basePath("/users")
            .when().get()
            .then().statusCode(200).contentType(ContentType.JSON);

    }

    @Test
    public void testGetAllUsers() {
        //Khai báo đối tượng request để thiết lập điều kiện đầu vào
        //Dùng given() chỉ thị sự thiết lập sẵn điều kiện
        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api");
        request.basePath("/users");
        request.header("accept", "application/json");
        //Khai báo đối tượng response để nhận giá trị trả về từ hàm when() làm gì đó
        //Cụ thể thì chúng ta dùng phương thức GET với hàm get() một endpoint
        Response response = request.when().get();
        //In ra giá trị của response nhận về
        //prettyPrint() là in với nội dung body đã format đẹp mắt
        response.prettyPrint();
    }

    @Test
    public void testGetUserSParam() {
        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api");
        request.basePath("/user");
        request.queryParam("username", "anhtester");

        Response response = request.when().get();
        //In ra giá trị của response nhận về
        //prettyPrint() là in với nội dung body đã format đẹp mắt
        response.prettyPrint();

        response.then().statusCode(200);
        response.then().body("response.username", equalTo("anhtester"));
        response.then().body("response.email", equalTo("anhtester@example.com"));
        response.then().body("response.phone", equalTo("0939206009"));
    }

    @Test
    public void testVerifyResponseUseAssertTestNG() {
        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
            .accept("application/json");

        int id = 1112; //ID của book. Gắn vào sau path url luôn

        Response response = request.when().get("/book/" + id);
        response.prettyPrint();

        //Verify kết quả từ response với Assert trong TestNG
        //Dùng class Assert chấm gọi 2 hàm chính là assertEquals và assertTrue
        Assert.assertEquals(response.getStatusCode(), 200, "Status Code chưa đúng.");
        Assert.assertEquals(response.getContentType(), "application/json", "Content Type chưa đúng.");

        //Muốn lấy giá trị từng key trong JSON body để compare chính xác thì dùng JsonPath
        JsonPath jsonPath = response.jsonPath(); //Lưu hết body vào đối tượng jsonPath

        //Truy xuất giá trị theo key hoặc đường dẫn xpath theo cấp bậc
        String name = jsonPath.get("response.name");
        System.out.println("Name: " + name);
        //Dùng Assert của TestNG để verify
        Assert.assertEquals(name.contains("book922367707"), true, "Name không tồn tại.");
        //Khi lấy trực tiếp giá trị từ jsonPath thì cần toString
        //và phải chuyển số về sạng chuỗi để so sánh
        Assert.assertEquals(jsonPath.get("response.price").toString(), "1200", "Price không đúng.");

        //Lấy đường dẫn path thứ 2 trong mảng của object "image"
        //Index bắt đầu tính từ 0
        String imagePath2 = jsonPath.get("response.image[1].path");
        System.out.println(imagePath2);
        Assert.assertTrue(imagePath2.contains("public/images/TwSX1W1"), "Không đúng hình ảnh thứ 2.");
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
