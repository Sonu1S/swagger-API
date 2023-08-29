package net.javaguide.springboot.service;

import java.util.List;

import net.javaguide.springboot.payload.PostDto;
import net.javaguide.springboot.payload.PostResponse;

public interface PostService {
  PostDto CreatePost(PostDto postDto);

 //List<PostDto> getAllPosts(); use pagignation and shorting

PostDto getPostById(long id);

PostDto updatePost(PostDto postDto, long id);

void deletePost(long id);

PostResponse getAllPosts(int pageNo, int pageSize, String sortBy,String sortDir);
	
List<PostDto> getPostByCategoryId(Long categoryId);
}
 
