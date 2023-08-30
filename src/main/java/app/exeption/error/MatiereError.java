package app.exeption.error;

public class MatiereError extends Error {

	public MatiereError() { super(); };
	
	public MatiereError(int status, String message, long timeStamp) {
		super(status, message, timeStamp);
	}
	
}

