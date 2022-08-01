package com.glauber.nfeprocessservice.core.rabbitmq.eventmodel;

import org.springframework.stereotype.Component;

import com.glauber.nfeprocessservice.domain.model.NotaFiscal;
import com.glauber.nfeprocessservice.domain.model.StatusProcessamento;

@Component
public class NotaFiscalUploadedModelEventDisassembler {

	public NotaFiscal toDomainObject(NotaFiscalUploadedModelEvent event) {
		NotaFiscal notaFiscal = new NotaFiscal();
		notaFiscal.setNumero(event.getNumero());
		notaFiscal.setDhRegistro(event.getDhRegistro());
		notaFiscal.setNomeDestinatario(event.getNomeDestinatario());
		notaFiscal.setNomeEmitente(event.getNomeEmitente());
		notaFiscal.setStatus(StatusProcessamento.valueOf(event.getStatus()));
		notaFiscal.setValorNota(event.getValorNota());
		notaFiscal.setNomeArquivo(event.getNomeArquivo());
		
		return notaFiscal;
	}
}
