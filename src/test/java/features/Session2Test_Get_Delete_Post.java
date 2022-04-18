package features;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import model.NormalObject;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Session2Test_Get_Delete_Post {

    // =================== GET REQUEST ==========================
    @Test
    void query_params_using_map() {
        Map<String, Object> params = new HashMap<>();
        params.put("foo1", "bar1");
        params.put("foo2", "bar2");

        //https://postman-echo.com/get?foo1=bar1&foo2=bar2
        RestAssured.given()
                .log().all()
                .queryParams(params)
                .get("https://postman-echo.com/get");
    }

    @Test
    void query_params_using_key_value_pairs() {
        RestAssured.given()
                .log().all()
                .queryParams("foo1", "bar1", "foo2", "bar2")
                .get("https://postman-echo.com/get");

    }

    @Test
    void query_params_using_each_query() {
        RestAssured.given()
                .log().all()
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
                .queryParam("foo3", "bar3")
                .get("https://postman-echo.com/get");

    }

    @Test
    void query_params_using_query_collection() {
        RestAssured.given()
                .log().all()
                .queryParam("foo1", Arrays.asList("bar1", "bar2"))
                .get("https://postman-echo.com/get");
    }

    @Test
    void query_params_using_path_param() {
        RestAssured.given()
                .log().all()
                .pathParam("id", 1)
                .pathParam("number", 100)
                .get("https://postman-echo.com/get/{id}/{number}");
    }
    @Test
    void query_params_using_map4() {
        RestAssured.given()
                .log().all()
                .get("https://postman-echo.com/get/{id}/{number}", 100, 50);

    }

    // =================== POST REQUEST ==========================

    @Test
    void normal_objet() {

        NormalObject normalObject = new NormalObject();
        normalObject.setId(1);
        normalObject.setFirstName("Vernon");
        normalObject.setLastName("Harper");
        normalObject.setEmail("egestas.rhoncus.Proin@massaQuisqueporttitor.org");
        normalObject.setProgramme("Financial Analysis");
        normalObject.setCourses(Arrays.asList("Accounting", "Statistics"));

        RestAssured.given()
                .log().all()
                .body(normalObject)
                .contentType(ContentType.JSON)
                .post("https://postman-echo.com/post").prettyPrint();

    }

}
