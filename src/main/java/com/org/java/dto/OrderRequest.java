package com.org.java.dto;

import com.org.java.entity.Employee;
import com.org.java.relation.Company;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderRequest {

    private Company company;
}
