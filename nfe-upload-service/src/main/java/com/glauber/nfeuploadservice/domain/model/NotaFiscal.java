package com.glauber.nfeuploadservice.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class NotaFiscal {

	private String nomeArquivo;
	private int numero;
	private LocalDateTime dhRegistro;
	private String nomeEmitente;
	private String nomeDestinatario;
	private BigDecimal valorNota;
	private StatusProcessamento status = StatusProcessamento.AGUARDANDO_PROCESSAMENTO;
	Set<NotaFiscalDuplicata> duplicatas = new HashSet<>();
	
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
	public Set<NotaFiscalDuplicata> getDuplicatas() {
		return duplicatas;
	}
	public void setDuplicatas(Set<NotaFiscalDuplicata> duplicatas) {
		this.duplicatas = duplicatas;
	}
	
	@Override
	public String toString() {
		return String.format("nomeArquivo: %s, numero: %d, dhRegistro: %s, nomeEmitente: %s, nomeDestinatario: %s, valorNota: %s, duplicatas: %d", 
				nomeArquivo, numero, dhRegistro, nomeEmitente, nomeDestinatario, valorNota, duplicatas.size());
	}
	
}
