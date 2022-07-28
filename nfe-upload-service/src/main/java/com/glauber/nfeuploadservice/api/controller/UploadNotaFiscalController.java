package com.glauber.nfeuploadservice.api.controller;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.glauber.nfeuploadservice.api.model.input.NotaFiscalInput;
import com.glauber.nfeuploadservice.domain.model.NotaFiscal;
import com.glauber.nfeuploadservice.domain.service.NotaFiscalStorageService.XmlWrapper;
import com.glauber.nfeuploadservice.domain.service.UploadNotaFiscalService;

@RestController
@RequestMapping("/notas-fiscais")
public class UploadNotaFiscalController {
	
	@Autowired
	private UploadNotaFiscalService uploadNotaFiscalService;
	
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public String upload(NotaFiscalInput notaFiscalInput) {
		try {
			InputStream xmlInputStream = notaFiscalInput.getInputStream();
			
			NotaFiscal notaFiscal = new NotaFiscal();
			
			XmlWrapper xmlWrapper = new XmlWrapper();
			xmlWrapper.setFileName(notaFiscalInput.getFileName());
			xmlWrapper.setInputStream(xmlInputStream);
			
			
			uploadNotaFiscalService.execute(notaFiscal, xmlWrapper);
			
			return "Nota fiscal enviado com sucesso!";
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
