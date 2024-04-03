package jhonerodrigues.com.passin.domain.checkin.exception;

public class CheckInAlreadyExistsException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public CheckInAlreadyExistsException(String msg) {
		super(msg);
	}
}
