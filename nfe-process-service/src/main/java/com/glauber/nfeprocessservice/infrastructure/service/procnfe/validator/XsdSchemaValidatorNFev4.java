package com.glauber.nfeprocessservice.infrastructure.service.procnfe.validator;

import java.io.File;
import java.nio.file.Path;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.springframework.stereotype.Component;

@Component
public class XsdSchemaValidatorNFev4 {

	public boolean validate(File xmlFile) {
		boolean isValid = false;
		try {
			SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
			
			//TODO: Valida apenas notas na versão 4.0
			String xsdProcNfe = "src/main/resources/xsd/procNFe_v4.00.xsd";		
			
			File xsdSchemaFile = Path.of(xsdProcNfe).toFile();
			
			Schema schema = schemaFactory.newSchema(xsdSchemaFile);
			Validator validator = schema.newValidator();  
			
			Source source = new StreamSource(xmlFile);
			
			validator.validate(source);
			
			isValid = true;
			
		} catch (Exception e) {
			return isValid;
		} 
		
		return isValid;
	}
}
