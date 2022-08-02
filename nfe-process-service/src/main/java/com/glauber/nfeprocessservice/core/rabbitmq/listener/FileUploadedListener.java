package com.glauber.nfeprocessservice.core.rabbitmq.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.glauber.nfeprocessservice.core.rabbitmq.message.NotaFiscalMessage;
import com.glauber.nfeprocessservice.core.rabbitmq.message.converter.NotaFiscalMessageConverter;
import com.glauber.nfeprocessservice.domain.model.NotaFiscal;
import com.glauber.nfeprocessservice.domain.service.NotaFiscalRegistrationService;

@Component
public class FileUploadedListener {
	
	private static final Logger logger = LoggerFactory.getLogger(FileUploadedListener.class); 
	
	@Autowired
	private NotaFiscalRegistrationService notaFiscalRegistrationService;
	
	@Autowired
	private NotaFiscalMessageConverter notaFiscalMessageConverter; 

	@RabbitListener(queues = "nfe-upload-service.v1.file-uploaded")
	public void whenFileUploaded(NotaFiscalMessage message) {
		logger.info(String.format("Nota Fiscal %s recebida.", message.getNomeArquivo()));
		
		NotaFiscal notaFiscal = notaFiscalMessageConverter.convertToDomainObject(message);
		
		notaFiscalRegistrationService.save(notaFiscal);
	}
}
