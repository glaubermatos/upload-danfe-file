package com.glauber.nfeuploadservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.glauber.nfeuploadservice.core.storage.StorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({StorageProperties.class})
public class NfeUploadServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NfeUploadServiceApplication.class, args);
	}

}
