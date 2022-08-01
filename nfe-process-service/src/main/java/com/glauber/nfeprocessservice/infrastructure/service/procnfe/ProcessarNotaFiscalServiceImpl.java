package com.glauber.nfeprocessservice.infrastructure.service.procnfe;

import java.io.File;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glauber.nfeprocessservice.domain.model.NotaFiscal;
import com.glauber.nfeprocessservice.domain.repository.NotaFiscalRepository;
import com.glauber.nfeprocessservice.domain.service.ProcessarNotaFiscalService;
import com.glauber.nfeprocessservice.infrastructure.service.procnfe.validator.XsdSchemaValidatorNFev4;

@Service
public class ProcessarNotaFiscalServiceImpl implements ProcessarNotaFiscalService {

	@Autowired
	private XsdSchemaValidatorNFev4 schemaValidatorNFev4;
	
	@Autowired
	private NotaFiscalRepository notaFiscalRepository;
	
	@Override
	@Transactional
	public void processar(NotaFiscal notaFiscal, File xml) {
		boolean xmlProcessadoComSucesso = schemaValidatorNFev4.validate(xml);
		
		if (xmlProcessadoComSucesso) { 
			notaFiscal.processada();
			notaFiscalRepository.save(notaFiscal);
			
		} else {
			notaFiscal.falhou();
			notaFiscalRepository.save(notaFiscal);
		}
	}

}
