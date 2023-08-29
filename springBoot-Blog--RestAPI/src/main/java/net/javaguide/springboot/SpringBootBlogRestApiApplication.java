package net.javaguide.springboot;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootBlogRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBlogRestApiApplication.class, args);
           System.out.println("Sonu");
	}
	
	@Bean
    public ModelMapper modelMapper() {
    	return new ModelMapper();
    }
}