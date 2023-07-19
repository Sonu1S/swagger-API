package net.javaguide.sprinboot.miceroservices.service;

import java.util.List;

import net.javaguide.sprinboot.miceroservices.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto user);
    
    UserDto getUserById(Long userId);
    
    List<UserDto> getAllUsers();
    
    UserDto updateUser(UserDto user);
    
   void deleteUser(Long userId); 
   void deleteAllUser();
}
