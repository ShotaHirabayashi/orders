package com.exaple.orders.interFace;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exaple.orders.Entity.OrderProductsEntity;
import com.exaple.orders.Entity.ReceivingOrdersEntity;


public interface JpaInterfaceRecevingOrders extends JpaRepository<ReceivingOrdersEntity, Integer> {

}