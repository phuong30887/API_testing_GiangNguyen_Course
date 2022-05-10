package features;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Session4Test_GPath_Hamcrest {
    @Test
    void test1() {
        RestAssured.given().contentType(ContentType.JSON)
                .body(new File("src/test/resources/lotto.json"))
                .post("https://postman-echo.com/post")
                .then()
                .rootPath("json")
                .body("lotto.lottoId", equalTo(5))
        ;
    }

    @Test
    void test2() {
        RestAssured.given().contentType(ContentType.JSON)
                .body(new File("src/test/resources/lotto.json"))
                .post("https://postman-echo.com/post")
                .then()
                .rootPath("json")
                .body("lotto.winners[0].winnerId", equalTo(23))
        ;
    }
    @Test
    void test3() {
        RestAssured.given().contentType(ContentType.JSON)
                .body(new File("src/test/resources/lotto.json"))
                .post("https://postman-echo.com/post")
                .then()
                .rootPath("json")
                .body("lotto.winners[-1].winnerId", equalTo(23))
        ;
    }

    @Test
    void test4() {
        RestAssured.given().contentType(ContentType.JSON)
                .body(new File("src/test/resources/books.json"))
                .post("https://postman-echo.com/post")
                .then()
                .rootPath("json")
                .body("store.book[1].author", equalTo("Nigel Rees"))
        ;
    }

    @Test
    void test5() {
        RestAssured.given().contentType(ContentType.JSON)
                .body(new File("src/test/resources/lotto.json"))
                .post("https://postman-echo.com/post")
                .then()
                .rootPath("json")
                .body("lotto.winning-numbers", hasItems(2,3,5))
        ;
    }

    @Test
    void test6() {
        RestAssured.given().contentType(ContentType.JSON)
                .body(new File("src/test/resources/lotto.json"))
                .post("https://postman-echo.com/post")
                .then()
                .rootPath("json")
                .body("lotto.winning-numbers", equalTo(Arrays.asList(2,45,34,23,7,5,3)))
        ;
    }

    @Test
    void test7() {
        RestAssured.given().contentType(ContentType.JSON)
                .body(new File("src/test/resources/lotto.json"))
                .post("https://postman-echo.com/post")
                .then()
                .rootPath("json")
                .body("lotto.winning-numbers", containsInAnyOrder(2,45,34,23,7,5,3))
        ;
    }

    @Test
    void test8() {
        int lottoId = RestAssured.given().contentType(ContentType.JSON)
                .body(new File("src/test/resources/lotto.json"))
                .post("https://postman-echo.com/post")
                .path("json.lotto.lottoId")
        ;
//        System.out.println("Lotto ID: " + lottoId);

        assertThat(lottoId, equalTo(4));
    }

    @Test
    void test9() {
        List<Integer> winningNumbers = RestAssured.given().contentType(ContentType.JSON)
                .body(new File("src/test/resources/lotto.json"))
                .post("https://postman-echo.com/post")
                .path("json.lotto.winning-numbers");
        assertThat(winningNumbers , containsInAnyOrder(2,45,34,23,7,5,3));
    }

    @Test
    void test10() {
        String author = RestAssured.given().contentType(ContentType.JSON)
                .body(new File("src/test/resources/books.json"))
                .post("https://postman-echo.com/post")
                .path("json.store.book[1].author");
        assertThat(author , equalTo("Evelyn Waugh"));
    }

    // lấy ra toàn bộ content của file json : thực chất nó là Map, bên trong chứa các String, nếu mình ko biết
    // đằng sau nó là gì thì để ? hoặc để là object đều được : List<Map<String, ?>> hoặc List<Map<String, Object>>
    @Test
    void test11() {
        List<Map<String, Object>> author = RestAssured.given().contentType(ContentType.JSON)
                .body(new File("src/test/resources/books.json"))
                .post("https://postman-echo.com/post")
                .path("json.store.book");

        //author.forEach( System.out::println);

        // lấy ra price của author đầu tiên:
        float price = (float) author.get(0).get("price");
        assertThat(price, equalTo(8.95F));
    }

    @Test
    void test12() {
        String res = RestAssured.given().contentType(ContentType.JSON)
                .body(new File("src/test/resources/books.json"))
                .post("https://postman-echo.com/post")
                .asString();
        List<String> author = JsonPath.from(res).getList("json.store.book.findAll {it}.author");
//        author.forEach(System.out::println);
        assertThat(author, hasItems("Nigel Rees", "Evelyn Waugh"));
    }

    @Test
    void test13() {
        String res = RestAssured.given().contentType(ContentType.JSON)
                .body(new File("src/test/resources/books.json"))
                .post("https://postman-echo.com/post")
                .asString();
        List<String> author = JsonPath.from(res).getList("json.store.book.findAll {it.price == 8.95}.author");
//        author.forEach(System.out::println);
        everyItem(equalTo("Nigel Rees"));
    }

}
