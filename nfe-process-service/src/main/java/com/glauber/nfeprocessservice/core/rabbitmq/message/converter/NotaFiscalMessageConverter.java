package com.glauber.nfeprocessservice.core.rabbitmq.message.converter;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.glauber.nfeprocessservice.core.rabbitmq.message.NotaFiscalMessage;
import com.glauber.nfeprocessservice.domain.model.NotaFiscal;
import com.glauber.nfeprocessservice.domain.model.NotaFiscalDuplicata;
import com.glauber.nfeprocessservice.domain.model.StatusProcessamento;

@Component
public class NotaFiscalMessageConverter {
	
	public NotaFiscal convertToDomainObject(NotaFiscalMessage event) {
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

	private Set<NotaFiscalDuplicata> obterDuplicatas(NotaFiscalMessage event) {
		Set<NotaFiscalMessage.Duplicata> messageDuplicatas = event.getDuplicatas();
		
		return messageDuplicatas.stream()
			.map(messageDuplicata -> {
				NotaFiscalDuplicata duplicata = new NotaFiscalDuplicata();
				duplicata.setDataVencimento(messageDuplicata.getDataVencimento());
				duplicata.setParcela(messageDuplicata.getParcela());
				duplicata.setValor(messageDuplicata.getValor());
				
				return duplicata;
			})
			.collect(Collectors.toSet());
	}
}
