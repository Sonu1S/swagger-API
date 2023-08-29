package net.javaguide.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguide.springboot.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
