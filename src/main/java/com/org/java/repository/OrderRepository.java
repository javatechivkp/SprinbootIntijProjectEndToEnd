package com.org.java.repository;

import com.org.java.relation.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {

    @Query("SELECT o FROM Order o JOIN o.items i WHERE i.productName = :name")
    List<Order> findByproductName(String name);
}
