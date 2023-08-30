package app.exeption.error;

public class ProfesseurError  extends Error {

	public ProfesseurError() { super(); };
	
	public ProfesseurError(int status, String message, long timeStamp) {
		super(status, message, timeStamp);
	}
	
}