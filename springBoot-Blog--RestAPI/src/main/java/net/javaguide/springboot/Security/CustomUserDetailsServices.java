package net.javaguide.springboot.Security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import net.javaguide.springboot.entity.User;
import net.javaguide.springboot.repository.UserRepository;

@Service
public class CustomUserDetailsServices  implements UserDetailsService{
    
	private UserRepository userRepository;
	
	public CustomUserDetailsServices(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
     User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
     .orElseThrow(() -> new UsernameNotFoundException
    		 ("user not found with username or Email " + usernameOrEmail));
	
     //convert list to set garanteed authorited
     Set<GrantedAuthority> authorities = user
    		 .getRoles()
             .stream()
    		 .map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
    // return new User(user.getEmail(),user.getPassword(),authorities);
     return new org.springframework.security.core.userdetails.User(
             user.getUsername(), user.getPassword(), authorities);
 }
	}
    

