package net.javaguide.springboot.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import net.javaguide.springboot.entity.Category;
import net.javaguide.springboot.exception.ResourceNotFoundException;
import net.javaguide.springboot.payload.CategoryDto;
import net.javaguide.springboot.repository.CategoryRepository;
import net.javaguide.springboot.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	private CategoryRepository categoryRepository;
	private ModelMapper modelMapper;
	
	public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
		super();
		this.categoryRepository = categoryRepository;
		this.modelMapper = modelMapper;
	}


	@Override
	public CategoryDto addCategory(CategoryDto categoryDto) {
        
		Category category = modelMapper.map(categoryDto, Category.class);
		Category saveCategory = categoryRepository.save(category);
		return modelMapper.map(saveCategory, CategoryDto.class);
	}


	@Override
	public CategoryDto getCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()->
        new ResourceNotFoundException("Category", "id", categoryId));
		return modelMapper.map(category, CategoryDto.class);
	}
	
	
	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category> categories = categoryRepository.findAll();
		return categories.stream().map((category)-> modelMapper.map(category, CategoryDto.class))
				.collect(Collectors.toList());
	}
	
	@Override
	public CategoryDto updatedCategory(CategoryDto categoryDto, Long categoryId) {
      
		Category category = categoryRepository.findById(categoryId).orElseThrow(()->
		                     new ResourceNotFoundException("Category", "id", categoryId));
		category.setName(categoryDto.getName());
		category.setDescription(categoryDto.getDescription());
		category.setId(categoryId);
		
		Category updatedCategory = categoryRepository.save(category);
		
		return modelMapper.map(updatedCategory, CategoryDto.class);
	}
	@Override
	public void deleteCategoryById(Long categoryId) {
      Category category = categoryRepository.findById(categoryId).orElseThrow(()->
                             new ResourceNotFoundException("Category", "id" , categoryId));		
	
      categoryRepository.delete(category);
	}
}
