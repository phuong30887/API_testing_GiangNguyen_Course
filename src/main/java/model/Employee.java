package model;

import lombok.Data;

import java.util.List;

@Data
public class Employee {
    private AccountObject account;
    private List<String> colors;
    private double star;
}
