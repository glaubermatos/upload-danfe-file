package com.glauber.nfeprocessservice.api.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.glauber.nfeprocessservice.domain.model.StatusProcessamento;

public class NotaFiscalModel {

	private Integer id;
	private String nomeArquivo;
	private int numero;
	private LocalDateTime dhRegistro;
	private String nomeEmitente;
	private String nomeDestinatario;
	private BigDecimal valorNota;
	private StatusProcessamento status;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNomeArquivo() {
		return nomeArquivo;
	}
	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public LocalDateTime getDhRegistro() {
		return dhRegistro;
	}
	public void setDhRegistro(LocalDateTime dhRegistro) {
		this.dhRegistro = dhRegistro;
	}
	public String getNomeEmitente() {
		return nomeEmitente;
	}
	public void setNomeEmitente(String nomeEmitente) {
		this.nomeEmitente = nomeEmitente;
	}
	public String getNomeDestinatario() {
		return nomeDestinatario;
	}
	public void setNomeDestinatario(String nomeDestinatario) {
		this.nomeDestinatario = nomeDestinatario;
	}
	public BigDecimal getValorNota() {
		return valorNota;
	}
	public void setValorNota(BigDecimal valorNota) {
		this.valorNota = valorNota;
	}
	public StatusProcessamento getStatus() {
		return status;
	}
	public void setStatus(StatusProcessamento status) {
		this.status = status;
	}
	
}
