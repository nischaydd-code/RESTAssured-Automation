package Basics;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.ExpectedMobiles;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class countriesGetCall {
    @Test
    public void simple_get_request() {
        given()
                .baseUri("https://api.restful-api.dev")
                .when()
                .get("/objects")
                .then()
                .statusCode(200);

    }

    @Test
    public void validate_json_simple_body(){

        given()
                .baseUri("https://api.restful-api.dev")
                .when()
                .get("/objects")
                .then()
                .body(
                        "[0].name", equalTo("Google Pixel 6 Pro"),
                        "[1].name", equalTo("Apple iPhone 12 Mini, 256GB, Blue")

                );

    }

    @Test
    public void validate_mobile_present(String mobile){
        List<String> expectedMobiles = ExpectedMobiles.getMobiles();

        Response response =
                given()
                        .baseUri("https://api.restful-api.dev")
                        .when()
                        .get("/objects")
                        .then()
                        .statusCode(200).extract().response();

        List<String> allNames = response.jsonPath().getList("name");

        Assert.assertEquals(true, allNames.containsAll(expectedMobiles));

    }

    @Test
    public void validate_xml_openweathermap(){

//        API Details:
//        API Key : 13cc652c59de594d60972a0c9e33b905
//        https://api.openweathermap.org/data/2.5/weather?q=London,uk&appid=13cc652c59de594d60972a0c9e33b905&mode=xml


//        Response response =
        given()
                .baseUri("https://api.openweathermap.org/data/2.5")
                .queryParam("q", "London,uk")
                .queryParam("appid", "13cc652c59de594d60972a0c9e33b905")
                .queryParam("mode", "xml")
                .when()
                .get("/weather")
                .then()
                .statusCode(200)
                .body("current.city.@name", equalTo("London"));
//                .extract().response();
//
//        System.out.println(response.xmlPath().getString("current.city.@name"));

    }

    @Test
    public void extract_response_openweathermap() {

String temp =
                given()
                        .baseUri("https://api.openweathermap.org/data/2.5")
                        .queryParam("q", "London,uk")
                        .queryParam("appid", "13cc652c59de594d60972a0c9e33b905")
                        .queryParam("mode", "xml")
                        .when()
                        .get("/weather")
                        .then()
                        .statusCode(200)
                        .extract().path("current.temperature.@value");

        System.out.println(temp);

    }

    @Test
    public void verifyStatusLine(){
        given()
                .baseUri("https://api.openweathermap.org/data/2.5")
                .queryParam("q", "London,uk")
                .queryParam("appid", "13cc652c59de594d60972a0c9e33b905")
                .queryParam("mode", "xml")
                .when()
                .get("/weather")
                .then()
                .statusLine("HTTP/1.1 200 OK");
    }

}
