package com.schoolproject.InvestorPanel.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

//	@Autowired
//	private DataSource userRepo;
//	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//		auth
//		.jdbcAuthentication()
//			.dataSource(userRepo)
//			.withUser("admin").password("{noop}Admin1").roles("USER");
//	}

//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//		return passwordEncoder;
//	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/admiPanel").access("hasRole('ROLE_ADMIN')")
		.antMatchers("/userRole", "/editPropertyForm", "/propertyForm", "/raport").access("hasRole('ROLE_USER')")
					.antMatchers("/userPanel", "/raport").authenticated()
					.antMatchers("/", "/showAll", "/index", "/registration", "/registerSuccess",  "/login",
						 "/addUser", "/error", "/propertyDetails").permitAll()
				.and()
					.formLogin()
					.loginPage("/login")
					.defaultSuccessUrl("/manage")
					.permitAll()
				.and()
					.logout();
//		.and()
//			.httpBasic();
	}

}
