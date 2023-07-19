package net.javaguide.sprinboot.miceroservices.mapper;

import net.javaguide.sprinboot.miceroservices.dto.UserDto;
import net.javaguide.sprinboot.miceroservices.entity.User;

public class UserMapper {
	//user JPA entity into user Dto
    public static UserDto mapToUserDto(User user) {
		UserDto userDto = new UserDto(
		user.getId(),user.getFirstName(),user.getLastName(),user.getEmail()
				);
    	return userDto;
    }
    
  //convert userDto into userEntity
    public static User mapToUserEntity(UserDto userDto) {
		User user = new User(
				userDto.getId(),userDto.getFirstName(),userDto.getLastName(),userDto.getEmail()
				);
    	
    	return user;
    	
    }
    
}
