package com.schoolproject.InvestorPanel.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.schoolproject.InvestorPanel.Entity.Property;
import com.schoolproject.InvestorPanel.Entity.PropertyRepository;
import com.schoolproject.InvestorPanel.Entity.UserRepository;
import com.schoolproject.InvestorPanel.Service.PropertyService;

@Controller
public class PropertyController {

	private PropertyService propertyService;
	private PropertyRepository propertyRepository;
	private UserRepository userRepository;

	@Autowired
	public PropertyController(PropertyRepository propertyRepository, UserRepository userRepository) {
		this.propertyRepository = propertyRepository;
		this.userRepository = userRepository;
	}

	@Autowired
	public void setPropertyService(PropertyService propertyService) {
		this.propertyService = propertyService;
	}
	
	@PostMapping("/addProperty")
	public String addProperty(@ModelAttribute() Property property, BindingResult bindResult) {

		if (bindResult.hasErrors()) {
			return "addProperty";
		} else {

			System.err.println(property);
			propertyService.addProperty(property);
			return "redirect:raport";
		}

//		propertyRepository.save(property);
//		
	}
	
	@PostMapping("/deleteProperty2")
	public String delProperty(Model model, Property property) {
		model.addAttribute("property", property);
		System.out.println(property.toString());
		return "/";
	}

}
