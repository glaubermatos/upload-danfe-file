package com.glauber.nfeuploadservice.domain.service.event.notafiscal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.glauber.nfeuploadservice.api.model.event.NotaFiscalUploadedModelEvent;
import com.glauber.nfeuploadservice.domain.service.UploadNotaFiscalService;

@Component
public class NotaFiscalUploadedEventListener {
	
	private static final Logger logger = LoggerFactory.getLogger(UploadNotaFiscalService.class);
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private Queue queue;

	@EventListener(condition = "#event.temNumero()")
	public void whenNotaFiscalUploaded(NotaFiscalUploadedEvent event) {
		NotaFiscalUploadedModelEvent modelEvent = new NotaFiscalUploadedModelEvent(event.getNotaFiscal());
		
		rabbitTemplate.convertAndSend(queue.getActualName(), modelEvent);
		
		logger.info(String.format("Nota Fiscal número %d enviada para Queue %s.", 
				event.getNotaFiscal().getNumero(), queue.getActualName()));
	}
}
