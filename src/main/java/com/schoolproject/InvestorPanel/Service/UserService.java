package com.schoolproject.InvestorPanel.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.schoolproject.InvestorPanel.Entity.Property;
import com.schoolproject.InvestorPanel.Entity.PropertyRepository;
import com.schoolproject.InvestorPanel.Entity.User;
import com.schoolproject.InvestorPanel.Entity.UserRepository;
import com.schoolproject.InvestorPanel.Entity.UserRole;
import com.schoolproject.InvestorPanel.Entity.UserRoleRepository;

@Service
public class UserService {

	private static final String USER_ROLE = "ROLE_USER";
	private static final String ADMIN_ROLE = "ROLE_ADMIN";
	private UserRepository userRepository;
	private UserRoleRepository userRoleRepository;
	private PropertyRepository propertyRepository;
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
			UserRole userRole = userRoleRepository.findByRole(USER_ROLE);
			user.getRoles().add(userRole);
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
			UserRole adminRole = userRoleRepository.findByRole(ADMIN_ROLE);
			user.getRoles().add(adminRole);
			String passwordHash = passwordEncoder.encode(user.getPassword());
			user.setPassword(passwordHash);
			userRepository.save(user);
			return true;
			
		}
	}
	
	public boolean hasAdminRole(User user) {
		return user.getRoles().contains(userRoleRepository.findByRole(ADMIN_ROLE));
	}
	
	public boolean hasUserRole(User user) {
		return user.getRoles().contains(userRoleRepository.findByRole(USER_ROLE));
	}
	
	public void addAdminRole(User user) {
		user.toString();
		UserRole adminRole = userRoleRepository.findByRole(ADMIN_ROLE);
		user.getRoles().add(adminRole);
		userRepository.save(user);
		user.toString();
		System.out.println("nadano role admina");
	}
	
//	public void deleteUserAndProperties(User user) {
//
//		Iterable<Property> allUserProperties = propertyRepository.findByOwner(user.getId());
//		propertyRepository.deleteAll(allUserProperties);
//		userRepository.delete(user);
//	}

}