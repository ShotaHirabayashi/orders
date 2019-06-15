package com.exaple.orders.interFace;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exaple.orders.Entity.AccountEntity;

public interface LoginDateInterface extends JpaRepository<AccountEntity, Integer> {
	public AccountEntity findByUserId(int userId);
	public AccountEntity findByEmail(String email);
	
}
