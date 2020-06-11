package com.schoolproject.InvestorPanel.Service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.schoolproject.InvestorPanel.Entity.Property;
import com.schoolproject.InvestorPanel.Entity.PropertyRepository;
import com.schoolproject.InvestorPanel.Entity.User;
import com.schoolproject.InvestorPanel.Entity.UserRepository;

@Service

public class PropertyService {
	
	private UserRepository userRepository;
	private PropertyRepository propertyRepository;
	
	@Autowired
	public PropertyService(UserRepository userRepository, PropertyRepository propertyRepository) {
		this.userRepository = userRepository;
		this.propertyRepository = propertyRepository;

	}

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Autowired
	public void setPropertyRepository(PropertyRepository propertyRepository) {
		this.propertyRepository = propertyRepository;
	}
	
	public void addProperty(Property property) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 
//        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            User user = userRepository.findByEmailAddress(username);
        System.out.println(username);
        System.out.println("Jestem w ADD PROPERTY");
        System.out.println(user);

//    } 
//        else 
//    {
//        String username = principal.toString();
//        System.out.println(username);
//    
		//property.setOwner(user.getId());
        property.setOwner(user.getId());
 //       user.getProperties().add(property);
		propertyRepository.save(property);
	}
	
	public void updateProperty(Property property) {
		propertyRepository.save(property);
	}
}
