package com.glauber.nfeprocessservice.api.exceptionhandler.problem;

public enum ProblemType {

	INVALID_DATA("Dados inválidos"), 
	SYSTEM_ERROR("Erro de sistema"), 
	ENTIDADE_NAO_ENCONTRADA("Entidade não encontrada");
	
	String title;
	
	ProblemType(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return this.title;
	}
}
