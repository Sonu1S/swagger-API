package net.javaguide.springboot.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

import net.javaguide.springboot.entity.Category;
import net.javaguide.springboot.payload.CategoryDto;
import net.javaguide.springboot.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
   
	private CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}
	
	//build Add Category REST API
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto){
		CategoryDto savedCategory = categoryService.addCategory(categoryDto);
		return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
		
	}
	
	//Build Get Category REST API
	@GetMapping("{id}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable("id") Long categoryId){
		CategoryDto categoryDto = categoryService.getCategory(categoryId);
		return ResponseEntity.ok(categoryDto);
		//return new ResponseEntity<>(categoryDto, HttpStatus.OK);
		
	}
	
	//Build GetAll Category REST API
	@GetMapping
	public ResponseEntity<List<CategoryDto>> getCategories(){
//		List<CategoryDto> listAllCategory = categoryService.getAllCategories();
//		return ResponseEntity.ok( listAllCategory);
		
//      this is for print the value on console
//		Category category = new Category();
//		category.setId(1);
//		category.setName("sonu");
//		category.setDescription("HII");
//		System.out.println(category.getId());
//		System.out.println(category.getDescription());
//		System.out.println(category.getName());
		
		return ResponseEntity.ok(categoryService.getAllCategories());
	}

	
	  //Build Update Category REST API
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("{id}")
     public ResponseEntity<CategoryDto> updatedCategory(@RequestBody CategoryDto categoryDto,
    		 @PathVariable("id") Long categoryId){
    	 CategoryDto updatedCategory = categoryService.updatedCategory(categoryDto, categoryId);
		return ResponseEntity.ok(updatedCategory);
    	 
     }
	
	//Build Delete Category REST API
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteCategory(@PathVariable("id") Long categoryId){
		categoryService.deleteCategoryById(categoryId);
		return ResponseEntity.ok("Category deleted successfully!.");
		
	}
	
}
