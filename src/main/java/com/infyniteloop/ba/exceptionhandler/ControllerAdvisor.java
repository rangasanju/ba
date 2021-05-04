package com.infyniteloop.ba.exceptionhandler;

import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.infyniteloop.ba.exceptions.MissingHeaderException;
import com.infyniteloop.ba.exceptions.ResourceNotFoundException;
import com.infyniteloop.ba.model.Error;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Error> handleResourceNotFoundException(ResourceNotFoundException ex) {

		Error e = new Error();
		e.setCode(HttpStatus.NOT_FOUND.value());
		e.setMessage(ex.getMessage());

		return new ResponseEntity<Error>(e, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MissingHeaderException.class)
	public ResponseEntity<Error> handleMissingHeaderException(MissingHeaderException ex) {

		Error e = new Error();
		e.setCode(HttpStatus.NOT_FOUND.value());
		e.setMessage(ex.getMessage());

		return new ResponseEntity<Error>(e, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<Error> handleNoSuchElementException(NoSuchElementException ex) {

		Error e = new Error();
		e.setCode(HttpStatus.NOT_FOUND.value());
		e.setMessage("ID not found");

		return new ResponseEntity<Error>(e, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody Error handleException(final Exception exception, final HttpServletRequest request) {

		Error error = new Error();
		error.setMessage(exception.getMessage());

		return error;
	}

}