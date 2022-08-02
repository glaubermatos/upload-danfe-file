package com.glauber.nfeprocessservice.infrastructure.service.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.glauber.nfeprocessservice.core.storage.StorageProperties;
import com.glauber.nfeprocessservice.domain.service.NotaFiscalStorageService;

@Service
public class LocalStorageServiceImpl implements NotaFiscalStorageService {

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
	public File findFileBy(String fileName) {
		try {
			Path path = getPathInputDirectory().resolve(fileName);
			
			return path.toFile();
			
		} catch (IOException e) {
			throw new StorageException(String.format("Arquivo %s não pôde ser encontrado", fileName), e);
		}
	}

	@Override
	public void moveToOutputDirectory(File xml) {
		String fileName = xml.getName();
		try {
			
			Path pathOutputDirectory = getPathOutputDirectory().resolve(fileName);
			
			FileCopyUtils.copy(xml, pathOutputDirectory.toFile());
			Files.deleteIfExists(getPathInputDirectory().resolve(fileName));
			
		} catch (IOException e) {
			throw new StorageException(String.format("Arquivo %s não pôde ser enviado para o diretório de destino", fileName), e);
		}
	}
	
	@Override
	public void moveToErrorDirectory(File xml) {
		String fileName = xml.getName();
		try {
			
			Path pathErrorDirectory = getPathErrorDirectory().resolve(fileName);
			
			FileCopyUtils.copy(xml, pathErrorDirectory.toFile());
			Files.deleteIfExists(getPathInputDirectory().resolve(fileName));
			
		} catch (IOException e) {
			throw new StorageException(String.format("Arquivo %s não pôde ser enviado para o diretório de destino", fileName), e);
		}
	}
	
	@Override
	public void removeFileFromInputDirectory(String fileName) {
		try {
			Files.deleteIfExists(getPathInputDirectory().resolve(fileName));
		} catch (IOException e) {
			throw new StorageException(String.format("Arquivo %s não pôde ser removido", fileName), e);
		}
	}
	
	@Override
	public void removeFileFromOutputDirectory(String fileName) {
		try {
			Files.deleteIfExists(getPathOutputDirectory().resolve(fileName));
		} catch (IOException e) {
			throw new StorageException(String.format("Arquivo %s não pôde ser removido", fileName), e);
		}
	}
	
	@Override
	public void removeFileFromErrorDirectory(String fileName) {
		try {
			Files.deleteIfExists(getPathErrorDirectory().resolve(fileName));
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
