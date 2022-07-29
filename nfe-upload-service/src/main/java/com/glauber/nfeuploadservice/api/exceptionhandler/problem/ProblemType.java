package com.glauber.nfeuploadservice.api.exceptionhandler.problem;

public enum ProblemType {

	INVALID_DATA("Dados inválidos"), 
	SYSTEM_ERROR("Erro de sistema");
	
	String title;
	
	ProblemType(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return this.title;
	}
}
