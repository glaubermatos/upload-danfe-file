package com.glauber.nfeuploadservice.domain.event;

import com.glauber.nfeuploadservice.domain.model.NotaFiscal;

public class NotaFiscalUploadedEvent {
	
	private NotaFiscal notaFiscal;

	public NotaFiscalUploadedEvent(NotaFiscal notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	public NotaFiscal getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(NotaFiscal notaFiscal) {
		this.notaFiscal = notaFiscal;
	}
	
}
