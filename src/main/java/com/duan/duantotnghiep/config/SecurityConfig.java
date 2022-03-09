package com.duan.duantotnghiep.config;

import com.duan.duantotnghiep.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired UserService userService;

	//Cơ chế mã hóa mật khẩu
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	//Cung cấp nguồn dữ liệu đăng nhập
	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
				.antMatchers("/checkout/**").authenticated()
				.antMatchers("/admin/**").hasAnyRole("STAF","ADMI")
				.antMatchers("/rest/authorities").hasRole("ADMI")
				.anyRequest().permitAll();

//		http.httpBasic();

		http.formLogin()
				.loginPage("/security/login")
				.loginProcessingUrl("/security/login")
				.defaultSuccessUrl("/security/login/success",false)
				.failureUrl("/security/login/error");

		http.rememberMe()
				.tokenValiditySeconds(86400);

		http.exceptionHandling()
				.accessDeniedPage("/security/unauthoried");

		http.logout()
				.logoutUrl("/security/logoff")
				.logoutSuccessUrl("/security/logoff/success");

		// OAuth2 - Đăng nhập từ mạng xã hội
		http.oauth2Login()
				.loginPage("/security/login")
				.defaultSuccessUrl("/oauth2/login/success", true)
				.failureUrl("/security/login/error")
				.authorizationEndpoint()
				.baseUri("/oauth2/authorization");
	}

	//Phân quyền sử dụng
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable();
//		http.authorizeRequests()
//		.antMatchers("/checkout/**").authenticated()
//		.antMatchers("/admin/**").hasAnyRole("STAFF","ADMIN")
//		.antMatchers("/rest/authorities").hasRole("ADMIN")
//		.anyRequest().permitAll();
//
//		http.formLogin()
//		.loginPage("/security/login")
//		.defaultSuccessUrl("/security/login/success",false)
//		.failureUrl("/security/login/error");
//
//		http.logout()
//		.logoutUrl("/security/logoff")
//		.logoutSuccessUrl("/security/logoff/success");
//
//	}
	
	//Cho phép truy xuất RESP API từ bên ngoài (domain khác)
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS,"/**");
	}
	
}
