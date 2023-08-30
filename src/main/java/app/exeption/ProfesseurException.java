package app.exeption;

public class ProfesseurException extends RuntimeException{

	public ProfesseurException(String message) {
		super(message);
	}
	
	public ProfesseurException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ProfesseurException(Throwable cause) {
		super(cause);
	}
	
	
}