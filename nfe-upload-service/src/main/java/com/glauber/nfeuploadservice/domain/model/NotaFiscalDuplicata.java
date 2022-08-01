package com.glauber.nfeuploadservice.domain.model;

import java.math.BigDecimal;
import java.util.Date;

public class NotaFiscalDuplicata {

	private Integer parcela;
	private BigDecimal valor;
	private Date dataVencimento;
	
	public Integer getParcela() {
		return parcela;
	}
	public void setParcela(Integer parcela) {
		this.parcela = parcela;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
}
