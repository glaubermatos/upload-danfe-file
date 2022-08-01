package com.glauber.nfeprocessservice.domain.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glauber.nfeprocessservice.domain.exception.NotaFiscalNaoEncontradaException;
import com.glauber.nfeprocessservice.domain.model.NotaFiscal;
import com.glauber.nfeprocessservice.domain.repository.NotaFiscalRepository;

@Service
public class NotaFiscalRegistrationService {
	
	private static final Logger logger = LoggerFactory.getLogger(NotaFiscalRegistrationService.class);

	@Autowired
	private NotaFiscalRepository notaFiscalRepository;
	
	@Transactional
	public void save(NotaFiscal notaFiscal) {
		notaFiscalRepository.save(notaFiscal);
		
		logger.info(String.format("Nota Fiscal %s inserida no banco de dados com status %s.", notaFiscal.getNomeArquivo(), notaFiscal.getStatus().toString()));
	}

	@Transactional
	public void remover(int numero) {
		NotaFiscal notaFiscal = findNotaFiscalByNumeroOrElseThrow(numero);
		notaFiscalRepository.delete(notaFiscal);
		//TODO: remover arquivo após exclusão da nota fiscal do banco de dados
	}

	private NotaFiscal findNotaFiscalByNumeroOrElseThrow(int numero) {
		return notaFiscalRepository.findByNumero(numero)
				.orElseThrow(() -> new NotaFiscalNaoEncontradaException(numero));
	}
}
