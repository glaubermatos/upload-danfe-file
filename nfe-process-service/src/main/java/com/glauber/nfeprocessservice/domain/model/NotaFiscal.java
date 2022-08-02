package com.glauber.nfeprocessservice.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.data.domain.AbstractAggregateRoot;

import com.glauber.nfeprocessservice.domain.event.NotaFiscalEmProcessamentoEvent;
import com.glauber.nfeprocessservice.domain.event.NotaFiscalFalhouEvent;
import com.glauber.nfeprocessservice.domain.event.NotaFiscalProcessadaEvent;

@Entity
public class NotaFiscal extends AbstractAggregateRoot<NotaFiscal>  {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private String nomeArquivo;
	
	private int numero;
	
	private LocalDateTime dhRegistro;
	
	private String nomeEmitente;
	
	private String nomeDestinatario;
	
	private BigDecimal valorNota;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusProcessamento status = StatusProcessamento.AGUARDANDO_PROCESSAMENTO;
	
	@OneToMany(mappedBy = "notaFiscal", cascade = CascadeType.ALL)
	private Set<NotaFiscalDuplicata> duplicatas = new HashSet<>();
	
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
	public String getNomeArquivo() {
		return nomeArquivo;
	}
	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
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
	
	public void processar() {
		setStatus(StatusProcessamento.EM_PROCESSAMENTO);
		
		registerEvent(new NotaFiscalEmProcessamentoEvent(this));
	}

	public void processada() {
		setStatus(StatusProcessamento.PROCESSADA);
		
		registerEvent(new NotaFiscalProcessadaEvent(this));
	}

	public void falhou() {
		setStatus(StatusProcessamento.PROCESSADA_COM_ERRO);
		
		registerEvent(new NotaFiscalFalhouEvent(this));
	}
	
	public boolean estaAguardandoProcessamento() {
		return getStatus().equals(StatusProcessamento.AGUARDANDO_PROCESSAMENTO);
	}
	
	public boolean foiProcessada() {
		return getStatus().equals(StatusProcessamento.PROCESSADA);
	}
	
	public boolean foiProcessadaComErro() {
		return getStatus().equals(StatusProcessamento.PROCESSADA_COM_ERRO);
	}
	
}
