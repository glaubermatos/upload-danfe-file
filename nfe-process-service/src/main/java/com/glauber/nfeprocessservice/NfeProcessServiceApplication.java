package com.glauber.nfeprocessservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.glauber.nfeprocessservice.infrastructure.repository.CustomJpaRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class NfeProcessServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NfeProcessServiceApplication.class, args);
	}

}
