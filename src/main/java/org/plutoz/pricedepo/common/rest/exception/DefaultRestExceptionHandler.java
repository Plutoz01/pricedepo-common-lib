package org.plutoz.pricedepo.common.rest.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.annotation.JsonProperty;

@ControllerAdvice
public class DefaultRestExceptionHandler {
	
	private static final Logger LOG = LoggerFactory.getLogger(DefaultRestExceptionHandler.class);	
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ErrorMessage> handleDataIntegrityViolation(DataIntegrityViolationException exception){
		LOG.warn(exception.getMessage());
		
		//TODO: create a more informative (but not too informative) error message
		return new ResponseEntity<DefaultRestExceptionHandler.ErrorMessage>(new DefaultRestExceptionHandler.ErrorMessage("DataIntegrityViolation"), HttpStatus.CONFLICT);
	}
	
	public class ErrorMessage {
		private final String message;
		
		public ErrorMessage (String message){
			this.message = message;
		}
		
		@JsonProperty
		public String getMessage(){
			return this.message;
		}
	}
}
