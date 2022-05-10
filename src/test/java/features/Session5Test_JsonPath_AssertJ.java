package features;


import com.jayway.jsonpath.JsonPath;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("5")
public class Session5Test_JsonPath_AssertJ {

    static String response;

    @BeforeAll
    static void setUp() {
        RestAssured.filters(new AllureRestAssured());
        response = get("https://run.mocky.io/v3/060803e9-f05f-420e-97ad-49a1dba5cd5a").asString();
    }

    // extract 1 giá trị
    @Test
    void test1() {

        // biểu tượng $ là root.
        String author = JsonPath.parse(response).read("$.store.book[0].author");
        String category = JsonPath.parse(response).read("$.store.book[0].category");
        double price = JsonPath.parse(response).read("$.store.book[0].price");
        String title = JsonPath.parse(response).read("$.store.book[0].title");

        /*System.out.println("author = " + author);
        System.out.println("category = " + category);
        System.out.println("price = " + price);
        System.out.println("title = " + title);*/

        assertThat(author).isEqualToIgnoringCase("Nigel Rees");
        assertThat(category).isEqualToIgnoringCase("reference");
        assertThat(price).isEqualTo(8.95);
        assertThat(title).isEqualToIgnoringCase("Sayings of the Century");
    }

    // extract danh sách các authors
    @Test
    void test2() {

        // biểu tượng $ là root.
        List<String> authors = JsonPath.read(response, "$.store.book[*].author");
//        authors.forEach(System.out::println);
        assertThat(authors).as("********* all authors: ").contains("Herman Melville", "abc");

    }

    // extract ra toàn bộ data của item đầu tiên : get ra 1 object

    @Test
    void test3() {

        Map<String, Object> firstBook = JsonPath.read(response, "$.store.book[0]");
//        System.out.println(firstBook);
        assertThat(firstBook.get("author")).isEqualTo("Nigel Rees");
        assertThat(firstBook.get("category")).isEqualTo("reference");
        assertThat(firstBook.get("price")).isEqualTo(8.95);
        assertThat(firstBook.get("title")).isEqualTo("Sayings of the Century");
    }

    @Test
    void test4() {
        List<String> title = JsonPath.read(response, "$..book[?(@.price == 12.99)].title");
        //title.forEach(System.out::println);
    }

    @Test
    void test5() {
        List<String> titles = JsonPath.read(response, "$..book[*].title");
        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(titles).as("Title contains a ").allMatch(title -> title.contains("a"));
        soft.assertThat(titles).as("Title contains o ").allMatch(title -> title.contains("o"));
        soft.assertAll();
    }

    @Test
    void test6() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        given().contentType(ContentType.JSON)
                .body(new File("src/test/resources/books.json"))
                .post("https://postman-echo.com/post")
                .then()
                .statusCode(200);
    }

}
