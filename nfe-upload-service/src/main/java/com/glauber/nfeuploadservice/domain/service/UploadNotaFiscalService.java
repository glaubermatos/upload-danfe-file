package com.glauber.nfeuploadservice.domain.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.glauber.nfeuploadservice.domain.event.NotaFiscalUploadedEvent;
import com.glauber.nfeuploadservice.domain.model.NotaFiscal;
import com.glauber.nfeuploadservice.domain.service.NotaFiscalStorageService.NotaFiscalWrapper;

@Service
public class UploadNotaFiscalService {
	
	private static final Logger logger = LoggerFactory.getLogger(UploadNotaFiscalService.class);
	
	@Autowired
	private NotaFiscalStorageService notaFiscalStorageService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	public void upload(NotaFiscal notaFiscal, NotaFiscalWrapper notaFiscalWrapper) {
		try {
			notaFiscalStorageService.store(notaFiscalWrapper);
			logger.info(String.format("Nota Fiscal número %d armazenada com sucesso!.", notaFiscal.getNumero()));
			
			publisher.publishEvent(new NotaFiscalUploadedEvent(notaFiscal));
			
		} catch (Exception e) {
			//TODO: Tratar exceção
			throw new RuntimeException(e);
		}
	}
}
