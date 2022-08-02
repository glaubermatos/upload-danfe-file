package com.glauber.nfeprocessservice.infrastructure.service.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.glauber.nfeprocessservice.core.storage.StorageProperties;
import com.glauber.nfeprocessservice.domain.service.NotaFiscalStorageService;

@Service
public class LocalStorageServiceImpl implements NotaFiscalStorageService {
	
	private static final Logger logger = LoggerFactory.getLogger(LocalStorageServiceImpl.class);

	@Autowired
	private StorageProperties storageProperties;
	
	@Override
	public List<File> loadFiles() {
		try {
			 Path path = getPathInputDirectory();
			 
			 File file = path.toFile();
			 
			 return Arrays.asList(file.listFiles());
		 
		} catch (IOException e) {
			throw new StorageException("Não foi possível recuperar arquivos do diretório", e);
		}
		
	}

	@Override
	public void moveToOutputDirectory(File xml) {
		String fileName = xml.getName();
		try {
			var diretorio = getPathOutputDirectory();
			
			Path pathOutputDirectory = diretorio.resolve(fileName);
			
			FileCopyUtils.copy(xml, pathOutputDirectory.toFile());
			Files.deleteIfExists(getPathInputDirectory().resolve(fileName));
			
			logger.info(String.format("Arquivo %s movido para o diretório %s", fileName, diretorio));
			
		} catch (IOException e) {
			throw new StorageException(String.format("Arquivo %s não pôde ser enviado para o diretório de destino", fileName), e);
		}
	}
	
	@Override
	public void moveToErrorDirectory(File xml) {
		String fileName = xml.getName();
		try {
			var diretorio = getPathErrorDirectory();
			
			Path pathErrorDirectory = diretorio.resolve(fileName);
			
			FileCopyUtils.copy(xml, pathErrorDirectory.toFile());
			Files.deleteIfExists(getPathInputDirectory().resolve(fileName));
			
			logger.info(String.format("Arquivo %s movido para o diretório %s", fileName, diretorio));
			
		} catch (IOException e) {
			throw new StorageException(String.format("Arquivo %s não pôde ser enviado para o diretório de destino", fileName), e);
		}
	}
	
	@Override
	public void removeFileFromInputDirectory(String fileName) {
		try {
			var diretorio = getPathInputDirectory();
			
			Files.deleteIfExists(diretorio.resolve(fileName));
			
			logger.info(String.format("Arquivo %s removido do diretório %s", fileName, diretorio));
			
		} catch (IOException e) {
			throw new StorageException(String.format("Arquivo %s não pôde ser removido", fileName), e);
		}
	}
	
	@Override
	public void removeFileFromOutputDirectory(String fileName) {
		try {
			var diretorio = getPathOutputDirectory();
			
			Files.deleteIfExists(diretorio.resolve(fileName));
			
			logger.info(String.format("Arquivo %s removido do diretório %s", fileName, diretorio));
			
		} catch (IOException e) {
			throw new StorageException(String.format("Arquivo %s não pôde ser removido", fileName), e);
		}
	}
	
	@Override
	public void removeFileFromErrorDirectory(String fileName) {
		try {
			var diretorio = getPathErrorDirectory();
			
			Files.deleteIfExists(diretorio.resolve(fileName));
			
			logger.info(String.format("Arquivo %s removido do diretório %s", fileName, diretorio));
			
		} catch (IOException e) {
			throw new StorageException(String.format("Arquivo %s não pôde ser removido", fileName), e);
		}
	}
	
	private Path getPathInputDirectory() throws IOException {
		return Path.of(storageProperties.getLocal().getXmlInputDirectory());
	}
	
	private Path getPathOutputDirectory() throws IOException {
		return Path.of(storageProperties.getLocal().getXmlOutputDirectory());
	}
	
	private Path getPathErrorDirectory() throws IOException {
		return Path.of(storageProperties.getLocal().getXmlErrorDirectory());
	}

}
