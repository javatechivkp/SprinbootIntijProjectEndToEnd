package com.org.java.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class EmployeeDto {

    private Long empid;
    private String name;
    private int age;
    private double salary;
    private String email;
    private String workLocation;
    private String platform;
    private String projectName;
    private Long addharNumber;
    private String panNumber;
    private Long mobbileNumber;

}
