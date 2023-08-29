package net.javaguide.springboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.javaguide.springboot.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	   
	   Optional<User> findByEmail(String email);  //findBy return optional
	   
	   @Query("SELECT u FROM User u WHERE u.username = :username OR u.email = :email")
	   Optional<User> findByUsernameOrEmail(@Param("username") String username, @Param("email") String email);

	  // Optional<User> findByUsernameOrEmail(String username, String email);
	   
	   Optional<User> findByUsername(String username);
	   
	   Boolean existsByUsername(String username); //existBy return type boolean
	   
	   Boolean existsByEmail(String email);

}
