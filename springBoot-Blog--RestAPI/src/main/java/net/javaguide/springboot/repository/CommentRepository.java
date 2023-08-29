package net.javaguide.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguide.springboot.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	   List<Comment> findByPostId(long postId);
	   
	}

