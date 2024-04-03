package jhonerodrigues.com.passin.domain.attendee.exceptions;

public class AttendeeNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public AttendeeNotFoundException (String msg) {
		super(msg);
	}
}
