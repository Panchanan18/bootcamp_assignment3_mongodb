package com.springboot.reactive.asssignment3.entity;

//import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private int empId;
    private String empName;
    private String empCity;
    private String empPhone;
    private double javaExp;
    private double springExp;

}
