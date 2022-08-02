package com.glauber.nfeprocessservice.domain.event;

import com.glauber.nfeprocessservice.domain.model.NotaFiscal;

public class NotaFiscalRemovidaEvent {

	private NotaFiscal notaFiscal;
	
	public NotaFiscalRemovidaEvent(NotaFiscal notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	public NotaFiscal getNotaFiscal() {
		return notaFiscal;
	}
	public void setNotaFiscal(NotaFiscal notaFiscal) {
		this.notaFiscal = notaFiscal;
	}
	
}
