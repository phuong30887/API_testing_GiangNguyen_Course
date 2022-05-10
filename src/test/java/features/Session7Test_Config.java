package features;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static utilites.AppProperty.*;
import static utilites.LoadConfig.CONFIG;

@Tag("7")
public class Session7Test_Config extends BaseTest{
    @Test
    void test1() {
        System.out.println(System.getProperty("env"));  // env trong file pom
        System.out.println(CONFIG.getProperty("baseUrl"));
        System.out.println(CONFIG.getProperty("admin-account"));
        System.out.println(CONFIG.getProperty("editor-account"));

        System.out.println(System.getProperty("env"));  // env trong file pom
        System.out.println(CONFIG.getProperty(BASE_URL));
        System.out.println(CONFIG.getProperty(ADMIN_ACCOUNT));
        System.out.println(CONFIG.getProperty(EDITOR_ACCOUNT));

    }
}
