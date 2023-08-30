package app.exeption.error;

public class FormationError extends Error {

	public FormationError() { super(); };
	
	public FormationError(int status, String message, long timeStamp) {
		super(status, message, timeStamp);
	}
	
}
