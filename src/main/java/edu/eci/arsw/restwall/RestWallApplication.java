package edu.eci.arsw.restwall;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RestWallApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestWallApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper() {
	 return new ModelMapper();
	}

}
