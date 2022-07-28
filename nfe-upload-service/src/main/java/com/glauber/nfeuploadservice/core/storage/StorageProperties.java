package com.glauber.nfeuploadservice.core.storage;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import com.glauber.nfeuploadservice.core.validation.StorageTypeLocalNotEmptyXmlInputDirectory;

@Validated 
//@StorageTypeLocalNotEmptyXmlInputDirectory(type = "type", xmlInputDirectory = "xmlInputDirectory", message = "StorageType.LOCAL requer preenchimento da propriedade xmlInputDirectory")
@Component
@ConfigurationProperties(prefix = "nfe-upload-service.storage")
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

		public String getXmlInputDirectory() {
			return xmlInputDirectory;
		}
		public void setXmlInputDirectory(String xmlInputDirectory) {
			this.xmlInputDirectory = xmlInputDirectory;
		}
		
	}

}
