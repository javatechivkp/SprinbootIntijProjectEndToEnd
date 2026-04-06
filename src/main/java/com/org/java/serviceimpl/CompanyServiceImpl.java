package com.org.java.serviceimpl;

import com.org.java.relation.Company;
import com.org.java.repository.CompanyRepository;
import com.org.java.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Company addCompanyDetails(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public List<Company> fetchAllCompanyDetails() {
        List<Company> list=  companyRepository.findAll();
        return list;
    }
}
