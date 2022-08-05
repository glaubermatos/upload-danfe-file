package com.glauber.nfeprocessservice.api.exceptionhandler.problem;

public enum ProblemType {

	INVALID_DATA("Dados inválidos"), 
	SYSTEM_ERROR("Erro de sistema"), 
	REGRA_DE_NEGOCIO_VIOLADA("Regra de negócio violada"), 
	ENTIDADE_NAO_ENCONTRADA("Entidade não encontrada");
	
	String title;
	
	ProblemType(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return this.title;
	}
}
