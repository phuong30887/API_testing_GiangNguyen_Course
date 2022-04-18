package features;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

public class Session1Test {

    @Test
    void test1() {
        RestAssured.given()
                .get("https://postman-echo.com/get?foo1=bar1&foo2=bar2")
                .prettyPrint();
        System.out.println("===================");
    }

    @Test
    void test2() {
        RestAssured.given()
                .get("https://restful-booker.herokuapp.com/booking/2")
                .prettyPrint();
        System.out.println("===================");
    }

    @Test
    void test3() {
        RestAssured.given()
                .get("https://restful-booker.herokuapp.com/booking?firstname=Susan&lastname=Jones")
                .prettyPrint();
        System.out.println("===================");
    }

    @Test
    void test4() {
        RestAssured.given()
                .get("https://restful-booker.herokuapp.com/booking?checkin=2015-03-09&checkout=2017-07-27")
                .prettyPrint();
        System.out.println("===================");
    }

}
