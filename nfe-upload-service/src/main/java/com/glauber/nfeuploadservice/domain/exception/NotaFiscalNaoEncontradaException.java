package com.glauber.nfeuploadservice.domain.exception;

public class NotaFiscalNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public NotaFiscalNaoEncontradaException(int numeroNotaFiscal) {
		super(String.format("Não existe um cadastro de Nota fiscal de código %d", numeroNotaFiscal));
	}

	
}
