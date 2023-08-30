package app.exeption;

public class AuthenticateException extends RuntimeException {
	public AuthenticateException(String message) {
		super(message);
	}
	
	public AuthenticateException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public AuthenticateException(Throwable cause) {
		super(cause);
	}
}
