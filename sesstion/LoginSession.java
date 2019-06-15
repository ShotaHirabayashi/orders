package com.exaple.orders.sesstion;

import java.io.Serializable;
import java.sql.Timestamp;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

//DIコンテナにbeanとして登録したいクラスへ付与するアノテーション
@Component
//セッションスコープを利用するためのアノテーション
@Scope(value = "session",proxyMode = ScopedProxyMode.TARGET_CLASS)
//ScopedProxyMode.TARGET_CLASS)
//Serializableインターフェースを実装するクラスにすることでオブジェクトの情報を保存することが出来る
public class LoginSession implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int userId;

	private String firstName;
	
	private String lastName;
	
	private Timestamp registDate;
	
	private Timestamp updateDate;
	
	private String email;
	
	private String password;
	
	private int loginMissCount;
	
	private Timestamp loginDate;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Timestamp getRegistDate() {
		return registDate;
	}

	public void setRegistDate(Timestamp registDate) {
		this.registDate = registDate;
	}

	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getLoginMissCount() {
		return loginMissCount;
	}

	public void setLoginMissCount(int loginMissCount) {
		this.loginMissCount = loginMissCount;
	}

	public Timestamp getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Timestamp loginDate) {
		this.loginDate = loginDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
