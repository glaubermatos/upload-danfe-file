package com.glauber.nfeuploadservice.domain.service;

import com.glauber.nfeuploadservice.domain.model.NotaFiscal;

public interface ParseXmlToNotaFiscalService {

	NotaFiscal parce(String xml);	
}
