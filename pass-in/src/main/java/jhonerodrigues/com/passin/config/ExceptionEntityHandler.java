package jhonerodrigues.com.passin.config;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
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
}
