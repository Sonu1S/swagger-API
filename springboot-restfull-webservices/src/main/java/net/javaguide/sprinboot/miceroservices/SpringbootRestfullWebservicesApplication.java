package net.javaguide.sprinboot.miceroservices;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info  //it is a general information about swagger
		(
			title ="Sprin boot RestAPI Documentation",
			description = "spring Boot Rest API Documentation",
			version="v1.0",
			contact=@Contact
			(
					name="Sonu",
					email="rakeshkrchy1112@gmail.com",
					url="https://javaguides.net"
			),
			license = @License
			(
					name ="Apache 2.0",
					url = "https://javaguides.net/license"
			)
		),	
		externalDocs = @ExternalDocumentation( //it is a external documentaion details
				description = "Spring Boot User Management Documentation",
				url="https://javaguides.net/user_management.html"
				)
		)

public class SpringbootRestfullWebservicesApplication {
   
	@Bean
     public ModelMapper modelMapper() {
    	 return new ModelMapper();
     }
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootRestfullWebservicesApplication.class, args);
	}

}
