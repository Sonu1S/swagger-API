package net.javaguide.springboot.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="comments")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String body;
	private String email;
	private String name;
	
	public Comment() {
		super();
	}
	public Comment(long id, String body, String email, String name) {
		super();
		this.id = id;
		this.body = body;
		this.email = email;
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
			@ManyToOne(fetch=FetchType.LAZY)
			@JoinColumn(name="post_id",nullable = false)
			private Post post;
			
			public Post getPost() {
				return post;
			}
			public void setPost(Post post) {
				this.post = post;
	}

}
