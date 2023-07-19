package net.javaguide.sprinboot.miceroservices.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguide.sprinboot.miceroservices.entity.User;

public interface UserRepositroy extends JpaRepository<User,Long>{
   Optional<User> findByEmail(String email); //findById () return type is optional
}
