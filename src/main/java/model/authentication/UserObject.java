package model.authentication;

import lombok.Data;
import utilites.AppProperty;

import static utilites.AppProperty.ADMIN_ACCOUNT;
import static utilites.AppProperty.EDITOR_ACCOUNT;
import static utilites.LoadConfig.CONFIG;

@Data
public class UserObject {
        private String username;
        private String password;

    public UserObject() {
        this.username = "phuongNguyen";
        this.password = "1234567890";
    }

    public UserObject(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static UserObject admin(){
        //String username = CONFIG.getProperty("admin-account");
        String username = CONFIG.getProperty(ADMIN_ACCOUNT);
        return new UserObject(username, "1234");
    }
    public static UserObject editor(){
        String username = CONFIG.getProperty(EDITOR_ACCOUNT);
        return new UserObject(username, "1234");
    }
}
