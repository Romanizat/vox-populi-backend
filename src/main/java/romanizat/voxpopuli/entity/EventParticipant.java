package romanizat.voxpopuli.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.*;
import java.util.*;
import javax.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "event_participant")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class EventParticipant extends Auditable {
	@Id
	@EqualsAndHashCode.Include
	@Column(name = "id_event_participant")
	private Integer id;
	@JoinColumn(name = "id_user", referencedColumnName = "id_user")
	@ManyToOne
	private User idUser;
	@JoinColumn(name = "id_event", referencedColumnName = "id_event")
	@ManyToOne
	private Event idEvent;
	@Column(name = "organizer")
	private Boolean organizer;
	
}