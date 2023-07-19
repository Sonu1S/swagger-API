package net.javaguide.sprinboot.miceroservices.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class User {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)//autoincrement
     private long id;
	 @Column(nullable = false)
     private String firstName;
	 @Column(nullable=false)
     private String lastName;
	 @Column(nullable=false,unique=true)
     private String email;
     
	 

     public User() {
		super();
		// TODO Auto-generated constructor stub
	}


	public User(long id, String userFirstName, String userLastName, String email) {
		super();
		this.id = id;
		this.firstName = userFirstName;
		this.lastName = userLastName;
		this.email = email;
	}


	public long getId() {
		return id;
	}


	public String getFirstName() {
		return firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setId(long id) {
		this.id = id;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
  
}
