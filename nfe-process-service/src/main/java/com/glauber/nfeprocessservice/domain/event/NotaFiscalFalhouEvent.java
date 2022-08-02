package com.glauber.nfeprocessservice.domain.event;

import java.io.File;

import com.glauber.nfeprocessservice.domain.model.NotaFiscal;

public class NotaFiscalFalhouEvent {

	private NotaFiscal notaFiscal;
	private File file;
	
	public NotaFiscalFalhouEvent(NotaFiscal notaFiscal, File file) {
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
