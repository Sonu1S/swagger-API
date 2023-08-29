package net.javaguide.springboot.service;


import java.util.List;

import net.javaguide.springboot.payload.CommentDto;

public interface CommentService {
   CommentDto createComment(long postId, CommentDto commentDto);
   
   List<CommentDto> getCommentByPostId(long postId);
   
   CommentDto updateComment(long postId, long id, CommentDto commentDto);

   void deleteComment(long postId, long commentId);

}
