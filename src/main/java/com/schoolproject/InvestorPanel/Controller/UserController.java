package com.schoolproject.InvestorPanel.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.schoolproject.InvestorPanel.Entity.Property;
import com.schoolproject.InvestorPanel.Entity.PropertyRepository;
import com.schoolproject.InvestorPanel.Entity.User;
import com.schoolproject.InvestorPanel.Entity.UserRepository;
import com.schoolproject.InvestorPanel.Service.UserService;

@RequestMapping("User")
@Controller
public class UserController {
	
	private UserRepository userRepository;
	private PropertyRepository propertyRepository;
	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public UserController(UserRepository userRepository, PropertyRepository propertyRepository) {
		this.userRepository = userRepository;
		this.propertyRepository = propertyRepository;
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

		return "User/userPanel";
	}

}
