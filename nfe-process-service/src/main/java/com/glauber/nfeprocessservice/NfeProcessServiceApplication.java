package com.glauber.nfeprocessservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.glauber.nfeprocessservice.core.storage.StorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({StorageProperties.class})
public class NfeProcessServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NfeProcessServiceApplication.class, args);
	}

}
