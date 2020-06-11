package com.schoolproject.InvestorPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.schoolproject.InvestorPanel.Entity.Property;
import com.schoolproject.InvestorPanel.Entity.PropertyRepository;
import com.schoolproject.InvestorPanel.Entity.User;
import com.schoolproject.InvestorPanel.Entity.UserRepository;
import com.schoolproject.InvestorPanel.Entity.UserRole;
import com.schoolproject.InvestorPanel.Entity.UserRoleRepository;
import com.schoolproject.InvestorPanel.Service.UserService;

@Component
public class Start {
	
	private UserRepository userRepository;
	private UserRoleRepository userRoleRepository;
	private PropertyRepository propertyRepository;
	private static final String DEFAULT_ROLE_USER = "ROLE_USER";
	private static final String DEFAULT_ROLE_ADMIN = "ROLE_ADMIN";
	
	private UserService userService;
	
	@Autowired
	public Start(UserRepository userRepository,  UserRoleRepository userRoleRepo, PropertyRepository propertyRepository) {
		this.userRepository = userRepository;
		this.userRoleRepository = userRoleRepo;
		this.propertyRepository = propertyRepository;
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void runExample() {
			
		userService.addWithAdminRole(new User("a.kot@wp.pl", "ala11"));
		userService.addWithUserRole(new User("e.plot@wp.pl", "ela2"));

		for(User user : userRepository.findAll()) {
			System.out.println(user);	
		}
		

	}
}