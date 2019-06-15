package com.exaple.orders.interFace;
import org.springframework.data.jpa.repository.JpaRepository;

import com.exaple.orders.Entity.AccountEntity;




public interface AccountInterface extends JpaRepository<AccountEntity, Integer> {
	public AccountEntity findByEmail(String email);
}


