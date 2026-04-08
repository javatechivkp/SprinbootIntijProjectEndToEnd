package com.org.java.serviceimpl;

import com.org.java.relation.Company;
import com.org.java.relation.Project;
import com.org.java.repository.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceImplTest {

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private CompanyServiceImpl companyService;

    @Test
    public void addCompanyDetailsTest(){
        Project project1 = new Project();
        Project project2 = new Project();
        Company company = new Company(
                1L,
                "Tech Corp",
                "Hyderabad",
                "tech@corp.com",
                "9876543210",
                "IT",
                2010,
                Arrays.asList(project1, project2)
        );
        when(companyRepository.save(any(Company.class))).thenReturn(company);
        // Act
        Company savedCompany = companyService.addCompanyDetails(company);
        // Assert
        assertNotNull(savedCompany);
        assertEquals("Tech Corp", savedCompany.getCompanyName());
        verify(companyRepository, times(1)).save(company);

    }
}
