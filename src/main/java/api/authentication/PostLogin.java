package api.authentication;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.authentication.UserObject;

public class PostLogin {

    public Response callAPI(UserObject userObject){
        RestAssured.given().contentType(ContentType.JSON)
                .body(userObject)
                .get("https://run.mocky.io/v3/024a97bf-f8a9-47b2-b0ec-e4755a0d0e89");
    }
}
