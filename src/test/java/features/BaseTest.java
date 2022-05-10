package features;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.config.EncoderConfig.encoderConfig;
import static utilites.LoadConfig.CONFIG;

public class BaseTest {
    @BeforeAll
    static void beforeAll() {
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.defaultParser = Parser.JSON;
        RestAssured.replaceFiltersWith(new AllureRestAssured());
        RestAssured.config=RestAssured.config()
                .encoderConfig(encoderConfig().defaultContentCharset("UTF-8"));
        //RestAssured.baseURI = CONFIG.getProperty("baseURL");

    }

}
