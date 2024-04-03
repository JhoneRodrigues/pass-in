package jhonerodrigues.com.passin.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jhonerodrigues.com.passin.domain.attendee.Attendee;
import jhonerodrigues.com.passin.domain.checkin.CheckIn;
import jhonerodrigues.com.passin.domain.checkin.exception.CheckInAlreadyExistsException;
import jhonerodrigues.com.passin.repositories.CheckinRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CheckInService {
	
	private final CheckinRepository checkinRepository;
	
	public void registerCheckIn(Attendee attendee) {
		this.verifyCheckInExists(attendee.getId());
		
		CheckIn checkIn = new CheckIn();
		checkIn.setAttendee(attendee);
		checkIn.setCreatedAt(LocalDateTime.now());
		
		checkinRepository.save(checkIn);
	}
	
	private void verifyCheckInExists(String attendeId) {
		Optional<CheckIn> isCheckedIn = this.getCheckIn(attendeId);
		if (isCheckedIn.isPresent()) throw new CheckInAlreadyExistsException("Attendee already checked in");
	}
	
	public Optional<CheckIn> getCheckIn(String attendeeId){
		return this.checkinRepository.findByAttendeeId(attendeeId);
	}
}
