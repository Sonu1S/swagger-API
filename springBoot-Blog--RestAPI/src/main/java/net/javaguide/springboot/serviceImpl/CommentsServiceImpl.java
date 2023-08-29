package net.javaguide.springboot.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import net.javaguide.springboot.entity.Comment;
import net.javaguide.springboot.entity.Post;
import net.javaguide.springboot.exception.ResourceNotFoundException;
import net.javaguide.springboot.payload.CommentDto;
import net.javaguide.springboot.repository.CommentRepository;
import net.javaguide.springboot.repository.PostRepository;
import net.javaguide.springboot.service.CommentService;

@Service
public class CommentsServiceImpl implements CommentService{

	private CommentRepository commentRepository;
	private PostRepository postRepositroy;
	private ModelMapper mapper;
	private CommentDto map;
	
	public CommentsServiceImpl(CommentRepository commentRepository, PostRepository postRepositroy,ModelMapper mapper) {
		super();
		this.commentRepository = commentRepository;
		this.postRepositroy = postRepositroy;
		this.mapper = mapper;
	}

	@Override
	public CommentDto createComment(long postId, CommentDto commentDto) {
		Post post = postRepositroy.findById(postId).orElseThrow(
				()-> new ResourceNotFoundException("post","id", postId)
				);
		
	Comment comment = mapToEntity(commentDto);
	
	comment.setPost(post);
	
	Comment newComment = commentRepository.save(comment);
	return mapToDto(newComment);
	
	}


	Comment mapToEntity(CommentDto commentDto) {
	Comment comment = mapper.map(commentDto, Comment.class); //this is the advantage of mapper liberrary
		
//		Comment comment = new Comment();
//		comment.setName(commentDto.getName());
//		comment.setEmail(commentDto.getEmail());
//		comment.setBody(commentDto.getBody());
	   
		return comment;	
	   }
	
	CommentDto mapToDto(Comment comment) {
		CommentDto commentDto = mapper.map(comment, CommentDto.class);
		
//		CommentDto commentDto = new CommentDto();
//		commentDto.setId(comment.getId());
//		commentDto.setName(comment.getName());
//		commentDto.setEmail(comment.getEmail());
//		commentDto.setBody(comment.getBody());
		
		return commentDto;
	}

	@Override
	public List<CommentDto> getCommentByPostId(long postId) {
     List<Comment> comments = commentRepository.findByPostId(postId);
     return comments.stream().map(comment->mapToDto(comment)).collect(Collectors.toList());
     
    
	}

	@Override //here we check this commnet belongs to this post or not
	public CommentDto updateComment(long postId, long id, CommentDto commentDto) {
      Post post = postRepositroy.findById(postId).orElseThrow( 
    		   () -> new ResourceNotFoundException("post","id", postId));		
		
      Comment comment = commentRepository.findById(id).orElseThrow(
				()-> new ResourceNotFoundException("comment", "id", id));
		
		comment.setName(commentDto.getName());
		comment.setEmail(comment.getEmail());
		comment.setBody(comment.getBody());
		
		Comment updatedComment = commentRepository.save(comment);
		
        return mapToDto(updatedComment);
	}

	@Override
	public void deleteComment(long postId, long commentId) {
     Post post = postRepositroy.findById(postId).orElseThrow(
    		 ()-> new ResourceNotFoundException("post", "id", postId));	
     
    Comment comment = commentRepository.findById(commentId).orElseThrow(
    		 ()-> new ResourceNotFoundException("comment", "id" , commentId));
   
    commentRepository.deleteById(commentId);
	}
}