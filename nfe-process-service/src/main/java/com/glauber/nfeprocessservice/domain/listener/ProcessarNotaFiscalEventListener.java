package com.glauber.nfeprocessservice.domain.listener;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import com.glauber.nfeprocessservice.domain.event.NotaFiscalEmProcessamentoEvent;
import com.glauber.nfeprocessservice.domain.event.NotaFiscalFalhouEvent;
import com.glauber.nfeprocessservice.domain.event.NotaFiscalProcessadaEvent;
import com.glauber.nfeprocessservice.domain.service.NotaFiscalStorageService;
import com.glauber.nfeprocessservice.domain.service.ProcessarNotaFiscalService;

@Component
public class ProcessarNotaFiscalEventListener {
	
	private static final Logger logger = LoggerFactory.getLogger(ProcessarNotaFiscalEventListener.class);
	
	@Autowired
	private ProcessarNotaFiscalService processarNotaFiscalService;
	
	@Autowired
	private NotaFiscalStorageService notaFiscalStorageService;

	@TransactionalEventListener
	public void whenInProcessing(NotaFiscalEmProcessamentoEvent event) {
		logger.info(String.format("Processando nota fiscal %s", event.getNotaFiscal().getNomeArquivo()));
		
		File xmlFile = notaFiscalStorageService.findFileBy(event.getNotaFiscal().getNomeArquivo());
		
		processarNotaFiscalService.processar(event.getNotaFiscal(), xmlFile);
	}

	@TransactionalEventListener
	public void whenSuccessfullyProcessed(NotaFiscalProcessadaEvent event) {
		logger.info(String.format("Nota Fiscal %s processada com sucesso", event.getNotaFiscal().getNomeArquivo()));
		
		File xmlFile = notaFiscalStorageService.findFileBy(event.getNotaFiscal().getNomeArquivo());
				
		notaFiscalStorageService.moveToOutputDirectory(xmlFile);
	}

	@TransactionalEventListener
	public void whenProcessedWithFailure(NotaFiscalFalhouEvent event) {
		logger.info(String.format("Nota Fiscal %s processada com erro", event.getNotaFiscal().getNomeArquivo()));
		
		File xmlFile = notaFiscalStorageService.findFileBy(event.getNotaFiscal().getNomeArquivo());
				
		notaFiscalStorageService.moveToErrorDirectory(xmlFile);
	}
}
