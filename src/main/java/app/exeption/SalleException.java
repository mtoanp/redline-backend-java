package app.exeption;

@SuppressWarnings("serial")
public class SalleException extends RuntimeException {

	public SalleException (String message) {
		super(message);
	}
	
	public SalleException (String message, Throwable cause) {
		super(message, cause);
	}
	
	public SalleException (Throwable cause) {
		super(cause);
	}
}
