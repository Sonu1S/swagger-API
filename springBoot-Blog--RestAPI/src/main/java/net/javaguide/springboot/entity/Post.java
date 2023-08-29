package net.javaguide.springboot.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name ="posts",uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
    
    @Column(name="title",nullable=false)
    private String title;
    
    @Column(name="description",nullable=false)
    private String description;
   
    @Lob
    @Column(name="content",nullable=false)
	private String content; //256
    
    @OneToMany(mappedBy="post",cascade = CascadeType.ALL,orphanRemoval = true)
    Set<Comment> comments = new HashSet<>();
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id") //Foreign Key
    private Category category;
    
    
    
	public Set<Comment> getComments() {
		return comments;
	}

	public Category getCategory() {
		return category;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Post() { //dafult constructor
		super();
	}

	public Post(long id, String title, String description, String content) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.content = content;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
    
	
}
