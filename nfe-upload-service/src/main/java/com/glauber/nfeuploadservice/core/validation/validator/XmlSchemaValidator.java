package com.glauber.nfeuploadservice.core.validation.validator;

import java.io.File;
import java.nio.file.Path;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.springframework.web.multipart.MultipartFile;

import com.glauber.nfeuploadservice.core.validation.XmlSchema;

public class XmlSchemaValidator implements ConstraintValidator<XmlSchema, MultipartFile> {

	@Override
	public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
		boolean isValid = false;
		
		try {
			SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
			String xsdProcNfe = "src/main/resources/xsd/procNFe_v4.00.xsd";			
			File xsdSchemaFile = Path.of(xsdProcNfe).toFile();
			
			Schema schema = schemaFactory.newSchema(xsdSchemaFile);
			Validator validator = schema.newValidator();  
			
			Source source = new StreamSource(value.getInputStream());
			
			validator.validate(source);
			
			isValid  = true;
			
		} catch (Exception e) {
			return isValid;
		}
		return isValid;
	}

}
