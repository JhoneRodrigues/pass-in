package jhonerodrigues.com.passin.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jhonerodrigues.com.passin.dto.attendee.AttendeeBadgeResponseDTO;
import jhonerodrigues.com.passin.services.AttendeeService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/attendees")
@RequiredArgsConstructor
public class AttendeeController {

	private final AttendeeService attendeeService;
	
	@GetMapping("/{attendeeId}/badge")
	public ResponseEntity<AttendeeBadgeResponseDTO> getAttendeeBadge(@PathVariable String attendeeId, UriComponentsBuilder uriComponentsBuilder){
		AttendeeBadgeResponseDTO attendeeResponse = this.attendeeService.getAttendeeBadge(attendeeId, uriComponentsBuilder);
		return ResponseEntity.ok().body(attendeeResponse);
	}
}
