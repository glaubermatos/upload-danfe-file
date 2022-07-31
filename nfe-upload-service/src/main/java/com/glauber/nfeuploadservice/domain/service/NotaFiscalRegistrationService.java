package com.glauber.nfeuploadservice.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glauber.nfeuploadservice.domain.exception.NotaFiscalNaoEncontradaException;
import com.glauber.nfeuploadservice.domain.model.NotaFiscal;
import com.glauber.nfeuploadservice.domain.repository.NotaFiscalRepository;

@Service
public class NotaFiscalRegistrationService {

	@Autowired
	private NotaFiscalRepository notaFiscalRepository;
	
	@Transactional
	public void save(NotaFiscal notaFiscal) {
		notaFiscalRepository.save(notaFiscal);
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
