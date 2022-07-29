package com.glauber.nfeuploadservice.core.validation.validator;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

import com.glauber.nfeuploadservice.core.validation.FileContentType;

public class FileContentTypeValidator implements ConstraintValidator<FileContentType, MultipartFile> {
	
	private Set<String> allowedContentTypes = new HashSet<>();
	
	@Override
	public void initialize(FileContentType constraintAnnotation) {
		for(String contentType: constraintAnnotation.allowed()) {
			allowedContentTypes.add(contentType);
		}
	}

	@Override
	public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
		return value == null 
              || this.allowedContentTypes.contains(value.getContentType());
	}

}
