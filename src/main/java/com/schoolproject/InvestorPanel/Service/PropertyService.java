package com.schoolproject.InvestorPanel.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.schoolproject.InvestorPanel.Entity.Property;
import com.schoolproject.InvestorPanel.Entity.PropertyRepository;
import com.schoolproject.InvestorPanel.Entity.User;
import com.schoolproject.InvestorPanel.Entity.UserRepository;
import com.sun.el.stream.Optional;

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

		String username = ((UserDetails) principal).getUsername();
		User user = userRepository.findByEmailAddress(username);
		System.out.println(username);
		System.out.println("Jestem w ADD PROPERTY");
		System.out.println(user);

		property.setOwner(user.getId());

		propertyRepository.save(property);
	}

	public void updateProperty(Property property) {
		propertyRepository.save(property);
	}
	
	public float generalCosts(Property property) {
		return property.getPurchasePrice() + property.getRenovationPrice() + property.getAdditionalCosts();
	}
	
	public float costPerMeter(Property property) {
		return generalCosts(property)/property.getArea();
	}
	
	public float monthlyProfit(Property property) {
		return property.getPropertyRental() - property.getMonthlyCosts();
	}
	
	public float returnRatePerYear(Property property) {
		return ((monthlyProfit(property)*12)/generalCosts(property))*100;
	}
	
	
}
