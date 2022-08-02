package com.glauber.nfeuploadservice.core.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.glauber.nfeuploadservice.domain.service.NotaFiscalStorageService;
import com.glauber.nfeuploadservice.infrastructure.service.storage.LocalNotaFiscalStorageServiceImpl;

@Configuration
public class StorageConfig {
	
	@Autowired
	private StorageProperties storageProperties;
	
	@Bean
	public NotaFiscalStorageService notaFiscalStorageService() {
		switch (storageProperties.getType()) {
			case LOCAL:
				return new LocalNotaFiscalStorageServiceImpl();
	
			default:
				return null;
		}
	}

}
