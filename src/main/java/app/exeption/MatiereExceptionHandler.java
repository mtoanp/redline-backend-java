package app.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import app.exeption.error.MatiereError;

@ControllerAdvice
public class MatiereExceptionHandler {

	public MatiereExceptionHandler() {
		// TODO Auto-generated constructor stub
	}

@ExceptionHandler
public ResponseEntity<MatiereError> handleExeption(MatiereException e) {
	MatiereError error = new MatiereError();
	error.setStatus(HttpStatus.NOT_FOUND.value());
	error.setMessage(e.getMessage());
	error.setTimeStamp(System.currentTimeMillis());
	return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
}

@ExceptionHandler
public ResponseEntity<MatiereError> handleException(Exception e) {
	MatiereError error = new MatiereError();
	error.setStatus(HttpStatus.BAD_REQUEST.value());
	error.setMessage(e.getMessage());
	error.setTimeStamp(System.currentTimeMillis());
	return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
}
}



