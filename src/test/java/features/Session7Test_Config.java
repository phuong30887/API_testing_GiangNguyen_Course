package features;

import api.bookstore.GetBookstore;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static api.authentication.PostLogin.ADMIN_TOKEN;
import static api.authentication.PostLogin.EDITOR_TOKEN;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static utilites.AppProperty.*;
import static utilites.LoadConfig.CONFIG;

@Tag("7")
public class Session7Test_Config extends BaseTest{

    GetBookstore getBookstore = new GetBookstore();


    @Test
    void test1() {
//        getBookstore.getTBookstore(ADMIN_TOKEN);
    }

    @Test
    void test2() {
        Response bookstore = getBookstore.getTBookstore(ADMIN_TOKEN);
        assertThat(bookstore.statusCode()).isEqualTo(200);
        List<String> author = JsonPath.read(bookstore.asString(), "$..book[?(@.title == 'Moby Dick')].author");
        assertThat(author).isEqualTo (Arrays.asList("Herman Melville"));
    }


}
