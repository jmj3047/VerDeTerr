package com.board.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	/**
	 * PasswordEncoder를 Bean으로 등록
	 */
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * 인증 or 인가에 대한 설정
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable() // post 방식으로 값을 전송할 때 token을 사용해야하는 보안 설정을 해제

				.authorizeRequests()
				.antMatchers("/main",
						"/signup", "/signup_proc", "/checkId", "/checkId_proc", "/checkEmail", "/checkEmail_proc",
						"/login", "/login_proc", "/logout", "/deleteUser", "/deleteUser_proc",
						"/findId", "/findPw", "/findId_proc", "/findPw_proc",
						"/identify", "/identify_proc", "/mypage", "/managerpage",
						"/modify", "/modify_proc", "/character/**",
						"/css/**", "/assets/**", "/js/**" , "/scripts/**", "/plugin/**",
						"/survey/surveylist", "/survey/surveyresult", "/survey/surveydone",
						"/board/list", "/board/write", "/board/register", "/board/view", "/board/delete",
						"/comments/*","/comments", "/board/mylist", 
						"/board/select", "/board/write", "/board/register", "/board/view", "/board/delete")
				.permitAll().anyRequest().authenticated().
				and().formLogin().loginPage("/login").permitAll().
				and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutProc").permitAll();

	}

	/*
	 * // static 파일 권한 허용 설정
	 * 
	 * @Override public void configure(WebSecurity web) throws Exception {
	 * web.ignoring().requestMatchers(PathRequest.toStaticResources().
	 * atCommonLocations()); }
	 */

}
