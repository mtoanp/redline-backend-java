package app.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import app.exeption.error.SalleError;

@ControllerAdvice
public class SalleExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<SalleError> handleException(SalleException s) {
		SalleError error = new SalleError();
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(s.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<SalleError> handleException(Exception e) {
		SalleError error = new SalleError();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(e.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
