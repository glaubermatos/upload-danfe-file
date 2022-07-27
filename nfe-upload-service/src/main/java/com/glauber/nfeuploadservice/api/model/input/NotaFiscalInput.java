package com.glauber.nfeuploadservice.api.model.input;

import org.springframework.web.multipart.MultipartFile;

public class NotaFiscalInput {

	private MultipartFile xmlNotaFiscal;

	public MultipartFile getXmlNotaFiscal() {
		return xmlNotaFiscal;
	}
	public void setXmlNotaFiscal(MultipartFile xmlNotaFiscal) {
		this.xmlNotaFiscal = xmlNotaFiscal;
	}
	
}
