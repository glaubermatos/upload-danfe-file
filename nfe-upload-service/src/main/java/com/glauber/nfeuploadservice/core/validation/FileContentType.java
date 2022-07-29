package com.glauber.nfeuploadservice.core.validation;

import static java.lang.annotation.ElementType.*;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.glauber.nfeuploadservice.core.validation.validator.FileContentTypeValidator;

@Retention(RetentionPolicy.RUNTIME)
@Target({ FIELD, METHOD, PARAMETER, CONSTRUCTOR, ANNOTATION_TYPE, TYPE_USE })
@Constraint(validatedBy = {FileContentTypeValidator.class})
public @interface FileContentType {
	
	String message() default "O arquivo deve ser do tipo {allowed}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
	
	String [] allowed();
}
