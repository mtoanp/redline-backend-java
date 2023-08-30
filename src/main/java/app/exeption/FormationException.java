package app.exeption;

public class FormationException extends RuntimeException {

	public FormationException(String message) {
		super(message);
	}
	
	public FormationException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public FormationException(Throwable cause) {
		super(cause);
	}
	
}
