package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.OrderList;
import com.example.demo.model.User;

public interface OrderRepository extends JpaRepository<OrderList, Integer> {

}
