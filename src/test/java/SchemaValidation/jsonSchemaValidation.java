package SchemaValidation;

import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.matcher.RestAssuredMatchers.matchesXsd;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

public class jsonSchemaValidation {

    @Test
    public void jsonSchema(){

        File file = new File("resources/schema1.json");

        given()
                .baseUri("http://data.fixer.io/api")
                .queryParam("access_key", "b63ddb3fa6c929059cb35899e818bef9")
                .queryParam("Symbols", "USD")
                .when()
                .get("/latest")
                .then().log().all()
                .body(matchesJsonSchema(file));

    }

    @Test
    public void xmlSchema(){

//        DTD = old, simple, limited.
//        XSD/SSD = modern, powerful, preferred in real-world XML-based systems.

        File file = new File("resources/xmlSchema1.xsd");

        given()
                .baseUri("https://api.openweathermap.org/data/2.5")
                .queryParam("APPID", "8f5d911d86200c6e4d30a9e8d3877fe1")
                .queryParam("q", "London,uk")
                .queryParam("mode", "xml").
                when()
                .get("/weather").
                then()
                .log().all()
                .body(matchesXsd(file))
                .statusCode(200);
    }

}
