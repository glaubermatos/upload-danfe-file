package com.glauber.nfeprocessservice.core.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Validated
@Component
@ConfigurationProperties(prefix = "nfe-process-service.storage")
public class StorageProperties {
	
	private StorageType type = StorageType.LOCAL;
	
	@Valid
	private Local local = new Local();	
	
	public StorageType getType() {
		return type;
	}
	public void setType(StorageType type) {
		this.type = type;
	}
	public Local getLocal() {
		return local;
	}
	public void setLocal(Local local) {
		this.local = local;
	}

	public enum StorageType {
		LOCAL
	}
	
	@Validated
	public class Local {
		
		@NotNull
		private String xmlInputDirectory;
		
		@NotNull
		private String xmlOutputDirectory;
		
		@NotNull
		private String xmlErrorDirectory;

		public String getXmlInputDirectory() throws IOException {
			Path pathInputDirectory = Path.of(this.xmlInputDirectory);
			Files.createDirectories(pathInputDirectory);
			
			return xmlInputDirectory;
		}
		
		public String getXmlOutputDirectory() throws IOException {
			Path pathOutputDirectory = Path.of(this.xmlOutputDirectory);
			Files.createDirectories(pathOutputDirectory);
			
			return xmlOutputDirectory;
		}
		
		public String getXmlErrorDirectory() throws IOException {
			Path pathErrorDirectory = Path.of(this.xmlErrorDirectory);
			Files.createDirectories(pathErrorDirectory);
			
			return xmlErrorDirectory;
		}
		
		public void setXmlInputDirectory(String xmlInputDirectory) {
			this.xmlInputDirectory = xmlInputDirectory;
		}
		public void setXmlOutputDirectory(String xmlOutputDirectory) {
			this.xmlOutputDirectory = xmlOutputDirectory;
		}
		public void setXmlErrorDirectory(String xmlErrorDirectory) {
			this.xmlErrorDirectory = xmlErrorDirectory;
		}
		
	}

}
