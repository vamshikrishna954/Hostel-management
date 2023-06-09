package com.spring.hostel.config;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
           
		auth.jdbcAuthentication()
            .dataSource(dataSource)
            .usersByUsernameQuery("select user_name,user_password,'true' from signup_tbl where user_name=?")
            .authoritiesByUsernameQuery("select user_name,role from signup_tbl where user_name=?")
            .passwordEncoder(bCryptPasswordEncoder);
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		 http.authorizeRequests()
		    .antMatchers("/cust/login").permitAll()
			.antMatchers("/cust/getGetHomeInfo").hasAuthority("ADMIN")
			.antMatchers("/cust/roomBookingBeforeLogin").hasAnyRole("USER","ADMIN")
			.anyRequest().permitAll()
			.and()
			.formLogin()
			.loginPage("/login")
			.permitAll()
			.defaultSuccessUrl("/cust/loggedInPage",true)
			.and()
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/cust/getHome")
			.and()
			.exceptionHandling()
			.accessDeniedPage("/cust/accessDenied")
			.and()
			.csrf().disable();
			
			
			
		   
	}

}
