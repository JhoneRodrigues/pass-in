package jhonerodrigues.com.passin.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jhonerodrigues.com.passin.dto.event.EventIdDTO;
import jhonerodrigues.com.passin.dto.event.EventRequestDTO;
import jhonerodrigues.com.passin.dto.event.EventResponseDTO;
import jhonerodrigues.com.passin.services.EventService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
	
	private final EventService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<EventResponseDTO> getEvent(@PathVariable String id){
		EventResponseDTO event = this.service.getEventDetails(id);
		return ResponseEntity.ok().body(event);
	}
	
	@PostMapping
	public ResponseEntity<EventIdDTO> createEvent(@RequestBody EventRequestDTO request, UriComponentsBuilder uriComponentsBuilder){
		EventIdDTO eventIdDTO = this.service.createEvent(request);
		
		var uri = uriComponentsBuilder.path("/events/{id}").buildAndExpand(eventIdDTO.eventId()).toUri();
		
		return ResponseEntity.created(uri).body(eventIdDTO);
	}
}
