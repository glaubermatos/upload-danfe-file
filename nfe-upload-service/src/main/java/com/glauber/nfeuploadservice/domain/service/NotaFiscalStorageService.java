package com.glauber.nfeuploadservice.domain.service;

import java.io.InputStream;

public interface NotaFiscalStorageService {

	void store(NotaFiscalWrapper newInvoice);
	
	public class NotaFiscalWrapper {
		
		private String fileName;
		private InputStream inputStream;
		
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		public InputStream getInputStream() {
			return inputStream;
		}
		public void setInputStream(InputStream inputStream) {
			this.inputStream = inputStream;
		}
		
	}
}
