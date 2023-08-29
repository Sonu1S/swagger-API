package net.javaguide.springboot.payload;

public class CategoryDto {

   private long id;
   private String name;
   private String description;
	   
	   
	public CategoryDto() {
	super();
  }
	
	
	public CategoryDto(long id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}


	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	   
	   
}
