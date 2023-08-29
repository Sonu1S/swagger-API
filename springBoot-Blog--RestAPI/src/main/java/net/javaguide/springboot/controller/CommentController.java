package net.javaguide.springboot.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import net.javaguide.springboot.payload.CommentDto;
import net.javaguide.springboot.service.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {
  
	private CommentService commentService;
	
	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	//localhost:8080/api/posts/2/comments
	@PostMapping("posts/{postId}/comments")
	public ResponseEntity<CommentDto> createCommnet(@PathVariable("postId") long postId,
			@Valid @RequestBody CommentDto commentDto){
		CommentDto dto = commentService.createComment(postId, commentDto);
		return new ResponseEntity<>(dto,HttpStatus.CREATED);
	}
	
	//localhost:8080/api/posts/1/comments
	@GetMapping("posts/{postId}/comments")
	public List<CommentDto> getCommentsByPostId(@PathVariable(value="postId") long postId){
		return commentService.getCommentByPostId(postId);
		
	}
	
	@GetMapping("/posts/{postId}/comments/{id}")
//	public ResponseEntity<CommentDto> getCommentById(
//			@PathVariable(value = "postId")Long postId,
//			@PathVariable(value = "id") Long commentId){
//		CommentDto commentDto = commentService.getCommentById(postId, commentId);
//			return new ResponseEntity<>(commentDto, HttpStatus.OK);
//		}
		
	//localhost:8080/api/posts/1/comments/2
	@PutMapping("/posts/{postId}/comments/{id}")
	public ResponseEntity<CommentDto> updateComment(@PathVariable(value="postId") long postId,
			@PathVariable(value="id")long commentId, 
			@Valid @RequestBody CommentDto commentDto){
		
		CommentDto updateComment = commentService.updateComment(postId, commentId, commentDto);
		  
		return new ResponseEntity<>(updateComment, HttpStatus.OK);
	}
	
	//localhost:8080/api/posts/2/comments/1
	@DeleteMapping("posts/{postId}/comments/{id}")
	public ResponseEntity<String> deleteComment(
			  @PathVariable("postId") long postId,
	          @PathVariable("id") long commentId
			){
		
		commentService.deleteComment(postId,commentId);
		return new ResponseEntity<String>("Comment is deleted!", HttpStatus.OK);
		
	}
}

