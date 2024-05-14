package homework6;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;

import static org.testng.Assert.assertEquals;

public class TestHomework {
    @BeforeTest
    public void setup() {
        RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .build();
    }

    @org.testng.annotations.Test
    public void updateEmployee() {
        RequestEmployee body = RequestEmployee.builder()
                .name("Olga Test")
                .salary("100000")
                .age("30")
                .build();

        Response response = RestAssured.given()
                .body(body)
                .put("/update/3");

        response.then().statusCode(200);

        ResponseEmployee responseEmployee = response.as(ResponseEmployee.class);

        assertEquals(responseEmployee.getMessage(), "Successfully! Record has been updated.");
    }

}
