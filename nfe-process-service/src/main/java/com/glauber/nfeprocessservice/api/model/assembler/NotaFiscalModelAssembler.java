package com.glauber.nfeprocessservice.api.model.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.glauber.nfeprocessservice.api.model.NotaFiscalModel;
import com.glauber.nfeprocessservice.domain.model.NotaFiscal;

@Component
public class NotaFiscalModelAssembler {

	public NotaFiscalModel toModel(NotaFiscal notaFiscal) {
		NotaFiscalModel notaFiscalModel = new NotaFiscalModel();
		notaFiscalModel.setDhRegistro(notaFiscal.getDhRegistro());
		notaFiscalModel.setId(notaFiscal.getId());
		notaFiscalModel.setNomeArquivo(notaFiscal.getNomeArquivo());
		notaFiscalModel.setNomeDestinatario(notaFiscal.getNomeDestinatario());
		notaFiscalModel.setNomeEmitente(notaFiscal.getNomeEmitente());
		notaFiscalModel.setNumero(notaFiscal.getNumero());
		notaFiscalModel.setStatus(notaFiscal.getStatus());
		notaFiscalModel.setValorNota(notaFiscal.getValorNota());
		
		return notaFiscalModel;
	}
	
	public List<NotaFiscalModel> toCollectionModel(Collection<NotaFiscal> notasFiscais) {
		return notasFiscais.stream()
				.map(notaFiscal -> toModel(notaFiscal))
				.collect(Collectors.toList());
	}
}
