package app.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import app.exeption.error.AuthenticateError;


@ControllerAdvice
public class AuthenticateExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<AuthenticateError> handleExeption(AuthenticateException e) {
		AuthenticateError error = new AuthenticateError();
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(e.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
}
