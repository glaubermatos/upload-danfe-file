package com.glauber.nfeuploadservice.domain.service;

import com.glauber.nfeuploadservice.domain.model.NotaFiscal;

public interface NotaFiscalXmlParseToDomainObjectService {

	NotaFiscal parce(String xml);	
}
