package com.glauber.nfeuploadservice.domain.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.glauber.nfeuploadservice.api.model.event.NotaFiscalMessageEvent;
import com.glauber.nfeuploadservice.domain.event.NotaFiscalUploadedEvent;
import com.glauber.nfeuploadservice.domain.service.UploadNotaFiscalService;

@Component
public class NotaFiscalUploadedEventListener {
	
	private static final Logger logger = LoggerFactory.getLogger(UploadNotaFiscalService.class);
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private Queue queue;

	@EventListener
	public void whenNotaFiscalUploaded(NotaFiscalUploadedEvent event) {
		NotaFiscalMessageEvent notaFiscalMessage = new NotaFiscalMessageEvent(event.getNotaFiscal());
		
		rabbitTemplate.convertAndSend(queue.getActualName(), notaFiscalMessage);
		
		logger.info(String.format("Nota Fiscal número %d enviada para Queue %s.", 
				event.getNotaFiscal().getNumero(), queue.getActualName()));
	}
}
