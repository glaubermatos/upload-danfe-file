package com.glauber.nfeuploadservice.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glauber.nfeuploadservice.domain.model.NotaFiscal;
import com.glauber.nfeuploadservice.domain.service.NotaFiscalStorageService.XmlWrapper;

@Service
public class UploadNotaFiscalService {
	
	@Autowired
	private NotaFiscalStorageService notaFiscalStorageService;

	public void execute(NotaFiscal notaFiscal, XmlWrapper xmlWrapper) {
		notaFiscalStorageService.store(xmlWrapper);
	}
}
