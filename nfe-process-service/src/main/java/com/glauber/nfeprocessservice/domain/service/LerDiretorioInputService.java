package com.glauber.nfeprocessservice.domain.service;

import java.io.File;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.glauber.nfeprocessservice.domain.model.NotaFiscal;
import com.glauber.nfeprocessservice.domain.model.StatusProcessamento;
import com.glauber.nfeprocessservice.domain.repository.NotaFiscalRepository;
import com.glauber.nfeprocessservice.infrastructure.service.storage.StorageException;

@Service
@EnableScheduling
public class LerDiretorioInputService {
	
	private static final Logger logger = LoggerFactory.getLogger(LerDiretorioInputService.class);
	
//	private static final long DELAY_2_MIN = (1000 * 60) * 2 ;
	private static final long DELAY_2_MIN = (1000 * 10);
	
	@Autowired
	private NotaFiscalStorageService notaFiscalStorageService;
	
	@Autowired
	private NotaFiscalRepository notaFiscalRepository;

	@Scheduled(fixedDelay = DELAY_2_MIN)
	@Transactional
	public void lerDiretorio() {
		try {
			logger.info("Lendo o diretório de entrada de notas fiscais");

			List<File> files = notaFiscalStorageService.loadFiles();
			
			if (!files.isEmpty()) {
				List<NotaFiscal> notasFiscais = notaFiscalRepository.findByStatus(StatusProcessamento.AGUARDANDO_PROCESSAMENTO);
				
				files.stream().forEach(file -> {
					notasFiscais.stream().forEach(notaFiscal -> {
						if (file.getName().equals(notaFiscal.getNomeArquivo())) {
							notaFiscal.processar(file);
							
							notaFiscalRepository.save(notaFiscal);
						}
					});
				});
			}
		} catch (StorageException e) {
			logger.error(e.getMessage());
		}
	}
}
