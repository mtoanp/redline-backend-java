package app.exeption;

public class MatiereException extends RuntimeException {

	public MatiereException(String message) {
		super(message);
	}
	
	public MatiereException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public MatiereException(Throwable cause) {
		super(cause);
	}
	
}
