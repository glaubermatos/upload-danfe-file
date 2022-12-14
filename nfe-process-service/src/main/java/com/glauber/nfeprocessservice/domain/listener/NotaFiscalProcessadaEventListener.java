  package com.glauber.nfeprocessservice.domain.listener;
  
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import com.glauber.nfeprocessservice.domain.event.NotaFiscalProcessadaEvent;
import com.glauber.nfeprocessservice.domain.service.NotaFiscalStorageService;
  
  @Component 
  public class NotaFiscalProcessadaEventListener {
  
	  private static final Logger logger = LoggerFactory.getLogger(NotaFiscalProcessadaEventListener.class);
  
	  @Autowired private NotaFiscalStorageService notaFiscalStorageService;
  
	  @TransactionalEventListener 
	  public void whenSuccessfullyProcessed(NotaFiscalProcessadaEvent event) {
		  logger.info(String.format("Nota Fiscal %s processada com sucesso",
		  event.getNotaFiscal().getNomeArquivo()));
		  
		  notaFiscalStorageService.moveToOutputDirectory(event.getFile()); 
	  } 
  }
 
