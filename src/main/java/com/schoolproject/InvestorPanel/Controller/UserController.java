package com.schoolproject.InvestorPanel.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
import com.schoolproject.InvestorPanel.Entity.User;
import com.schoolproject.InvestorPanel.Entity.UserRepository;
import com.schoolproject.InvestorPanel.Entity.UserRoleRepository;
import com.schoolproject.InvestorPanel.Service.UserService;

@Controller
public class UserController {

	private UserRepository userRepository;
	private PropertyRepository propertyRepository;
	private UserRoleRepository userRoleRepository;
	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public UserController(UserRepository userRepository, PropertyRepository propertyRepository,
			UserRoleRepository userRoleRepository) {
		this.userRepository = userRepository;
		this.propertyRepository = propertyRepository;
		this.userRoleRepository = userRoleRepository;
	}

	@GetMapping("/manage")
	public String toUserPanel(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			String username = ((UserDetails) principal).getUsername();
			User user = userRepository.findByEmailAddress(username);
			System.out.println(username);
			System.out.println(user);
			model.addAttribute("user", user);
			if (userService.hasAdminRole(user)) {
				Iterable<User> allUsers = userRepository.findAll();
				model.addAttribute("allUsers", allUsers);
				return "adminPanel";
			} else if (userService.hasUserRole(user)) {
				Iterable<Property> allUserProperties = propertyRepository.findByOwner(user.getId());
				model.addAttribute("allUserProperties", allUserProperties);
				return "userPanel";
			}
		}
//        else 
//    {
//        String username = principal.toString();
//        System.out.println(username);
//    }

		return "/";
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

//	@GetMapping("userPanel")
//	public String toManagePanel(Model model) {
//		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//		if (principal instanceof UserDetails) {
//			String username = ((UserDetails) principal).getUsername();
//			User user = userRepository.findByEmailAddress(username);
//			System.out.println(username);
//			System.out.println(user);
//			model.addAttribute("user", user);
//			Iterable<Property> allUserProperties = propertyRepository.findByOwner(user.getId());
//			model.addAttribute("allUserProperties", allUserProperties);
//
//		}
////        else 
////    {
////        String username = principal.toString();
////        System.out.println(username);
////    }
//
//		return "userPanel";
//	}
	
//	@GetMapping("/adminSite")
//	public String toforAdmin() {
//		return "adminSite";
//	}
	
	
	
	
	@RequestMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable(name = "id") Long id) {
		User user = userRepository.findOneById(id);
		Iterable<Property> allUserProperties = propertyRepository.findByOwner(id);
		propertyRepository.deleteAll(allUserProperties);
		userRepository.deleteById(id);
		return "redirect:/adminPanel";
	}
	
	@RequestMapping("/addAdminRole/{id}")
	public String addAdminRole(@PathVariable(name = "id") Long id) {

		userService.addAdminRole(userRepository.findOneById(id));
		return "redirect:/adminPanel";
	}
	
	

}
