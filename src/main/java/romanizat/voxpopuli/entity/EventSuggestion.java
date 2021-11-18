package romanizat.voxpopuli.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.*;
import java.util.*;
import javax.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "event_suggestion")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class EventSuggestion extends Auditable {
	@Id
	@EqualsAndHashCode.Include
	@Column(name = "id_event_suggestion")
	private Integer id;
	@JoinColumn(name = "id_event", referencedColumnName = "id_event")
	@ManyToOne
	private Event idEvent;
	@Column(name = "position")
	private Integer position;
	
}