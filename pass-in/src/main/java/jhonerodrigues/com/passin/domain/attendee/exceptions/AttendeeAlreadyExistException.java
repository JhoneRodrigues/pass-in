package jhonerodrigues.com.passin.domain.attendee.exceptions;

public class AttendeeAlreadyExistException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public AttendeeAlreadyExistException (String msg) {
		super(msg);
	}
}
