package jhonerodrigues.com.passin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import jhonerodrigues.com.passin.domain.event.Event;

public interface EventRepository extends JpaRepository<Event, String>{

}
