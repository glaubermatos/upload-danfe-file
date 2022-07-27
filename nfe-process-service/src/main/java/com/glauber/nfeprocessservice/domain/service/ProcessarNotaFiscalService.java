package com.glauber.nfeprocessservice.domain.service;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

@Service
@EnableScheduling
public class ProcessarNotaFiscalService {
	
	private static final long DELAY_2_MIN = (1000 * 60) * 2 ;

	@Scheduled(fixedDelay = DELAY_2_MIN)
	public void process() {
		try {
			String fileName = "26161413000141_202100000000062.xml";
			
			System.out.println("Processando nota fiscal....");
			
			Path xmlPath = getFilePath(fileName);
			
			InputStream inputStream = Files.newInputStream(xmlPath);
			
			FileCopyUtils.copy(inputStream, Files.newOutputStream(Path.of("../output/"+fileName)));
			Files.deleteIfExists(xmlPath);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	private Path getFilePath(String fileName) {
		Path path = Path.of("../input/" + fileName);
		return path;
	}
}
