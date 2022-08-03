package com.glauber.nfeuploadservice.api.model.input;

import java.io.IOException;
import java.io.InputStream;

import javax.validation.constraints.NotNull;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import com.glauber.nfeuploadservice.core.validation.FileContentType;

public class NotaFiscalInput {

	//TODO: Poderia criar validação customizada para limitar o tamanho do arquivo
	@NotNull
	@FileContentType(allowed = {MediaType.TEXT_XML_VALUE})
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
