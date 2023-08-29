package net.javaguide.springboot.payload;


import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PostDto {
	 private long id;
	  
	  @NotNull
	  @Size(min=2, message = "Post title should have at least 2 character")
	  private String title;
	  
	  @NotNull
	  @Size(min=10, message = " Post description should have at least 10 chracters or more ")
	  private String description;
	  
	  @NotNull  
	  @NotEmpty(message = "Post Conetent Name Shoud Not be Empty")
	  private String content;
	  
	  private Set<CommentDto> comments;
	  
	  private Long categoryId;

	  
	public PostDto() {
		super();
	}

	public PostDto(long id,
			@NotNull @Size(min = 2, message = "Post title should have at least 2 character") String title,
			@NotNull @Size(min = 10, message = " Post description should have at least 10 chracters or more ") String description,
			@NotNull @NotEmpty(message = "Post Conetent Name Shoud Not be Empty") String content,
			Set<CommentDto> comments, Long categoryId) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.content = content;
		this.comments = comments;
		this.categoryId = categoryId;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getContent() {
		return content;
	}

	public Set<CommentDto> getComments() {
		return comments;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setComments(Set<CommentDto> comments) {
		this.comments = comments;
	}
	  
		
	
}