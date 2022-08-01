package com.glauber.nfeprocessservice.core.rabbitmq.eventmodel;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.glauber.nfeprocessservice.domain.model.NotaFiscalDuplicata;

@Component
public class NotaFiscalDuplicataUploadedModelEventDisassembler {

	public Set<NotaFiscalDuplicata> toDomainObject(NotaFiscalUploadedModelEvent event) {
		Set<NotaFiscalDuplicataModelEvent> duplicatasModelEvent = event.getDuplicatas();
		
		Set<NotaFiscalDuplicata> duplicatas = new HashSet<>();
		
		duplicatasModelEvent.stream().forEach(duplicataModel -> {
			NotaFiscalDuplicata duplicata = new NotaFiscalDuplicata();
			duplicata.setDataVencimento(duplicataModel.getDataVencimento());
			duplicata.setParcela(duplicataModel.getParcela());
			duplicata.setValor(duplicataModel.getValor());
			
			duplicatas.add(duplicata);
		});
		
		return duplicatas;
	}
}
