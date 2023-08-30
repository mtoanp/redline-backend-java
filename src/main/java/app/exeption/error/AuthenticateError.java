package app.exeption.error;

public class AuthenticateError extends Error {
	public AuthenticateError() { super(); };
	
	public AuthenticateError(int status, String message, long timeStamp) {
		super(status, message, timeStamp);
	}
}
