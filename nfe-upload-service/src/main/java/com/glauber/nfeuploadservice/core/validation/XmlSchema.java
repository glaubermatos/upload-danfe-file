package com.glauber.nfeuploadservice.core.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.glauber.nfeuploadservice.core.validation.validator.XmlSchemaValidator;


@Retention(RetentionPolicy.RUNTIME)
@Target({ FIELD, METHOD, PARAMETER, CONSTRUCTOR, ANNOTATION_TYPE, TYPE_USE })
@Constraint(validatedBy = {XmlSchemaValidator.class})
public @interface XmlSchema {
	
	String message() default "Arquivo xml da Nota Fiscal inválido";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
