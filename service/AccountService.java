package com.exaple.orders.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.exaple.orders.Entity.AccountEntity;
import com.exaple.orders.Entity.OrderProductsEntity;
import com.exaple.orders.repository.AccountRepository;
import com.exaple.orders.sesstion.LoginSession;


@Service
public class AccountService implements UserDetailsService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	// LoginSessionクラスを@Autowiredでインジェクション
	@Autowired
	LoginSession session;	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// usernameが空か確認
		if (StringUtils.isEmpty(email)) {
			throw new UsernameNotFoundException("Emailを入力してね");
		}

		// usernameを元にDBからアカウント情報が取得できたら、アカウント情報を生成
		AccountEntity account = accountRepository.findByEmail(email);
		if (account == null) {
			throw new UsernameNotFoundException("メールアドレスが違います");
		}
		
		
		// ログイン成功時セッションに値をセット
		session.setUserId(account.getUserId());
		session.setFirstName(account.getFirstName());
		session.setLastName(account.getLastName());
		session.setRegistDate(account.getRegistDate());
		session.setUpdateDate(account.getUpdateDate());
		session.setEmail(account.getEmail());
		session.setPassword(account.getPassword());
		session.setLoginMissCount(account.getLoginMissCount());
		session.setLoginDate(new Timestamp(System.currentTimeMillis()));
		accountRepository.registDate(session.getUserId(),session.getLoginDate());
		return account;
	}
	
	public AccountEntity userDetail(int userId) {
		AccountEntity accountEntity = accountRepository.userDetail(userId);
		return accountEntity;
	}
	
	public void doUserUpdate(int usersId , String lastName , String firstName , String email , Timestamp updateDate) {
			accountRepository.doUserUpdate(usersId, lastName, firstName, email, updateDate);
			session.setLastName(lastName);
			session.setFirstName(firstName);
			session.setEmail(email);
			session.setUpdateDate(updateDate);
	}
	
	
}
