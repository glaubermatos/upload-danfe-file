package com.glauber.nfeuploadservice.api.model.disassembler;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import com.glauber.nfeuploadservice.api.model.input.NotaFiscalInput;
import com.glauber.nfeuploadservice.domain.model.NotaFiscal;
import com.glauber.nfeuploadservice.domain.service.ParseXmlToNotaFiscalService;

@Component
public class NotaFiscalInputDisassembler {
	
	@Autowired
	private ParseXmlToNotaFiscalService parseXmlToNotaFiscalService;

	public NotaFiscal toDomainModel(NotaFiscalInput notaFiscalInput) throws IOException, ClassNotFoundException {
		InputStream stream = notaFiscalInput.getInputStream();
		
		String xmlNotaFiscalString = StreamUtils.copyToString(stream, Charset.forName("UTF-8"));
		
		NotaFiscal notaFiscal = parseXmlToNotaFiscalService.parce(xmlNotaFiscalString);
		notaFiscal.setNomeArquivo(notaFiscalInput.getFileName());
		
		return notaFiscal;
	}
}
