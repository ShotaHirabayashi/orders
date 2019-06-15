package com.exaple.orders.interFace;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.exaple.orders.Entity.CompaniesEntity;


public interface JpaInterfaceCompany extends JpaRepository<CompaniesEntity,Integer>,JpaSpecificationExecutor<CompaniesEntity> {

}

 


