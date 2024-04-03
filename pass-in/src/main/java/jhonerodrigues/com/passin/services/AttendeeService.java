package jhonerodrigues.com.passin.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jhonerodrigues.com.passin.domain.attendee.Attendee;
import jhonerodrigues.com.passin.domain.attendee.exceptions.AttendeeAlreadyExistException;
import jhonerodrigues.com.passin.domain.checkin.CheckIn;
import jhonerodrigues.com.passin.dto.attendee.AttendeeDetails;
import jhonerodrigues.com.passin.dto.attendee.AttendeesListResponseDTO;
import jhonerodrigues.com.passin.repositories.AttendeeRepository;
import jhonerodrigues.com.passin.repositories.CheckinRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttendeeService {
	
	private final AttendeeRepository attendeeRepository;
	private final CheckinRepository checkInRepository;
	
	public List <Attendee> getAllAttendeesFromEvents(String eventId) {
		return this.attendeeRepository.findByEventId(eventId);
	}
	
	public AttendeesListResponseDTO getEventsAttendee(String eventId) {
		List <Attendee> attendeeList = this.getAllAttendeesFromEvents(eventId);
		
		List<AttendeeDetails> attendeeDetailsList = attendeeList.stream().map(attendee -> {
			Optional<CheckIn> checkIn = this.checkInRepository.findByAttendeeId(attendee.getId());
			LocalDateTime checkedInAt = checkIn.<LocalDateTime>map(CheckIn::getCreatedAt).orElse(null);
			return new AttendeeDetails(attendee.getId(), attendee.getName(), attendee.getEmail(), attendee.getCreatedAt(), checkedInAt);	
		}).toList();
		
		return new AttendeesListResponseDTO(attendeeDetailsList);
	}
	
	public void verifyAttendeeSubscription(String email, String eventId) {
		Optional<Attendee> isAttendeeRegistered =this.attendeeRepository.findByEventIdAndEmail(eventId,email);
		if (isAttendeeRegistered.isPresent()) throw new AttendeeAlreadyExistException("Attendee is already registered");
	}
	
	public Attendee registerAttendee(Attendee newAttendee) {
		this.attendeeRepository.save(newAttendee);
		return newAttendee;
	}
}
