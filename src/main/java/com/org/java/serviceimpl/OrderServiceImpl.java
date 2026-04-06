package com.org.java.serviceimpl;

import com.org.java.relation.Company;
import com.org.java.relation.Order;
import com.org.java.repository.OrderRepository;
import com.org.java.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order saveOrders(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> findAllOrders() {
       List<Order> list = orderRepository.findAll();
        return list;
    }

    @Override
    public List<Order> findByProduct(String name) {
        List<Order> products=orderRepository.findByproductName(name);
        return products;
    }
}
