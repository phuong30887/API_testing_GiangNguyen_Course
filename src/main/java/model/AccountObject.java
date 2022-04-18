package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AccountObject {
    private String name;
    private int age;
    @JsonProperty("is_admin")
    private boolean isAdmin;

    public AccountObject() {
        this.name = "Phương";
        this.age = 34;
        this.isAdmin = true;
    }
}
