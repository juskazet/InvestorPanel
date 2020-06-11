package com.schoolproject.InvestorPanel.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
			return "redirect:manage";
		}
	}

	@RequestMapping("/deleteProperty/{id}")
	public String deleteProperty(@PathVariable(name = "id") Long id) {
		propertyRepository.deleteById(id);
		return "redirect:manage";
	}

	@PostMapping("/update")
	public String saveProperty(@ModelAttribute("property") Property property) {
		propertyService.updateProperty(property);
		return "redirect:manage";
	}

	@RequestMapping("/editProperty/{id}")
	public String showEditProductPage(@PathVariable(name = "id") Long id, Model model) {
		Property property = propertyRepository.findOneById(id);
		model.addAttribute("property", property);
		property.setId(id);
		return "editPropertyForm";
	}
	
	@RequestMapping("raport/{id}")
	public String raportPage(@PathVariable(name = "id") Long id, Model model) {
		Property property = propertyRepository.findOneById(id);
		model.addAttribute("property", property);
		property.setId(id);
		model.addAttribute("generalCosts", propertyService.generalCosts(property));
		model.addAttribute("costPerMeter", propertyService.costPerMeter(property));
		model.addAttribute("monthlyProfit", propertyService.monthlyProfit(property));
		model.addAttribute("returnRatePerYear", propertyService.returnRatePerYear(property));
		return "/raport";
	}
			

}
