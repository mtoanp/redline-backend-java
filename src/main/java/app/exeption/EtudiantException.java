package app.exeption;

@SuppressWarnings("serial")
public class EtudiantException extends RuntimeException {

	public EtudiantException (String message) {
		super(message);
	}
	
	public EtudiantException (String message, Throwable cause) {
		super(message, cause);
	}
	
	public EtudiantException (Throwable cause) {
		super(cause);
	}
}
