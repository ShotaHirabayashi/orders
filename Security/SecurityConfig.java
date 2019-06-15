package com.exaple.orders.Security;

import javax.annotation.security.PermitAll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.exaple.orders.service.AccountService;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	AccountService accountService;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		// 認証するユーザーを設定する(今回はServiceで)
		authenticationManagerBuilder.userDetailsService(this.accountService)
				// PasswordEncoderを使用しハッシュ化した値でパスワード認証を行う
				.passwordEncoder(passwordEncoder());
	}
	
	
	@Override
	protected void configure(HttpSecurity httpSecurity)throws Exception{
		httpSecurity
			.csrf()
			.disable()
			.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/orders/view",true)
			.failureUrl("/login-error")//ログイン失敗時に流すURL
			.permitAll();
		httpSecurity
			.authorizeRequests()
			.antMatchers("/css/**")
			.permitAll()
			.anyRequest()
			.authenticated();
		httpSecurity
			.logout()
			.logoutSuccessUrl("/login")
        	.permitAll();
		
	   
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
	
	
}