package com.org.java.service;

import com.org.java.relation.Company;
import com.org.java.relation.Order;
import com.org.java.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {


    Order saveOrders(Order order);

    List<Order> findAllOrders();

    List<Order> findByProduct(String name);
}
