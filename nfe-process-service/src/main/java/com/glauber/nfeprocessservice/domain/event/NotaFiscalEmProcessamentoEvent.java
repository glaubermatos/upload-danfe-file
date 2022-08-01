package com.glauber.nfeprocessservice.domain.event;

import com.glauber.nfeprocessservice.domain.model.NotaFiscal;

public class NotaFiscalEmProcessamentoEvent {

	private NotaFiscal notaFiscal;
	
	public NotaFiscalEmProcessamentoEvent(NotaFiscal notaFiscal) {
		System.out.println("==================> INSTANCIOU -> NotaFiscalEmProcessamentoEvent");
		this.notaFiscal = notaFiscal;
	}

	public NotaFiscal getNotaFiscal() {
		return notaFiscal;
	}
	public void setNotaFiscal(NotaFiscal notaFiscal) {
		this.notaFiscal = notaFiscal;
	}
	
}
