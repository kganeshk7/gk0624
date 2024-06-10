package com.demo.gk0624.exception.handler;

import com.demo.gk0624.exception.ApplicationException;
import com.demo.gk0624.exception.model.ErrorDetails;
import com.demo.gk0624.exception.model.FieldValidationError;
import com.demo.gk0624.util.ApplicationConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GenericExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({Exception.class })
	public final ResponseEntity<?> handleGenericExceptions(Exception ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), ex.getCause()!=null ? ex.getCause().getMessage():ex.getMessage(),
				request.getDescription(false));
		// logging all the exceptions here. Just in case an anything is uncaught
       logger.error(ex);
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}


	@ExceptionHandler({ ApplicationException.class})
	public final ResponseEntity<?> handleVoIPExceptions(Exception ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), ex.getCause()!=null ? ex.getCause().getMessage():ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorDetails> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), ex.getCause()!=null ? ex.getCause().getMessage():ex.getMessage(),
				request.getDescription(false));
		return ResponseEntity.badRequest().body(errorDetails);
	}

	// to Handle all generic method arugment validation and returns a proper format response.
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		System.out.println("I am here");
		ErrorDetails errorDetails = new ErrorDetails(
				new Date(), ApplicationConstants.VALIDATION_FAILED,
						ex.getBindingResult()
						.getFieldErrors()
						.stream()
						.map((field) -> new FieldValidationError(field.getField(), field.getCode(), field.getRejectedValue(),field.getDefaultMessage()))
						.toList(), request.getDescription(false));
		  logger.error(ex);
		System.out.println(ex.getMessage());

		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

}