package app.exeption;

@SuppressWarnings("serial")
public class CoursException extends RuntimeException {

	public CoursException (String message) {
		super(message);
	}
	
	public CoursException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public CoursException(Throwable cause) {
		super(cause);
	}
}
