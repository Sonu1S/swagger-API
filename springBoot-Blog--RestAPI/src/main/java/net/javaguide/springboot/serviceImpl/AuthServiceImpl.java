package net.javaguide.springboot.serviceImpl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import net.javaguide.springboot.entity.Role;
import net.javaguide.springboot.entity.User;
import net.javaguide.springboot.exception.BlogAPIException;
import net.javaguide.springboot.payload.LoginDto;
import net.javaguide.springboot.payload.SignUpDto;
import net.javaguide.springboot.repository.RoleRepository;
import net.javaguide.springboot.repository.UserRepository;
import net.javaguide.springboot.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
     
	private AuthenticationManager authenticationManager;
	
	private UserRepository  userRepository;
	private RoleRepository  roleRepository;
	private PasswordEncoder passwordEncoder;
	
	
    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository,
			RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		super();
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}

//    public AuthServiceImpl() {
//        // Default constructor
//    }
    
    //this was for only login implementation
//	public AuthServiceImpl(AuthenticationManager authenticationManager) {
//		this.authenticationManager = authenticationManager;
//	}

	@Override
	public String login(LoginDto loginDto) {
	Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
			(loginDto.getUsernameOrEmail(), loginDto.getPassword()));
		
	SecurityContextHolder.getContext().setAuthentication(authenticate);
	
	return "User logged-In successfully.";
	}

	@Override
	public String signUp(SignUpDto signUpDto) {

		//add check for exist in Database
		if(userRepository.existsByUsername(signUpDto.getUsername())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "UserName is already exists!.");
		}
		
		//add check for email exist in database
		if(userRepository.existsByEmail(signUpDto.getEmail())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Email is already exist in database");
		}
		User user = new User();
		user.setName(signUpDto.getName());
		user.setUsername(signUpDto.getUsername());
		user.setEmail(signUpDto.getEmail());
		user.setPassword( passwordEncoder.encode(signUpDto.getPassword()));
		
		Set<Role> roles = new HashSet<>();
		Role userRole = roleRepository.findByName("ROLE_USER").get();
		roles.add(userRole);
		user.setRoles(roles);
		
		userRepository.save(user);
		return "user registerd or signUp successfully!. ";
	}

}
