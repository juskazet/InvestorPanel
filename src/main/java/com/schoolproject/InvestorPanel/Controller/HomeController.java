package com.schoolproject.InvestorPanel.Controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.schoolproject.InvestorPanel.Entity.PropertyRepository;
import com.schoolproject.InvestorPanel.Entity.Property;
import com.schoolproject.InvestorPanel.Entity.UserRepository;
import com.schoolproject.InvestorPanel.Service.UserService;
import com.schoolproject.InvestorPanel.Entity.User;

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

	@GetMapping("/userPanel")
	public String toUserPanel(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			String username = ((UserDetails) principal).getUsername();
			User user = userRepository.findByEmailAddress(username);
			System.out.println(username);
			System.out.println(user);
			model.addAttribute("user", user);
			Iterable<Property> allUserProperties = propertyRepository.findByOwner(user.getId());
			model.addAttribute("allUserProperties", allUserProperties);

		}
//        else 
//    {
//        String username = principal.toString();
//        System.out.println(username);
//    }

		return "userPanel";
	}

	@GetMapping("/kontakt")
	public String toKontakt(Model model) {
		Iterable<User> allUsers = userRepository.findAll();
		model.addAttribute("allUsers", allUsers);
		return "showAll";
	}

//	@PostMapping("/registration")
//	public String registrerUser(@ModelAttribute User user) {
//		userRepository.save(user);
//		return "redirect:/";
//	}

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

//	@GetMapping("/show")
//	public String showAll(Model model) {
//		List<User> allUsers = (List<User>) userRepo.findAll();
//		model.addAttribute("allUsers", allUsers);
//	    return "showall";
//	}
//	
////
////	
////	@GetMapping("/add")
////	public String toAdd() {
////		return "add";
////	}

	@GetMapping("/raport")
	public String toRaport(Model model) {
		Iterable<Property> allProperties = propertyRepository.findAll();
		model.addAttribute("allProperties", allProperties);
		return "raport";
	}

	@GetMapping("/error")
	public String toErr(Model model) {
		Iterable<Property> allProperties = propertyRepository.findAll();
		model.addAttribute("allProperties", allProperties);
		return "index";
	}

	@GetMapping("/propertyForm")
	public String toRegistrationProperty(Model model) {
		model.addAttribute("property", new Property());
		return "propertyForm";
	}

	@GetMapping("/forUser")
	public String toforUser() {
		return "forUser";
	}

	@GetMapping("/forAdmin")
	public String toforAdmin() {
		return "forAdmin";
	}

	@PostMapping("/addUser")
	public String addUser(@ModelAttribute()  User user, BindingResult bindResult) {
		if(bindResult.hasErrors()) {
			return "registration";
		}
		else {
			if(userService.addWithUserRole(user)) {
			return "redirect:registerSuccess";
			}
			else {
				return "redirect:registerFail";
			}
//			userRepo.save(user);
//			System.err.println(user);
//			return "redirect:registerSuccess";
		}
	}
	

//	@GetMapping("/propertyForm")
//	public String toRegistration(Model model) {
//		model.addAttribute("property", new Property());
//		return "propertyForm";
//	}
	
//	@GetMapping("/usun")
//	public String usun() {
//		User user=userRepository.findOneById(6L);
//		userRepository.delete(user);
//		Iterable<Property> properties =user.getProperties();
//		propertyRepository.deleteAll(properties);
////		for(Property prop : properties) {
////			propertyRepository.delete(prop);
////		}
//		System.out.println(properties);
//		//propertyRepository.deleteAll(properties);
//		userRepository.delete(user);
//		return "raport";
//	}
}
