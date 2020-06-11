package com.schoolproject.InvestorPanel.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.schoolproject.InvestorPanel.Entity.Property;
import com.schoolproject.InvestorPanel.Entity.PropertyRepository;
import com.schoolproject.InvestorPanel.Entity.User;
import com.schoolproject.InvestorPanel.Entity.UserRepository;
import com.schoolproject.InvestorPanel.Service.UserService;

@Controller
public class HomeController {

	private UserRepository userRepository;
	private PropertyRepository propertyRepository;
	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public HomeController(UserRepository userRepository, PropertyRepository propertyRepository) {
		this.userRepository = userRepository;
		this.propertyRepository = propertyRepository;
	}

	@GetMapping("/")
	public String toIndex() {

		return "index";
	}

	@GetMapping("/login")
	public String toLogin() {
		return "login";
	}

	@GetMapping("/registration")
	public String toRegistration(Model model) {
		model.addAttribute("user", new User());
		return "registration";
	}

	@GetMapping("/registerSuccess")
	public String toRegisterSuccess() {
		return "registerSuccess";
	}

	@GetMapping("/registerFail")
	public String toRegisterFail() {
		return "registerFail";
	}
	
	@GetMapping("/adminPanel")
	public String toAdminPanel(Model model) {
		Iterable<User> allUsers = userRepository.findAll();
		model.addAttribute("allUsers", allUsers);
		return "adminPanel";
	}


	@GetMapping("/kontakt")
	public String toKontakt(Model model) {
		Iterable<User> allUsers = userRepository.findAll();
		model.addAttribute("allUsers", allUsers);
		return "showAll";
	}


	@GetMapping("/logout")
	public String toLogoutSuccess(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login";
	}

	@GetMapping("/logoutSuccess")
	public String toLogoutSuccess() {
		return "LogoutSuccess";
	}


	@GetMapping("/raport")
	public String toRaport(Model model) {
		Iterable<Property> allProperties = propertyRepository.findAll();
		model.addAttribute("allProperties", allProperties);
		return "raport";
	}

//	@GetMapping("/error")
//	public String toErr() {
//		return "index";
//	}

	@GetMapping("/propertyForm")
	public String toRegistrationProperty(Model model) {
		model.addAttribute("property", new Property());
		return "propertyForm";
	}


	
	@GetMapping("/propertyDetails")
	public String toPD() {
		return "propertyDetails";
	}
	
	@GetMapping("/new")
	public String tonew() {
		return "new";
	}

}
