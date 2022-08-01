package com.glauber.nfeprocessservice.core.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.glauber.nfeprocessservice.domain.service.NotaFiscalStorageService;
import com.glauber.nfeprocessservice.infrastructure.service.storage.LocalStorageServiceImpl;

@Configuration
public class StorageConfig {
	
	@Autowired
	private StorageProperties storageProperties;

	@Bean
	public NotaFiscalStorageService notaFiscalStorageService() {
		switch (storageProperties.getType()) {
			default:
				return new LocalStorageServiceImpl();
		}
	}
}
