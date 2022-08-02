package com.glauber.nfeprocessservice.domain.event;

import java.io.File;

import com.glauber.nfeprocessservice.domain.model.NotaFiscal;

public class NotaFiscalEmProcessamentoEvent {

	private NotaFiscal notaFiscal;
	private File file;
	
	public NotaFiscalEmProcessamentoEvent(NotaFiscal notaFiscal, File file) {
		this.notaFiscal = notaFiscal;
		this.file = file;
	}

	public NotaFiscal getNotaFiscal() {
		return notaFiscal;
	}
	public void setNotaFiscal(NotaFiscal notaFiscal) {
		this.notaFiscal = notaFiscal;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	
}
