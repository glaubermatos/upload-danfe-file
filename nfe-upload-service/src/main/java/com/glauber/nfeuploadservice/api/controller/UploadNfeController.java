package com.glauber.nfeuploadservice.api.controller;

import java.io.IOException;
import java.nio.file.Path;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.glauber.nfeuploadservice.api.model.input.NotaFiscalInput;

@RestController
@RequestMapping("/nfes")
public class UploadNfeController {
	
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public String upload(NotaFiscalInput notaFiscalInput) {
		
		saveXmlLocalStorage(notaFiscalInput.getXmlNotaFiscal());
		
		return "Nfe enviado com sucesso!";
	}
	
	private void saveXmlLocalStorage(MultipartFile xml) {
		var xmlFile = Path.of("../input/"+xml.getOriginalFilename());
		
		try {
			xml.transferTo(xmlFile);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
