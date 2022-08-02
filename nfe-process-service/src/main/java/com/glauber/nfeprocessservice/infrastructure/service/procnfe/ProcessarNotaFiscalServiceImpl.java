package com.glauber.nfeprocessservice.infrastructure.service.procnfe;

import java.io.File;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glauber.nfeprocessservice.domain.model.NotaFiscal;
import com.glauber.nfeprocessservice.domain.repository.NotaFiscalRepository;
import com.glauber.nfeprocessservice.domain.service.ProcessarNotaFiscalService;
import com.glauber.nfeprocessservice.infrastructure.service.procnfe.validator.SchemaValidatorNFev4;

@Service
public class ProcessarNotaFiscalServiceImpl implements ProcessarNotaFiscalService {

	@Autowired
	private SchemaValidatorNFev4 schemaValidatorNFev4;
	
	@Autowired
	private NotaFiscalRepository notaFiscalRepository;
	
	@Override
	@Transactional
	public void execute(NotaFiscal notaFiscal, File xml) {
		boolean xmlProcessadoComSucesso = schemaValidatorNFev4.validate(xml);
		
		if (xmlProcessadoComSucesso) { 
			notaFiscal.processada(xml);
			notaFiscalRepository.save(notaFiscal);
			
		} else {
			notaFiscal.falhou(xml);
			notaFiscalRepository.save(notaFiscal);
		}
	}

}
