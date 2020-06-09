package com.schoolproject.InvestorPanel.Service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.schoolproject.InvestorPanel.Entity.User;
import com.schoolproject.InvestorPanel.Entity.UserRepository;
import com.schoolproject.InvestorPanel.Entity.UserRole;

@Component
public class MyUserDetailService implements UserDetailsService {

	private UserRepository userRepository;

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.findByEmailAddress(username);
		if (user == null) {
			throw new UsernameNotFoundException("Nie znaleziono uzytkownika");
		}
			org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core.userdetails.User(
					user.getEmailAddress(), user.getPassword(), convertAuthorities(user.getRoles()));
			return userDetails;
		}
		
		private Set<GrantedAuthority> convertAuthorities(Set<UserRole> userRoles){
			
			Set<GrantedAuthority> authorities = new HashSet<>();
			for(UserRole role: userRoles) {
				authorities.add(new SimpleGrantedAuthority(role.getRole()));
				System.out.println(authorities);
			}
			return authorities;
		}

	

}

