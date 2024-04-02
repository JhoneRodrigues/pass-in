package jhonerodrigues.com.passin.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jhonerodrigues.com.passin.domain.checkin.CheckIn;

public interface CheckinRepository extends JpaRepository<CheckIn, Integer>{
	
	Optional<CheckIn> findByAttendeeId(String attendeeId);
}
