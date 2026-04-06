package com.org.java.service;

import com.org.java.relation.Company;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompanyService {

    Company addCompanyDetails(Company company);

    List<Company> fetchAllCompanyDetails();
}
