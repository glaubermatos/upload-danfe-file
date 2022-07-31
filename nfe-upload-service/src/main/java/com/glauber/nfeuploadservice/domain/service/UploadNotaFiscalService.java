package com.glauber.nfeuploadservice.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glauber.nfeuploadservice.domain.model.NotaFiscal;
import com.glauber.nfeuploadservice.domain.service.NotaFiscalStorageService.XmlWrapper;

@Service
public class UploadNotaFiscalService {
	
	@Autowired
	private NotaFiscalStorageService notaFiscalStorageService;
	
	@Autowired
	private NotaFiscalRegistrationService notaFiscalRegistrationService;

	@Transactional
	public void up(NotaFiscal notaFiscal, XmlWrapper xmlWrapper) {
		try {
			notaFiscalRegistrationService.save(notaFiscal);
			notaFiscalStorageService.store(xmlWrapper);
		} catch (Exception e) {
			//TODO: Tratar exceção
			throw new RuntimeException(e);
		}
	}
}
