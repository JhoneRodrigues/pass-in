package jhonerodrigues.com.passin.domain.event.exceptions;

public class EventFullException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public EventFullException (String msg) {
		super(msg);
	}
}
