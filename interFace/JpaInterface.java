package com.exaple.orders.interFace;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.exaple.orders.Entity.OrderProductsEntity;



public interface JpaInterface extends JpaRepository<OrderProductsEntity, Integer>,JpaSpecificationExecutor<OrderProductsEntity> {

}
