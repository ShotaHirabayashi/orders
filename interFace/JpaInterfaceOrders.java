package com.exaple.orders.interFace;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exaple.orders.Entity.OrdersEntity;


public interface JpaInterfaceOrders extends JpaRepository<OrdersEntity, Integer> {

}