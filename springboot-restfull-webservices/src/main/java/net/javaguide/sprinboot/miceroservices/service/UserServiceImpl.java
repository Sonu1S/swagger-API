package net.javaguide.sprinboot.miceroservices.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import net.javaguide.sprinboot.miceroservices.dto.UserDto;
import net.javaguide.sprinboot.miceroservices.entity.User;
import net.javaguide.sprinboot.miceroservices.exception.EmailAlreadyExistException;
import net.javaguide.sprinboot.miceroservices.exception.ResourseNotFoundException;
import net.javaguide.sprinboot.miceroservices.mapper.UserMapper;
import net.javaguide.sprinboot.miceroservices.repository.UserRepositroy;

@Service
public class UserServiceImpl implements UserService {
     
	UserRepositroy userRepositroy;
	
	private ModelMapper modelMapper;
	
//	public UserServiceImpl(UserRepositroy userRepositroy) {
//		super();
//		this.userRepositroy = userRepositroy;
//	}

	public UserServiceImpl(UserRepositroy userRepositroy, ModelMapper modelMapper) {
		this.userRepositroy = userRepositroy;
		this.modelMapper = modelMapper;
	}



	@Override
	public UserDto createUser(UserDto userDto) {
		
		//Convert user Dto into user jpa entty
//	 User user = UserMapper.mapToUserEntity(userDto); //this is the Secound Way to convert Dto to entity
	     
		Optional<User> optionalUser = userRepositroy.findByEmail(userDto.getEmail());
		 if(optionalUser.isPresent()) {
			 throw new EmailAlreadyExistException("Email Already Exist for user");
		 }
		 
		 User user = modelMapper.map(userDto,User.class);
		 
//		User user = new User();   //This is the First Way to convert Dto to Entity
//		user.setId(userDto.getId());
//		user.setFirstName(userDto.getFirstName());
//		user.setLastName(userDto.getLastName());
//		user.setEmail(userDto.getEmail());
		
		User savedUser = userRepositroy.save(user);
		
		//convert user jpa entity into userDto  //This is the First Way to convert entity to dto
//		 UserDto savedUserDto = new UserDto(
//				 savedUser.getId(),savedUser.getFirstName(),savedUser.getLastName(),savedUser.getEmail()
//				 );
	//	UserDto savedUserDto = UserMapper.mapToUserDto(savedUser); //This is the Secound Way to convert to entity to Dto
		UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);
		 return savedUserDto;
	}


	@Override
	public UserDto getUserById(Long userId) {
		User user = userRepositroy.findById(userId).orElseThrow(
				()-> new ResourseNotFoundException("User", "id", userId)
				);
		
		//User user =  optionalUser.get();//this line is not required after Exception Hadndle concept
		//return UserMapper.mapToUserDto(user); //this is the secound way to convert UserDto to entity
	
		return modelMapper.map(user, UserDto.class);
	}


	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = userRepositroy.findAll();
   //return users.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());

    return users.stream().map((user)-> modelMapper.map(user, UserDto.class)).collect(
    		Collectors.toList());
	}


	@Override
	public UserDto updateUser(UserDto user) {
		User existingUser = userRepositroy.findById(user.getId()).orElseThrow(
				()->new ResourseNotFoundException("User","id",user.getId())
				);		
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		User updatedUser = userRepositroy.save(existingUser);
//		return UserMapper.mapToUserDto(updatedUser); //This is the secound way to convert Dto to Entity
		
		return modelMapper.map(updatedUser, UserDto.class);
	}

	@Override
	public void deleteUser(Long userId) {
		User existingUser = userRepositroy.findById(userId).orElseThrow(
				()->new ResourseNotFoundException("User","id",userId)
				);	
       userRepositroy.deleteById(userId);		
	}


	@Override
	public void deleteAllUser() {
     userRepositroy.deleteAll();		
	}

}
