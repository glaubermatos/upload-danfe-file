package com.glauber.nfeuploadservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import com.glauber.nfeuploadservice.core.storage.StorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({StorageProperties.class})
@EnableEurekaClient
public class NfeUploadServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NfeUploadServiceApplication.class, args);
	}

}
