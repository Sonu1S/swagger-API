package net.javaguide.sprinboot.miceroservices.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Schema( //We are providing User Dto model information
		description = "UserDto Model Information"
		)
public class UserDto {  //Validation is Not Working

	private long id;
	
	@Schema(
			description = "User First Name"
			)
	//user First Name Should not be null and Empty
	@NotNull
    @NotEmpty(message = "User First Name Shoud Not be Empty")
	@jakarta.validation.constraints.Size(min=3)
	private String firstName;
	
	@Schema(
			description = "User Lirst Name"
			)
  //user Lirst Name Should not be null and Empty
	@NotNull
	@NotEmpty(message = "User First Name Shoud Not be Empty")
	private String lastName;
	
	@Schema(
			description = "User Email Id"
			)
  //user Email Should not be null and Empty and email address should be valid
	@NotNull
	@NotEmpty( message = "EmailAddress should be Valid")
    @Email
	private String email;
	
	public UserDto() {
		super();
	}

	public UserDto(long id, String firstName, String lastName, String email) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
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
