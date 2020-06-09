package com.schoolproject.InvestorPanel.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.schoolproject.InvestorPanel.Entity.User;
import com.schoolproject.InvestorPanel.Entity.UserRepository;
import com.schoolproject.InvestorPanel.Entity.UserRole;
import com.schoolproject.InvestorPanel.Entity.UserRole;
import com.schoolproject.InvestorPanel.Entity.UserRoleRepository;

@Service
public class UserService {

	private static final String USER_ROLE = "ROLE_USER";
	private static final String ADMIN_ROLE = "ROLE_ADMIN";
	private UserRepository userRepository;
	private UserRoleRepository userRoleRepository;
	// private PasswordEncoder passwordEncoder;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Autowired
	public void setUserRoleRepository(UserRoleRepository userRoleRepository) {
		this.userRoleRepository = userRoleRepository;
	}

	public boolean addWithUserRole(User user) {
		System.out.println(userRepository.existsByEmailAddress(user.getEmailAddress()));
		if (userRepository.existsByEmailAddress(user.getEmailAddress())) {
			return false;
		} else
		{
			UserRole defaultRole = userRoleRepository.findByRole(USER_ROLE);
			user.getRoles().add(defaultRole);
			String passwordHash = passwordEncoder.encode(user.getPassword());
			user.setPassword(passwordHash);
			userRepository.save(user);
			return true;
			
		}
	}
	
	public boolean addWithAdminRole(User user) {
		System.out.println(userRepository.existsByEmailAddress(user.getEmailAddress()));
		if (userRepository.existsByEmailAddress(user.getEmailAddress())) {
			return false;
		} else
		{
			UserRole defaultRole = userRoleRepository.findByRole(ADMIN_ROLE);
			user.getRoles().add(defaultRole);
			String passwordHash = passwordEncoder.encode(user.getPassword());
			user.setPassword(passwordHash);
			userRepository.save(user);
			return true;
			
		}
	}

}