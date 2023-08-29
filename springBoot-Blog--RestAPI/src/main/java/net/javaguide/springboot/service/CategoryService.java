package net.javaguide.springboot.service;

import java.util.List;

import net.javaguide.springboot.payload.CategoryDto;

public interface CategoryService {
 
	CategoryDto addCategory(CategoryDto categoryDto);
	
	CategoryDto getCategory(Long categoryId);
	
	List<CategoryDto> getAllCategories();
	
	CategoryDto updatedCategory(CategoryDto categoryDto, Long CategoryId);
	
	void deleteCategoryById(Long cataegoryId);
}
