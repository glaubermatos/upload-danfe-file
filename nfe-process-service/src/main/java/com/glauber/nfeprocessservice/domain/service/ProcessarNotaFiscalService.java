package com.glauber.nfeprocessservice.domain.service;

import java.io.File;

import com.glauber.nfeprocessservice.domain.model.NotaFiscal;

public interface ProcessarNotaFiscalService {

	void execute(NotaFiscal notaFiscal, File xml);
}
