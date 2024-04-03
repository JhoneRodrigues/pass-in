package jhonerodrigues.com.passin.config;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import jhonerodrigues.com.passin.domain.attendee.exceptions.AttendeeAlreadyExistException;
import jhonerodrigues.com.passin.domain.attendee.exceptions.AttendeeNotFoundException;
import jhonerodrigues.com.passin.domain.checkin.exception.CheckInAlreadyExistsException;
import jhonerodrigues.com.passin.domain.event.exceptions.EventFullException;
import jhonerodrigues.com.passin.domain.event.exceptions.EventNotFoundException;

@ControllerAdvice
public class ExceptionEntityHandler {
	
	@ExceptionHandler(EventNotFoundException.class)
	public ResponseEntity<StandardError> handleEventNotFound(EventNotFoundException exception, HttpServletRequest request) {
		String error = "Not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, exception.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(AttendeeNotFoundException.class)
	public ResponseEntity<StandardError> handleAttendeeNotFound(AttendeeNotFoundException exception, HttpServletRequest request) {
		String error = "Not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, exception.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(AttendeeAlreadyExistException.class)
	public ResponseEntity<StandardError> handleAttendeeAlreadyExist(AttendeeAlreadyExistException exception, HttpServletRequest request) {
		String error = "Already exist";
		HttpStatus status = HttpStatus.CONFLICT;
		StandardError err = new StandardError(Instant.now(), status.value(), error, exception.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(CheckInAlreadyExistsException.class)
	public ResponseEntity<StandardError> handleCheckInAlreadyExist(CheckInAlreadyExistsException exception, HttpServletRequest request) {
		String error = "Already registered";
		HttpStatus status = HttpStatus.CONFLICT;
		StandardError err = new StandardError(Instant.now(), status.value(), error, exception.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(EventFullException.class)
	public ResponseEntity<StandardError> handleEventFull(EventFullException exception, HttpServletRequest request) {
		String error = "It's full";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, exception.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}
