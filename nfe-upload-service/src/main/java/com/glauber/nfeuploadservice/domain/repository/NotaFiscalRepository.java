package com.glauber.nfeuploadservice.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.glauber.nfeuploadservice.domain.model.NotaFiscal;

@Repository
public interface NotaFiscalRepository extends JpaRepository<NotaFiscal, Integer> {

	Optional<NotaFiscal> findByNumero(int numero);
}
