package Basics;

import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;

import java.util.HashMap;

import static org.hamcrest.Matchers.*;

public class query_params {

    @Test
    public void queryParam() {

        given()
                .baseUri("https://restcountries.com/v3.1")
                .param("status", false)
                .when()
                .get("/independent")
                .then()
                .log().all()
                .statusCode(200);
    }


    @Test
    public void multiple_QueryParams(){
//API Access Key:         b63ddb3fa6c929059cb35899e818bef9
        HashMap<String, Object> params = new HashMap<>();
        params.put("access_key", "b63ddb3fa6c929059cb35899e818bef9");
        params.put("Symbols", "USD");
    given()
            .baseUri("http://data.fixer.io/api/")
            .params(params)
            .when()
            .get("/latest")
            .then()
            .log().all().statusCode(200);
    }

    @Test
    public void path_params(){
        given()
                .baseUri("https://restcountries.eu/rest/v2")
                .pathParam("Country", "US")
                .when()
                .get("/alpha/{Country}")
                .then()
                .log().status();


    }

}
