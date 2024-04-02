package jhonerodrigues.com.passin.services;

import java.text.Normalizer;
import java.util.List;

import org.springframework.stereotype.Service;

import jhonerodrigues.com.passin.domain.attendee.Attendee;
import jhonerodrigues.com.passin.domain.event.Event;
import jhonerodrigues.com.passin.dto.event.EventIdDTO;
import jhonerodrigues.com.passin.dto.event.EventRequestDTO;
import jhonerodrigues.com.passin.dto.event.EventResponseDTO;
import jhonerodrigues.com.passin.repositories.AttendeeRepository;
import jhonerodrigues.com.passin.repositories.EventRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventService {
	
	private final EventRepository eventRepository;
	private final AttendeeRepository attendeeRepository;

	
	public EventResponseDTO getEventDetails(String eventId) {
		Event event = this.eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found with ID: " + eventId));
		List<Attendee> attendeeList = this.attendeeRepository.findByEventId(eventId);
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
	
	private String createSlug(String text) {
		String normalized = Normalizer.normalize(text, Normalizer.Form.NFD);
		return normalized.replaceAll("[\\p{InCOMBINING_DIACRITICAL_MARKS}]", "")
				.replaceAll("[^\\w\\s]","")
				.replaceAll("\\s+", "_").toLowerCase();
	}
}
