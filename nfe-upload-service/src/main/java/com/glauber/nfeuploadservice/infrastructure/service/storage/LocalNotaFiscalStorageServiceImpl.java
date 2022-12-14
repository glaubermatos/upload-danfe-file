package com.glauber.nfeuploadservice.infrastructure.service.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.glauber.nfeuploadservice.core.storage.StorageProperties;
import com.glauber.nfeuploadservice.domain.service.NotaFiscalStorageService;

@Service
public class LocalNotaFiscalStorageServiceImpl implements NotaFiscalStorageService {
	
	@Autowired
	private StorageProperties storageProperties;

	@Override
	public void store(NotaFiscalWrapper notaFiscalWrapper) {
		try {
			Path xmlFilePath = getInputDirectoryPath(notaFiscalWrapper.getFileName());
			var xmlInputStream = notaFiscalWrapper.getInputStream();
			
			FileCopyUtils.copy(xmlInputStream, Files.newOutputStream(xmlFilePath));
			
		} catch (IOException e) {
			throw new StorageException("Não foi possível armazenar arquivo", e);
		}
			
	}
	
	private Path getInputDirectoryPath(String xmlFileName) {
		try {
			String inputDirectoryName = storageProperties.getLocal().getXmlInputDirectory();
			
			Path pathInputDirectory = Path.of(inputDirectoryName);
			
			return pathInputDirectory.resolve(xmlFileName);
		} catch (IOException e) {
			throw new StorageException("Não foi possível encontrar o diretório", e);
		}
	}

}
