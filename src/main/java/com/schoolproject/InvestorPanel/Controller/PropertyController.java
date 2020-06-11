package com.schoolproject.InvestorPanel.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
			return "redirect:raport";
		}

//		propertyRepository.save(property);
//		
	}

	
	@RequestMapping("/deleteProperty/{id}")
	public String deleteProperty(@PathVariable(name = "id") Long id) {
		propertyRepository.deleteById(id);
		return "redirect:/manage";
	}
	
//	@RequestMapping("/editProperty/{id}")
//	public String editProperty(@PathVariable(name = "id") Long id) {
//		return "redirect:/editPropertyForm";
//	}
	
//	@RequestMapping("/editProperty/{id}")
//	public ModelAndView showEditProductPage(@PathVariable(name = "id") Long id) {
//	    ModelAndView mav = new ModelAndView("editPropertyForm");
//	    Property property = propertyRepository.findOneById(id);
//	    mav.addObject("property", property);
//	    System.out.println("     EDUTUJEMY      "); 
//	    return mav;
//	}
//	
//	@RequestMapping(value = "/save", method = RequestMethod.POST)
//	public String saveProperty(@ModelAttribute("property") Property property) {
//	    propertyRepository.save(property);
//	     
//	    return "redirect:/";
//	}
	
//	@GetMapping("/save")
//	public String savePropertyMap(Model model) {
//		Property property = new Property();
//	    model.addAttribute("property", property);
//	    return "save";
//	}
	
	@PostMapping("/update")
	public String saveProperty(@ModelAttribute("property") Property property) {
	    propertyService.updateProperty(property);
	     System.out.println("         ZAPIS           ");
	    return "redirect:/";
	}
	
	@RequestMapping("/editProperty/{id}")
	public String showEditProductPage(@PathVariable(name = "id") Long id, Model model) {
	    Property property = propertyRepository.findOneById(id);
	    model.addAttribute("property", property);
	    property.setId(id);
	    return "editPropertyForm";
	}
	
	

}
