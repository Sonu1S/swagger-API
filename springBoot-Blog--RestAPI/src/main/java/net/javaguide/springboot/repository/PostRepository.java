package net.javaguide.springboot.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.javaguide.springboot.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	
	// Query method with JPQL query to find categories by categoryId
//    @Query("SELECT c FROM Category c WHERE c.id = :categoryId")
//    List<Post> findByCategoryId(@Param("categoryId") Long categoryId);

    	List<Post> findByCategoryId(Long categoryId);
}
