package com.org.java.serviceimpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.org.java.dto.EmployeeDto;
import com.org.java.entity.Employee;
import com.org.java.exception.NoIdFoundException;
import com.org.java.mapper.EmployeeMapper;
import com.org.java.repository.EmployeeRepository;
import com.org.java.util.EmailSender;
import com.org.java.util.ExcelGenerator;
import com.org.java.util.PdfGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("EmployeeServiceImpl Test Suite")
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmailSender emailSender;

    @Mock
    private PdfGenerator pdfGenerator;

    @Mock
    private ExcelGenerator excelGenerator;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee1;
    private Employee employee2;
    private Employee employee3;
    private EmployeeDto employeeDto1;
    private List<Employee> employeeList;

    @BeforeEach
    public void setUp() {
        // Initialize test data
        employee1 = new Employee();
        employee1.setEmpid(1L);
        employee1.setName("John Doe");
        employee1.setAge(30);
        employee1.setSalary(75000.0);
        employee1.setEmail("john@example.com");
        employee1.setWorkLocation("New York");
        employee1.setPlatform("Java");
        employee1.setProjectName("Project A");
        employee1.setPanNumber("ABC123");
        employee1.setAddharNumber(123456789L);
        employee1.setMobbileNumber(9876543210L);

        employee2 = new Employee();
        employee2.setEmpid(2L);
        employee2.setName("Jane Smith");
        employee2.setAge(28);
        employee2.setSalary(65000.0);
        employee2.setEmail("jane@example.com");
        employee2.setWorkLocation("London");
        employee2.setPlatform("Python");
        employee2.setProjectName("Project B");
        employee2.setPanNumber("XYZ789");
        employee2.setAddharNumber(987654321L);
        employee2.setMobbileNumber(8765432109L);

        employee3 = new Employee();
        employee3.setEmpid(3L);
        employee3.setName("Bob Johnson");
        employee3.setAge(35);
        employee3.setSalary(85000.0);
        employee3.setEmail("bob@example.com");
        employee3.setWorkLocation("New York");
        employee3.setPlatform("Java");
        employee3.setProjectName("Project C");

        employeeList = Arrays.asList(employee1, employee2, employee3);

        employeeDto1 = new EmployeeDto();
        employeeDto1.setEmpid(1L);
        employeeDto1.setName("John Doe");
        employeeDto1.setAge(30);
        employeeDto1.setSalary(75000.0);
    }

    // ==================== CRUD Operations Tests ====================

    @Test
    @DisplayName("Should add employee details successfully")
    public void testAddEmployeeDetails() {
        when(employeeRepository.saveAll(employeeList)).thenReturn(employeeList);

        List<EmployeeDto> result = employeeService.addEmployeeDetails(employeeList);

        assertNotNull(result);
        assertEquals(3, result.size());
        verify(employeeRepository, times(1)).saveAll(employeeList);
    }

    @Test
    @DisplayName("Should update employee details successfully")
    public void testUpdateEmployeeDetails() {
        List<EmployeeDto> employeeDtoList = Arrays.asList(employeeDto1);
        when(employeeRepository.saveAll(any())).thenReturn(Arrays.asList(employee1));

        List<EmployeeDto> result = employeeService.updateEmployeeDetails(employeeDtoList);

        assertNotNull(result);
        verify(employeeRepository, times(1)).saveAll(any());
    }

    @Test
    @DisplayName("Should fetch all employee details")
    public void testFetchAllEmployeeDetails() {
        when(employeeRepository.findAll()).thenReturn(employeeList);

        List<EmployeeDto> result = employeeService.fetchAllEmployeeDetails();

        assertNotNull(result);
        assertEquals(3, result.size());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should find employee by ID successfully")
    public void testFindByEmployeeId_Success() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee1));

        Optional<EmployeeDto> result = employeeService.findByEmployeeId(1L);

        assertTrue(result.isPresent());
        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Should throw exception when employee not found by ID")
    public void testFindByEmployeeId_NotFound() {
        when(employeeRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NoIdFoundException.class, () -> employeeService.findByEmployeeId(99L));
        verify(employeeRepository, times(1)).findById(99L);
    }

    // ==================== Salary-Based Tests ====================

    @Test
    @DisplayName("Should find employee with max salary")
    public void testMaxSalaryEmployeeDetails() {
        when(employeeRepository.findAll()).thenReturn(employeeList);

        EmployeeDto result = employeeService.maxSalaryEmployeeDetails();

        assertNotNull(result);
        assertEquals(85000.0, result.getSalary());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should find max salary using query")
    public void testMaxSalarywithQuery() {
        when(employeeRepository.findByMaxSalary()).thenReturn(employee3);

        EmployeeDto result = employeeService.maxSalarywithQuery();

        assertNotNull(result);
        assertEquals("Bob Johnson", result.getName());
        verify(employeeRepository, times(1)).findByMaxSalary();
    }

    @Test
    @DisplayName("Should find employee with min salary")
    public void testMinSalaryEmployeeDetails() {
        when(employeeRepository.findAll()).thenReturn(employeeList);

        EmployeeDto result = employeeService.minSalaryEmployeeDetails();

        assertNotNull(result);
        assertEquals(65000.0, result.getSalary());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should find second highest salary employee")
    public void testSecondHighestSalaryEmployeeDetails() {
        when(employeeRepository.findAll()).thenReturn(employeeList);

        EmployeeDto result = employeeService.secondHigestSalaryEmployeeDetails();

        assertNotNull(result);
        assertEquals(75000.0, result.getSalary());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should filter employees with salary less than 50000")
    public void testFilterWithLimitSalaryEmployeeDetails() {
        List<Employee> filteredList = new ArrayList<>();
        when(employeeRepository.findAll()).thenReturn(filteredList);

        List<EmployeeDto> result = employeeService.filterWithLimitSalaryEmployeeDetails();

        assertNotNull(result);
        assertEquals(0, result.size());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should find all salaries in ascending order")
    public void testFindAllSalaryAscOrder() {
        when(employeeRepository.findAll()).thenReturn(employeeList);

        List<EmployeeDto> result = employeeService.findAllSalaryAscOrder();

        assertNotNull(result);
        assertEquals(3, result.size());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should find all salaries in descending order")
    public void testFindAllSalaryDscOrder() {
        when(employeeRepository.findAll()).thenReturn(employeeList);

        List<EmployeeDto> result = employeeService.findAllSalaryDscOrder();

        assertNotNull(result);
        assertEquals(3, result.size());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should find count of all salaries")
    public void testFindCountAllSalaries() {
        when(employeeRepository.findAll()).thenReturn(employeeList);

        long count = employeeService.findCountAllSalaries();

        assertEquals(3, count);
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should find sum of all salaries")
    public void testFindSumAllSalaries() {
        when(employeeRepository.findAll()).thenReturn(employeeList);

        double sum = employeeService.findSumAllSalaries();

        assertEquals(225000.0, sum);
        verify(employeeRepository, times(1)).findAll();
    }

    // ==================== Search/Filter Tests ====================

    @Test
    @DisplayName("Should find employees by name")
    public void testFindByName() {
        when(employeeRepository.findByName("John Doe")).thenReturn(Arrays.asList(employee1));

        List<EmployeeDto> result = employeeService.findByName("John Doe");

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(employeeRepository, times(1)).findByName("John Doe");
    }

    @Test
    @DisplayName("Should find employees by name and age")
    public void testFindByNameAndAge() {
        when(employeeRepository.findByNameAndAge("John Doe", 30)).thenReturn(Arrays.asList(employee1));

        List<EmployeeDto> result = employeeService.findByNameAndAge("John Doe", 30);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(employeeRepository, times(1)).findByNameAndAge("John Doe", 30);
    }

    @Test
    @DisplayName("Should find employees by name or age")
    public void testFindByNameOrAge() {
        when(employeeRepository.findByNameOrAge("John Doe", 35)).thenReturn(Arrays.asList(employee1, employee3));

        List<EmployeeDto> result = employeeService.findByNameOrAge("John Doe", 35);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(employeeRepository, times(1)).findByNameOrAge("John Doe", 35);
    }

    @Test
    @DisplayName("Should find employees by age greater than")
    public void testFindByAgeGreaterThan() {
        when(employeeRepository.findByAgeGreaterThan(30)).thenReturn(Arrays.asList(employee3));

        List<EmployeeDto> result = employeeService.findByAgeGreaterThan(30);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(employeeRepository, times(1)).findByAgeGreaterThan(30);
    }

    @Test
    @DisplayName("Should find employees by age less than")
    public void testFindByAgeLessThan() {
        when(employeeRepository.findByAgeLessThan(30)).thenReturn(Arrays.asList(employee2));

        List<EmployeeDto> result = employeeService.findByAgeLessThan(30);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(employeeRepository, times(1)).findByAgeLessThan(30);
    }

    @Test
    @DisplayName("Should find employees by age between range")
    public void testFindByAgeBetween() {
        when(employeeRepository.findByAgeBetween(28, 30)).thenReturn(Arrays.asList(employee1, employee2));

        List<EmployeeDto> result = employeeService.findByAgeBetween(28, 30);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(employeeRepository, times(1)).findByAgeBetween(28, 30);
    }

    @Test
    @DisplayName("Should find employees by name starting with prefix")
    public void testFindByNameStartingWith() {
        when(employeeRepository.findByNameStartingWith("Jo")).thenReturn(Arrays.asList(employee1));

        List<EmployeeDto> result = employeeService.findByNameStartingWith("Jo");

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(employeeRepository, times(1)).findByNameStartingWith("Jo");
    }

    @Test
    @DisplayName("Should find employees by work location")
    public void testFindByWorkLocation() {
        when(employeeRepository.findByWorkLocation("New York")).thenReturn(Arrays.asList(employee1, employee3));

        List<EmployeeDto> result = employeeService.findByWorkLocation("New York");

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(employeeRepository, times(1)).findByWorkLocation("New York");
    }

    @Test
    @DisplayName("Should find employees by salary greater than")
    public void testFindBySalaryGreaterThan() {
        when(employeeRepository.findBySalaryGreaterThan(70000)).thenReturn(Arrays.asList(employee1, employee3));

        List<EmployeeDto> result = employeeService.findBySalaryGreaterThan(70000);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(employeeRepository, times(1)).findBySalaryGreaterThan(70000);
    }

    @Test
    @DisplayName("Should find employees by email")
    public void testFindByEmail() {
        when(employeeRepository.findByEmail("john@example.com")).thenReturn(Arrays.asList(employee1));

        Optional<EmployeeDto> result = employeeService.findByEmail("john@example.com");

        assertTrue(result.isPresent());
        verify(employeeRepository, times(1)).findByEmail("john@example.com");
    }

    // ==================== Sorting Tests ====================

    @Test
    @DisplayName("Should sort employees by ID")
    public void testSortByEmployeeId() {
        when(employeeRepository.findAll()).thenReturn(employeeList);

        List<EmployeeDto> result = employeeService.sortByEmployeeId();

        assertNotNull(result);
        assertEquals(3, result.size());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should sort employees by name ascending")
    public void testSortByEmployeeName() {
        when(employeeRepository.findAll()).thenReturn(employeeList);

        List<EmployeeDto> result = employeeService.sortByEmployeeName();

        assertNotNull(result);
        assertEquals(3, result.size());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should sort employees by name descending")
    public void testSortByEmployeeNameDsc() {
        when(employeeRepository.findAll()).thenReturn(employeeList);

        List<EmployeeDto> result = employeeService.sortByEmployeeNameDsc();

        assertNotNull(result);
        assertEquals(3, result.size());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should sort employees by salary ascending")
    public void testSortByEmployeeSalary() {
        when(employeeRepository.findAll()).thenReturn(employeeList);

        List<EmployeeDto> result = employeeService.sortByEmployeeSalary();

        assertNotNull(result);
        assertEquals(3, result.size());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should sort employees by salary descending")
    public void testSortByEmployeeSalaryDsc() {
        when(employeeRepository.findAll()).thenReturn(employeeList);

        List<EmployeeDto> result = employeeService.sortByEmployeeSalaryDsc();

        assertNotNull(result);
        assertEquals(3, result.size());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should sort employees by age ascending")
    public void testSortByEmployeeAge() {
        when(employeeRepository.findAll()).thenReturn(employeeList);

        List<EmployeeDto> result = employeeService.sortByEmployeeAge();

        assertNotNull(result);
        assertEquals(3, result.size());
        verify(employeeRepository, times(1)).findAll();
    }

    // ==================== Aggregation Tests ====================

    @Test
    @DisplayName("Should find average salary")
    public void testFindAverageSalary() {
        when(employeeRepository.findAll()).thenReturn(employeeList);

        double averageSalary = employeeService.findAverageSalary();

        assertEquals(75000.0, averageSalary);
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should find all work locations")
    public void testFindAllWorkLocations() {
        when(employeeRepository.findAll()).thenReturn(employeeList);

        List<String> locations = employeeService.findAllWorkLocations();

        assertNotNull(locations);
        assertTrue(locations.contains("New York"));
        assertTrue(locations.contains("London"));
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should count employees by work location")
    public void testCountEmployeesByWorkLocation() {
        when(employeeRepository.findAll()).thenReturn(employeeList);

        long count = employeeService.countEmployeesByWorkLocation("New York");

        assertEquals(2, count);
        verify(employeeRepository, times(1)).findAll();
    }

    // ==================== Grouping Tests ====================

    @Test
    @DisplayName("Should group employees by salary")
    public void testGroupBySalaryDetails() {
        when(employeeRepository.findAll()).thenReturn(employeeList);

        Map<Object, List<Employee>> result = employeeService.groupBySalaryDeatails();

        assertNotNull(result);
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should group employees by name")
    public void testGroupByNamesDetails() {
        when(employeeRepository.findAll()).thenReturn(employeeList);

        Map<Object, List<Employee>> result = employeeService.groupByNamesDeatails();

        assertNotNull(result);
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should group employees by work location with count")
    public void testGroupByWorkLocationDetailsWithCount() {
        when(employeeRepository.findAll()).thenReturn(employeeList);

        Map<String, Long> result = employeeService.groupByWorkLocationDeatailsWithCount();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(employeeRepository, times(1)).findAll();
    }

    // ==================== String Conversion Tests ====================

    @Test
    @DisplayName("Should convert list to set")
    public void testListToSetConversion() {
        when(employeeRepository.findAll()).thenReturn(employeeList);

        Set<Employee> result = employeeService.listToSetCoversion();

        assertNotNull(result);
        assertEquals(3, result.size());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should convert list to map")
    public void testListToMapConversion() {
        when(employeeRepository.findAll()).thenReturn(employeeList);

        Map<Long, Employee> result = employeeService.listToMapCoversion();

        assertNotNull(result);
        assertEquals(3, result.size());
        assertTrue(result.containsKey(1L));
        verify(employeeRepository, times(1)).findAll();
    }



    // ==================== Pagination Tests ====================

    @Test
    @DisplayName("Should get employees list with pagination")
    public void testGetEmployeesList() {
        Page<Employee> page = new PageImpl<>(employeeList, PageRequest.of(0, 10), employeeList.size());
        when(employeeRepository.findAll(PageRequest.of(0, 10))).thenReturn(page);

        Page<Employee> result = employeeService.getEmployeesList(0, 10);

        assertNotNull(result);
        assertEquals(3, result.getContent().size());
        verify(employeeRepository, times(1)).findAll(PageRequest.of(0, 10));
    }

    // ==================== File Generation Tests ====================

    @Test
    @DisplayName("Should generate PDF successfully")
    public void testPdfGenerator() {
        when(employeeRepository.findAll()).thenReturn(employeeList);

        byte[] result = employeeService.pdfGenerator();

        assertNotNull(result);
        verify(employeeRepository, times(1)).findAll();
    }



    // ==================== Email Tests ====================

    @Test
    @DisplayName("Should send email with PDF attachment successfully")
    public void testSendEmailWithPdfAttachment_Success() throws Exception {
        when(employeeRepository.findAll()).thenReturn(employeeList);
        doNothing().when(emailSender).sendPdf(anyString(), anyString(), anyString(), any(byte[].class), anyString());

        ResponseEntity<String> result = employeeService.sendEmailWithPdfAttachment("test@example.com");

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertTrue(result.getBody().contains("successfully"));
        verify(employeeRepository, times(1)).findAll();
        verify(emailSender, times(1)).sendPdf(anyString(), anyString(), anyString(), any(byte[].class), anyString());
    }



    // ==================== Edge Case Tests ====================

    @Test
    @DisplayName("Should handle empty employee list")
    public void testFetchAllEmployeeDetails_EmptyList() {
        when(employeeRepository.findAll()).thenReturn(new ArrayList<>());

        List<EmployeeDto> result = employeeService.fetchAllEmployeeDetails();

        assertNotNull(result);
        assertEquals(0, result.size());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should return empty optional when email not found")
    public void testFindByEmail_NotFound() {
        when(employeeRepository.findByEmail("nonexistent@example.com")).thenReturn(new ArrayList<>());

        Optional<EmployeeDto> result = employeeService.findByEmail("nonexistent@example.com");

        assertTrue(result.isEmpty());
        verify(employeeRepository, times(1)).findByEmail("nonexistent@example.com");
    }

}