package jhonerodrigues.com.passin.domain.checkin;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jhonerodrigues.com.passin.domain.attendee.Attendee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "check_in")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CheckIn {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	@OneToOne
	@JoinColumn(name = "attendee_id", nullable = false)
	private Attendee attendee;
}
