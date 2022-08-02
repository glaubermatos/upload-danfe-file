package com.glauber.nfeprocessservice.api.model.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.glauber.nfeprocessservice.api.model.DuplicataModel;
import com.glauber.nfeprocessservice.domain.model.NotaFiscalDuplicata;

@Component
public class DuplicataModelAssembler {

	public DuplicataModel toModel(NotaFiscalDuplicata notaFiscalDuplicata) {
		DuplicataModel duplicataModel = new DuplicataModel();
		duplicataModel.setDataVencimento(notaFiscalDuplicata.getDataVencimento());
		duplicataModel.setId(notaFiscalDuplicata.getId());
		duplicataModel.setParcela(notaFiscalDuplicata.getParcela());
		duplicataModel.setValor(notaFiscalDuplicata.getValor());
		
		return duplicataModel;
	}
	
	public List<DuplicataModel> toCollectionModel(Collection<NotaFiscalDuplicata> duplicatas) {
		return duplicatas.stream()
				.map(duplicata -> toModel(duplicata))
				.collect(Collectors.toList());
	}
}
