package features;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import model.AccountObject;
import model.Employee;
import model.Employee2;
import model.Employee3;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Session3Test_Build_Object_Body {
    // object in object
    @Test
    void test1() {
        Employee employee = new Employee();
        employee.setAccount(new AccountObject());
        employee.setColors(Arrays.asList("red", "blue"));
        employee.setStar(4.5);

        RestAssured.given()
                .log().body(true)
                .contentType(ContentType.JSON)
                .body(employee)
                .post("https://postman-echo.com/post");
    }

    // object in object using Map to create data for body
    @Test
    void test2() {
        Map<String, Object> account = new HashMap<>();
        account.put("name", "Phương");
        account.put("age", 11);
        account.put("is_admin", true);

        Employee2 employee2 = new Employee2();
        employee2.setAccount(account);
        employee2.setColors(Arrays.asList("red", "blue"));
        employee2.setStar(4.5);

        RestAssured.given()
                .log().body(true)
                .contentType(ContentType.JSON)
                .body(employee2)
                .post("https://postman-echo.com/post");
    }
    // object in object using Map to create data for 2 kind of object.
    @Test
    void test3() {
        Map<String, Object> account = new HashMap<>();
        // Kind 1: object is personal:
//        account.put("name", "Phương");
//        account.put("age", 11);
//        account.put("is_admin", true);

        // Kind 2: object is enterprise
        account.put("company name", "Niteco");
        account.put("company address", "156 xa dan 2, Nam Dong, HN");


        Employee3 employee3 = new Employee3();
        employee3.setData(account);
        employee3.setString("Hello");

        RestAssured.given()
                .log().body(true)
                .contentType(ContentType.JSON)
                .body(employee3)
                .post("https://postman-echo.com/post");
    }
}
