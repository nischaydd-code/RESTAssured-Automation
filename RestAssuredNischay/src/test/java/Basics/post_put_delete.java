package Basics;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class post_put_delete {

//    Data sent using the below POST call is saved in /resources/createEmployee.json
    @Test
    public void post_call() {

        File file = new File("/Users/milliond/IdeaProjects/RestAssuredNischay/resources/createEmployee.json");

        Response response =
        given()
                .baseUri("https://dummy.restapiexample.com/api/v1")
                .contentType(ContentType.JSON)
                .body(file)
                .when()
                .post("/create")
                .then()
                .statusCode(200).extract().response();

        String employeeId = response.jsonPath().getString("data.id");
        System.out.println(employeeId);
        System.out.println(response.getBody().asString());
    }

    @Test
    public void post_call_JsonObject() {
        JSONObject body = new JSONObject();
        body.put("name","asdf");
        body.put("salary","12333");
        body.put("age","27");

        Response response =
                given()
                        .baseUri("https://dummy.restapiexample.com/api/v1")
                        .contentType(ContentType.JSON)
                        .body(body)
                        .when()
                        .post("/create")
                        .then()
                        .statusCode(200)
                        .body("data.name", equalTo("asdf"))
                        .extract().path("data.id");

        String employeeId = response.jsonPath().getString("data.id");
        System.out.println(employeeId);
        System.out.println(response.getBody().asString());
    }


    @Test
    public void put_request_using_jsonObject(){

        JSONObject body = new JSONObject();
        body.put("name", "hello");
        body.put("salary", "1234123");
        body.put("age", "42");

        Response response =
                given()
                        .baseUri("https://dummy.restapiexample.com/api/v1")
                        .contentType(ContentType.JSON)
                        .body(body)
                        .when()
                        .put("/update/21")
                        .then()
                        .statusCode(200).extract().response();

        System.out.println(response.getBody().asString());

    }

    @Test
    public void delete_record(){

       given()
               .baseUri("https://dummy.restapiexample.com/api/v1")
               .contentType(ContentType.JSON)
               .when()
               .put("/delete/21")
               .then()
               .statusCode(200)
               .extract().path("message");

    }


}

