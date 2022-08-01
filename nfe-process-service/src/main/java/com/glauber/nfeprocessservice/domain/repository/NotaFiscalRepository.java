package com.glauber.nfeprocessservice.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.glauber.nfeprocessservice.domain.model.NotaFiscal;
import com.glauber.nfeprocessservice.domain.model.StatusProcessamento;

@Repository
public interface NotaFiscalRepository extends CustomJpaRepository<NotaFiscal, Integer> {

	Optional<NotaFiscal> findByNumero(int numero);
	List<NotaFiscal> findByStatus(StatusProcessamento status);
}
