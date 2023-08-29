package net.javaguide.springboot.serviceImpl;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import net.javaguide.springboot.entity.Category;
import net.javaguide.springboot.entity.Post;
import net.javaguide.springboot.exception.ResourceNotFoundException;
import net.javaguide.springboot.payload.PostDto;
import net.javaguide.springboot.payload.PostResponse;
import net.javaguide.springboot.repository.CategoryRepository;
import net.javaguide.springboot.repository.PostRepository;
import net.javaguide.springboot.service.PostService;


@Service
public class PostServicesImpl implements PostService {
      
	private PostRepository postRepo;
	private ModelMapper mapper;   //video-1:05:02 sec
	
	private CategoryRepository categoryRepo;
	
//	public PostServicesImpl(PostRepository postRepo) {
//		this.postRepo = postRepo;
//		
//	}
	//this is constructor based injection //video-1:05:02 sec
	public PostServicesImpl(PostRepository postRepo, ModelMapper mapper,
			                            CategoryRepository categoryRepo) {
		this.postRepo = postRepo;
		this.mapper = mapper;
		this.categoryRepo = categoryRepo;
	}
	
	@Override
	public PostDto CreatePost(PostDto postDto) {
	
		Category category = categoryRepo.findById(postDto.getCategoryId()).orElseThrow(()->
		        new ResourceNotFoundException("Category", "id", postDto.getCategoryId()));
		
		//convert DTO to entity
        Post post = mapToEntity(postDto);
        post.setCategory(category);
    
        Post postEntity = postRepo.save(post);

        //convert entity to DTO
        PostDto dto =  mapToDto(postEntity);
        return dto;
	}
	@Override //pagignation
	public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy,String sortDir) {
	Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending(): 
			Sort.by(sortBy).descending();
		
		Pageable pageable = PageRequest.of(pageNo, pageSize,sort);
        Page<Post> posts = postRepo.findAll(pageable);
        List<Post> content = posts.getContent();
//return content.stream().map(post -> mapToDto(post)).collect(Collectors.toList());//it is without response
    List<PostDto> contents = content.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
    
    PostResponse  postResponse = new PostResponse();
    
    postResponse.setContent(contents);
    postResponse.setPageNo(posts.getNumber());
    postResponse.setPageSize(posts.getSize());
    postResponse.setTotalPages(posts.getTotalPages());
    postResponse.setTotalElement(posts.getTotalElements());
    postResponse.setLast(posts.isLast());
    return postResponse ;
	}
	
	@Override
	public PostDto getPostById(long id) {
     Post post = postRepo.findById(id).orElseThrow(
    		 () -> new ResourceNotFoundException("post","id",id)
    );
	  PostDto postDto = mapToDto(post);
     return postDto;
	}
	
	//convert entity into DTO
    public Post mapToEntity(PostDto postDto) {
    	Post post = mapper.map(postDto, Post.class); //this is advantage of mapper liberrary
    	
//    	 Post post = new Post();
//         post.setTitle(postDto.getTitle());
//         post.setDescription(postDto.getDescription());
//         post.setContent(postDto.getContent());
    	 return post ;
    }
    
    public PostDto mapToDto(Post post) {
    	PostDto dto = mapper.map(post, PostDto.class); ////this is advantage of mapper liberrary
    	
//    	 PostDto dto  = new PostDto();
//         dto.setId(post.getId());
//         dto.setTitle(post.getTitle());
//         dto.setDescription(post.getDescription());
//         dto.setContent(post.getContent());
      
    	return dto;
    }


	@Override
	public PostDto updatePost(PostDto postDto, long id) {
		
		//get postById from the database
		Post post = postRepo.findById(id).orElseThrow(
				() ->new ResourceNotFoundException("Post","id",id)
				);
		
		Category category = categoryRepo.findById(postDto.getCategoryId()).orElseThrow(
				()-> new ResourceNotFoundException("Category", "id", postDto.getCategoryId()));
       
		post.setTitle(postDto.getTitle());
       post.setDescription(postDto.getDescription());
       post.setContent(postDto.getContent());
       post.setCategory(category);//we can also update the category
       
       
       Post newPost = postRepo.save(post);
      return mapToDto(newPost);
	} 


	@Override
	public void deletePost(@PathVariable("id") long id) {
      postRepo.findById(id).orElseThrow(
    		  ()-> new ResourceNotFoundException("Post","id",id)
    		  );		
      postRepo.deleteById(id);
      }

	@Override
	public List<PostDto> getPostByCategoryId(Long categoryId) {
		     Category category = categoryRepo.findById(categoryId).orElseThrow(()->
		                      new ResourceNotFoundException("Category", "id", categoryId));
		List<Post> posts = postRepo.findByCategoryId(categoryId);
		 
		//convert posts to postDto    
		return posts.stream().map((post)-> mapToDto(post)).collect(Collectors.toList());
	}
}
