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
public class LocalXmlNotaFiscalStorageServiceImpl implements NotaFiscalStorageService {
	
	@Autowired
	private StorageProperties storageProperties;

	@Override
	public void store(XmlWrapper xmlWrapper) {
		try {
			Path newXmlFilePath = getFilePath(xmlWrapper.getFileName());
			var xmlInputStream = xmlWrapper.getInputStream();
			
			FileCopyUtils.copy(xmlInputStream, Files.newOutputStream(newXmlFilePath));
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private Path getFilePath(String xmlFileName) {
		try {
			String inputDirectoryName = storageProperties.getLocal().getXmlInputDirectory();
			
			Path pathInputDirectory = Path.of(inputDirectoryName);
			
			return pathInputDirectory.resolve(xmlFileName);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
