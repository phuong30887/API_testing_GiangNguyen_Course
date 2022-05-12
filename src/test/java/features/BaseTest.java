package features;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.jupiter.api.BeforeAll;
import utilites.AppProperty;

import static io.restassured.config.EncoderConfig.encoderConfig;
import static utilites.AppProperty.BASE_URL;
import static utilites.LoadConfig.CONFIG;

public class BaseTest {
    @BeforeAll
    static void beforeAll() {
        // phần này để pass qua https certificate khi link là http
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.defaultParser = Parser.JSON;
        // filter cho restassured
        RestAssured.replaceFiltersWith(new AllureRestAssured());
        // config để default content sẽ là UTF-8 cho trường hợp dùng cho tiếng việt
        RestAssured.config=RestAssured.config()
                .encoderConfig(encoderConfig().defaultContentCharset("UTF-8"));
        // config cho default URI phù hợp cho tất cả môi trường : QA, ACC, Pro...
        RestAssured.baseURI = CONFIG.getProperty(BASE_URL);

    }

}
