package app.exeption;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import app.exeption.error.ProfesseurError;


@ControllerAdvice
public class ProfesseurExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<ProfesseurError> handleExeption(ProfesseurException e) {
		ProfesseurError error = new ProfesseurError();
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(e.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ProfesseurError> handleException(Exception e) {
		ProfesseurError error = new ProfesseurError();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(e.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
