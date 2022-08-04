package com.glauber.nfeuploadservice.api.controller;

import java.io.InputStream;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.glauber.nfeuploadservice.api.exceptionhandler.problem.ApiProblemDetail;
import com.glauber.nfeuploadservice.api.exceptionhandler.problem.ProblemType;
import com.glauber.nfeuploadservice.api.model.disassembler.NotaFiscalInputDisassembler;
import com.glauber.nfeuploadservice.api.model.input.NotaFiscalInput;
import com.glauber.nfeuploadservice.domain.model.NotaFiscal;
import com.glauber.nfeuploadservice.domain.service.NotaFiscalStorageService.NotaFiscalWrapper;
import com.glauber.nfeuploadservice.domain.service.UploadNotaFiscalService;

@RestController
@RequestMapping("/notas-fiscais")
public class UploadNotaFiscalController {
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private UploadNotaFiscalService uploadNotaFiscalService;
	
	@Autowired
	private NotaFiscalInputDisassembler notaFiscalInputDisassembler;
	
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public String upload(@Valid NotaFiscalInput notaFiscalInput) {
		try {
			NotaFiscal notaFiscal = notaFiscalInputDisassembler.toDomainModel(notaFiscalInput);
			
			InputStream xmlInputStream = notaFiscalInput.getInputStream();
			
			NotaFiscalWrapper notaFiscalWrapper = new NotaFiscalWrapper();
			notaFiscalWrapper.setFileName(notaFiscalInput.getFileName());
			notaFiscalWrapper.setInputStream(xmlInputStream);
			
			uploadNotaFiscalService.upload(notaFiscal, notaFiscalWrapper);
			
			return "Seu arquivo foi recepcionado com sucesso, está aguardando para ser processado.";
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@ExceptionHandler(BindException.class)
	public ResponseEntity<Object> handleBindException(BindException ex) {
		List<ApiProblemDetail.Field> fieldsWithError = getFieldsWithError(ex.getBindingResult());
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		Integer statusCode = status.value();
		ProblemType type = ProblemType.INVALID_DATA;
		String detail = "Erro de validação dos campos. Preencha corretamente e tente novamente.";
		
		ApiProblemDetail problem = createParcialProblemDetail(statusCode, type, detail);
		problem.setUserMessage(detail);
		problem.setFields(fieldsWithError);
		
		return ResponseEntity.status(status).body(problem);
	}

	private ApiProblemDetail createParcialProblemDetail(Integer statusCode, ProblemType type, String detail) {
		ApiProblemDetail problem = new ApiProblemDetail();
		problem.setStatus(statusCode);
		problem.setTitle(type.getTitle());
		problem.setTimestamp(OffsetDateTime.now());
		problem.setDetail(detail);
		
		return problem;
	}
	
	private List<ApiProblemDetail.Field> getFieldsWithError(BindingResult bindingResult) {
		return bindingResult.getFieldErrors().stream()
				.map(fieldError -> {
					var userMessage = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
					
					ApiProblemDetail.Field field = new ApiProblemDetail.Field(fieldError.getField(), userMessage);
					
					return field;
				})
				.collect(Collectors.toList());
	}

}
