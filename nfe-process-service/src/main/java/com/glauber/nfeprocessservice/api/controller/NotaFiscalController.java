package com.glauber.nfeprocessservice.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.glauber.nfeprocessservice.api.model.DuplicataModel;
import com.glauber.nfeprocessservice.api.model.NotaFiscalModel;
import com.glauber.nfeprocessservice.api.model.assembler.DuplicataModelAssembler;
import com.glauber.nfeprocessservice.api.model.assembler.NotaFiscalModelAssembler;
import com.glauber.nfeprocessservice.domain.repository.NotaFiscalRepository;
import com.glauber.nfeprocessservice.domain.service.NotaFiscalRegistrationService;

@RestController
@RequestMapping("/notas-fiscais")
public class NotaFiscalController {
	
	@Autowired
	private NotaFiscalRepository notaFiscalRepository;
	
	@Autowired
	private NotaFiscalModelAssembler notaFiscalModelAssembler;
	
	@Autowired
	private DuplicataModelAssembler duplicataModelAssembler;
	
	@Autowired
	private NotaFiscalRegistrationService notaFiscalRegistrationService;

	@GetMapping
	public List<NotaFiscalModel> index() {
		var notasFiscais = notaFiscalRepository.findAll();
		return notaFiscalModelAssembler.toCollectionModel(notasFiscais);
	}
	
	@GetMapping("/{numeroNotaFiscal}/duplicatas")
	public List<DuplicataModel> showDuplicatas(@PathVariable int numeroNotaFiscal) {
		var notaFiscal = notaFiscalRegistrationService
				.findNotaFiscalByNumeroOrElseThrow(numeroNotaFiscal);
		
		var duplicatasModel = duplicataModelAssembler
				.toCollectionModel(notaFiscal.getDuplicatas());
		
		return duplicatasModel;
	}
	
	@DeleteMapping("/{numeroNotaFiscal}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int numeroNotaFiscal) {
		notaFiscalRegistrationService.remover(numeroNotaFiscal);
	}
}
