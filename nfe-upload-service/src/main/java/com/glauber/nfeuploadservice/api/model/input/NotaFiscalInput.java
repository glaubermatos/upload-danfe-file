package com.glauber.nfeuploadservice.api.model.input;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public class NotaFiscalInput {

	private MultipartFile xmlNotaFiscal;

	public MultipartFile getXmlNotaFiscal() {
		return xmlNotaFiscal;
	}
	public void setXmlNotaFiscal(MultipartFile xmlNotaFiscal) {
		this.xmlNotaFiscal = xmlNotaFiscal;
	}
	
	public InputStream getInputStream() throws IOException {
		if (getXmlNotaFiscal() != null) {
			return getXmlNotaFiscal().getInputStream();
		}
		
		return null;
	}
	
	public String getFileName() {
		if (getXmlNotaFiscal() != null) {
			return getXmlNotaFiscal().getOriginalFilename();
		}
		
		return null;
	}
	
}
