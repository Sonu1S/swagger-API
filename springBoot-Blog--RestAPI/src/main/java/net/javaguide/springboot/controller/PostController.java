package net.javaguide.springboot.controller;



import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import net.javaguide.springboot.entity.Post;
import net.javaguide.springboot.payload.PostDto;
import net.javaguide.springboot.payload.PostResponse;
import net.javaguide.springboot.service.PostService;
import net.javaguide.springboot.utils.AppConstants;

@RestController
@RequestMapping("/api/posts")
public class PostController {
   
	private PostService postservice;

	public PostController(PostService postservice) {
		super();
		this.postservice = postservice;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping //localhost:8080/api/posts
	public ResponseEntity<Object> createPost(@Valid @RequestBody PostDto postDto/*
																				 * , BindingResult bindingResult
																				 */) {
//		if(bindingResult.hasErrors()) {
//		  return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
//		}
	  return new ResponseEntity<>(postservice.CreatePost(postDto),HttpStatus.CREATED);
	} 
	
	//localhost:8080/api/posts?pageNo=0&pageSize=5
	//localhost:8080/api/posts?pageNo=0&pageSize=5&sortBy=title
	//localhost:8080/api/posts?pageNo=0&pageSize=5&sortBy=id&sortDir=desc
	@GetMapping 
	public PostResponse getAllPosts(
		@RequestParam(value = "pageNo",  defaultValue   = AppConstants.DEFAULT_PAGE_NUMBER,  required = false) int pageNo,
		@RequestParam(value = "pageSize",defaultValue   = AppConstants.DEFAULT_PAGE_SIZE,    required = false) int pageSize,
		@RequestParam(value = "sortBy",  defaultValue   = AppConstants.DEFAULT_SORT_BY,      required = false) String sortBy,
		@RequestParam(value = "sortDir", defaultValue   = AppConstants.DEFAULT_SORT_DIR,     required = false) String sortDir
		  ){
//		List<PostDto> postDto = postservice.getAllPosts();
//		return postDto;
		return postservice.getAllPosts(pageNo, pageSize, sortBy,sortDir);//we can  direcly written this
	}
	
	@GetMapping("/{id}")//localhost:8080/api/posts/1
	public ResponseEntity<PostDto> getPostById(@PathVariable("id") long id){
		return ResponseEntity.ok(postservice.getPostById(id));
		
	}
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}") //localhost:8080/api/posts/5
	public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto,@PathVariable("id") long id){
		PostDto dto = postservice.updatePost(postDto,id);
		return new ResponseEntity<PostDto>(dto,HttpStatus.OK);
		
	}
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")//localhost:8080/api/posts/5
    public ResponseEntity<String> deletePost(@PathVariable("id") long id){
      postservice.deletePost(id);
      return new ResponseEntity<>("Post Entity deleted successfully." ,HttpStatus.OK);
  	
    }
	
	@GetMapping("/category/{id}")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable("id") Long categoryId) {
        List<PostDto> postDtos = postservice.getPostByCategoryId(categoryId);
        return ResponseEntity.ok(postDtos);
    }
  }
  