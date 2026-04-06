package com.org.java.controller;

import com.org.java.relation.Company;
import com.org.java.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping("/add")
    public ResponseEntity<Company> addCompanyDetails(@RequestBody Company company) {
        Company list = companyService.addCompanyDetails(company);
        return new ResponseEntity<>(list, HttpStatus.CREATED);
    }
    @GetMapping("/findAll")
    public ResponseEntity<List<Company>> fetchAllCompanyDetails() {
        List<Company> list = companyService.fetchAllCompanyDetails();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
