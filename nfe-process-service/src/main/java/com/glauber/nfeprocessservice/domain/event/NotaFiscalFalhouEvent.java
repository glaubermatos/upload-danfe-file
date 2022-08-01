package com.glauber.nfeprocessservice.domain.event;

import com.glauber.nfeprocessservice.domain.model.NotaFiscal;

public class NotaFiscalFalhouEvent {

	private NotaFiscal notaFiscal;
	
	public NotaFiscalFalhouEvent(NotaFiscal notaFiscal) {
		System.out.println("==================> INSTANCIOU -> NotaFiscalFalhouEvent");
		this.notaFiscal = notaFiscal;
	}

	public NotaFiscal getNotaFiscal() {
		return notaFiscal;
	}
	public void setNotaFiscal(NotaFiscal notaFiscal) {
		this.notaFiscal = notaFiscal;
	}
	
}
