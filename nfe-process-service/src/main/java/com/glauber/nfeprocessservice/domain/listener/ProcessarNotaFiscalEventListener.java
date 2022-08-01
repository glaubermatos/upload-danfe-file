package com.glauber.nfeprocessservice.domain.listener;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import com.glauber.nfeprocessservice.domain.event.NotaFiscalEmProcessamentoEvent;
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
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void whenInProcessing(NotaFiscalEmProcessamentoEvent event) {
		logger.info(String.format("Processando nota fiscal %s", event.getNotaFiscal().getNomeArquivo()));
		
		File xmlFile = notaFiscalStorageService.findFileBy(event.getNotaFiscal().getNomeArquivo());
		
		processarNotaFiscalService.processar(event.getNotaFiscal(), xmlFile);
	}
}
