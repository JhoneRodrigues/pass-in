package jhonerodrigues.com.passin.services;

import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import jhonerodrigues.com.passin.domain.attendee.Attendee;
import jhonerodrigues.com.passin.domain.event.Event;
import jhonerodrigues.com.passin.domain.event.exceptions.EventFullException;
import jhonerodrigues.com.passin.domain.event.exceptions.EventNotFoundException;
import jhonerodrigues.com.passin.dto.attendee.AttendeeIdDTO;
import jhonerodrigues.com.passin.dto.attendee.AttendeeRequestDTO;
import jhonerodrigues.com.passin.dto.event.EventIdDTO;
import jhonerodrigues.com.passin.dto.event.EventRequestDTO;
import jhonerodrigues.com.passin.dto.event.EventResponseDTO;
import jhonerodrigues.com.passin.repositories.EventRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventService {
	
	private final EventRepository eventRepository;
	private final AttendeeService attendeeService;

	
	public EventResponseDTO getEventDetails(String eventId) {
		Event event = this.getEventById(eventId);
		List<Attendee> attendeeList = this.attendeeService.getAllAttendeesFromEvents(eventId);
		return new EventResponseDTO(event, attendeeList.size());
	}

	public EventIdDTO createEvent(EventRequestDTO request) {
		Event newEvent = new Event();
		
		newEvent.setTitle(request.title());
		newEvent.setDetails(request.details());
		newEvent.setMaximumAttendees(request.maximumAttendees());
		newEvent.setSlug(this.createSlug(request.title()));
		
		this.eventRepository.save(newEvent);
		
		return new EventIdDTO(newEvent.getId());
	}
	
	public AttendeeIdDTO registerAttendeeOnEvent(String eventId, AttendeeRequestDTO attendeeRequest) {
		this.attendeeService.verifyAttendeeSubscription(attendeeRequest.email(),eventId);
		
		Event event = this.getEventById(eventId);
		List<Attendee> attendeeList = this.attendeeService.getAllAttendeesFromEvents(eventId);
		
		if (event.getMaximumAttendees() <= attendeeList.size()) throw new EventFullException("Event is full");
		
		Attendee newAttendee = new Attendee();
		newAttendee.setName(attendeeRequest.name());
		newAttendee.setEmail(attendeeRequest.email());
		newAttendee.setEvent(event);
		newAttendee.setCreatedAt(LocalDateTime.now());
		this.attendeeService.registerAttendee(newAttendee);
		
		return new AttendeeIdDTO(newAttendee.getId());
	}
	
	private Event getEventById(String eventId) {
		return this.eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException("Event not found with ID: " + eventId)); 
	}
	
	private String createSlug(String text) {
		String normalized = Normalizer.normalize(text, Normalizer.Form.NFD);
		return normalized.replaceAll("[\\p{InCOMBINING_DIACRITICAL_MARKS}]", "")
				.replaceAll("[^\\w\\s]","")
				.replaceAll("\\s+", "_").toLowerCase();
	}
}
