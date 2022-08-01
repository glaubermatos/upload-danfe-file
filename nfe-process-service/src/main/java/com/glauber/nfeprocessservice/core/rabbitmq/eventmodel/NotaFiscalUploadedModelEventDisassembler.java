package com.glauber.nfeprocessservice.core.rabbitmq.eventmodel;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.glauber.nfeprocessservice.domain.model.NotaFiscal;
import com.glauber.nfeprocessservice.domain.model.NotaFiscalDuplicata;
import com.glauber.nfeprocessservice.domain.model.StatusProcessamento;

@Component
public class NotaFiscalUploadedModelEventDisassembler {
	
	@Autowired
	private NotaFiscalDuplicataUploadedModelEventDisassembler notaFiscalDuplicataUploadedModelEventDisassembler;

	public NotaFiscal toDomainObject(NotaFiscalUploadedModelEvent event) {
		NotaFiscal notaFiscal = new NotaFiscal();
		notaFiscal.setNumero(event.getNumero());
		notaFiscal.setDhRegistro(event.getDhRegistro());
		notaFiscal.setNomeDestinatario(event.getNomeDestinatario());
		notaFiscal.setNomeEmitente(event.getNomeEmitente());
		notaFiscal.setStatus(StatusProcessamento.valueOf(event.getStatus()));
		notaFiscal.setValorNota(event.getValorNota());
		notaFiscal.setNomeArquivo(event.getNomeArquivo());
		
		notaFiscal.setDuplicatas(obterDuplicatas(event));
		
		return notaFiscal;
	}

	private Set<NotaFiscalDuplicata> obterDuplicatas(NotaFiscalUploadedModelEvent event) {
		return notaFiscalDuplicataUploadedModelEventDisassembler
				.toDomainObject(event);
	}
}
