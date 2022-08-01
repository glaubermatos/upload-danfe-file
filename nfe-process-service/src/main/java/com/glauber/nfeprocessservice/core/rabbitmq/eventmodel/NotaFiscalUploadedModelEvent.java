package com.glauber.nfeprocessservice.core.rabbitmq.eventmodel;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.glauber.nfeprocessservice.domain.model.NotaFiscal;

public class NotaFiscalUploadedModelEvent {

	private int numero;
	private String nomeArquivo;
	
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dhRegistro;
	
	private String nomeEmitente;
	private String nomeDestinatario;
	private BigDecimal valorNota;
	private String status;
	private Set<NotaFiscalDuplicataModelEvent> duplicatas;
	
	public NotaFiscalUploadedModelEvent() {}
	
	public NotaFiscalUploadedModelEvent(NotaFiscal notaFiscal) {
		this.numero = notaFiscal.getNumero();
		this.dhRegistro = notaFiscal.getDhRegistro();
		this.nomeEmitente = notaFiscal.getNomeEmitente();
		this.nomeDestinatario = notaFiscal.getNomeDestinatario();
		this.valorNota = notaFiscal.getValorNota();
		this.status = notaFiscal.getStatus().toString();
		this.nomeArquivo = notaFiscal.getNomeArquivo();
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Set<NotaFiscalDuplicataModelEvent> getDuplicatas() {
		return duplicatas;
	}
	public void setDuplicatas(Set<NotaFiscalDuplicataModelEvent> duplicatas) {
		this.duplicatas = duplicatas;
	}
	
}
