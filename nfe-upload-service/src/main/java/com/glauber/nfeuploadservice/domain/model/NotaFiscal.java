package com.glauber.nfeuploadservice.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class NotaFiscal {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private int numero;
	
	@Column(nullable = false)
	private LocalDateTime dhRegistro;
	
	@Column(nullable = false)
	private String nomeEmitente;
	
	@Column(nullable = false)
	private String nomeDestinatario;
	
	@Column(nullable = false)
	private BigDecimal valorNota;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusProcessamento status = StatusProcessamento.AGUARDANDO_PROCESSAMENTO;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NotaFiscal other = (NotaFiscal) obj;
		return Objects.equals(id, other.id);
	}
	
	@Override
	public String toString() {
		return String.format("numero: %d, dhRegistro: %s, nomeEmitente: %s, nomeDestinatario: %s, valorNota: %s", 
				numero, dhRegistro, nomeEmitente, nomeDestinatario, valorNota);
	}
	
}
