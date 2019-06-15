package com.exaple.orders.repository;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.exaple.orders.Entity.AccountEntity;
import com.exaple.orders.Entity.OrderProductsEntity;
import com.exaple.orders.interFace.AccountInterface;
import com.exaple.orders.interFace.LoginDateInterface;


@Repository
public class AccountRepository {
	
	@Autowired
	AccountInterface accountInterface;
	
	@Autowired
	LoginDateInterface loginDateInterface;
	
	
	
	public AccountEntity findByEmail(String email) {
		return accountInterface.findByEmail(email);
	}
	
	public AccountEntity userDetail(int userId) {
		AccountEntity accountEntity =accountInterface.getOne(userId);
		return	accountEntity;
	}
	

	public void registDate(int userId,Timestamp loginDate) {
		AccountEntity accountEntity =loginDateInterface.findByUserId(userId);
		accountEntity.setLoginDate(loginDate);
		loginDateInterface.save(accountEntity);
	}
	
	
	public void doUserUpdate(int usersId,String lastName,String firstName,String email,Timestamp updatetime) {
		AccountEntity entity = loginDateInterface.findByUserId(usersId);
		entity.setFirstName(firstName);
		entity.setLastName(lastName);
		entity.setEmail(email);
		entity.setUpdateDate(updatetime);
		
		loginDateInterface.save(entity);
	}
	
	
	
	
	
}
