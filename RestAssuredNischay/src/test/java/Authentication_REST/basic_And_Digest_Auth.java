package Authentication_REST;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class basic_And_Digest_Auth {

    @Test
    public void basic_authentication(){
//        https://postman-echo.com
//        digest-auth

        given()
                .baseUri("https://postman-echo.com")
                .auth().digest("postman", "password")
                .when()
                .get("digest-auth")
                .then().log().all()
                .statusCode(200);

    }


}
