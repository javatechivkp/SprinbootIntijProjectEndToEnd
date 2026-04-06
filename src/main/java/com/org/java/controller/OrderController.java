package com.org.java.controller;

import com.org.java.dto.EmployeeDto;
import com.org.java.dto.OrderRequest;
import com.org.java.entity.Employee;
import com.org.java.mapper.EmployeeMapper;
import com.org.java.relation.Company;
import com.org.java.relation.Order;
import com.org.java.relation.Project;
import com.org.java.repository.CompanyRepository;
import com.org.java.repository.EmployeeRepository;
import com.org.java.repository.ProjectRepository;
import com.org.java.service.CompanyService;
import com.org.java.service.EmployeeService;
import com.org.java.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private OrderService orderService;

    @PostMapping("/placeOrder")
    public Order addCompanyOrderDetails(@RequestBody Order order) {
        return orderService.saveOrders(order);

    }
    @GetMapping("/fetchOrders")
    public ResponseEntity<List<Order>> fetchAllCompanyDetails() {
        List<Order> list = orderService.findAllOrders();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @GetMapping("/search")
    public List<Order> search(@RequestParam String product) {
        return orderService.findByProduct(product);
    }
}
