package com.glauber.nfeprocessservice.domain.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import com.glauber.nfeprocessservice.domain.event.NotaFiscalRemovidaEvent;
import com.glauber.nfeprocessservice.domain.model.NotaFiscal;
import com.glauber.nfeprocessservice.domain.service.NotaFiscalStorageService;

@Component
public class NotaFiscalRemovidaEventListener {
	
	private static final Logger logger = LoggerFactory.getLogger(NotaFiscalRemovidaEventListener.class);
	
	@Autowired
	private NotaFiscalStorageService notaFiscalStorageService;

	@TransactionalEventListener
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void whenRemoved(NotaFiscalRemovidaEvent event) {
		var notaFiscal = event.getNotaFiscal();
		
		logger.info(String.format("Removendo nota fiscal %s", notaFiscal.getNomeArquivo()));
		
		removeNotaFiscal(notaFiscal);
	}

	private void removeNotaFiscal(NotaFiscal notaFiscal) {
		if (notaFiscal.estaAguardandoProcessamento()) {
			notaFiscalStorageService.removeFileFromInputDirectory(notaFiscal.getNomeArquivo());
			
		} else if (notaFiscal.foiProcessada()) {
			notaFiscalStorageService.removeFileFromOutputDirectory(notaFiscal.getNomeArquivo());
			
		} else if (notaFiscal.foiProcessadaComErro()) {
			notaFiscalStorageService.removeFileFromErrorDirectory(notaFiscal.getNomeArquivo());
		}
	}
	
}
