package model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Employee2 {
    private Map<String, Object> account;
    private List<String> colors;
    private double star;
}
