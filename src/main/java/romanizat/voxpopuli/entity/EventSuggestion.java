package romanizat.voxpopuli.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.*;
import java.util.*;
import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "event_suggestion")
public class EventSuggestion extends Auditable {
	@Id
	@Column(name = "id_event_suggestion")
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "id_event", referencedColumnName = "id_event")
	private Event event;
	@Column(name = "position")
	private Integer position;
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		EventSuggestion eventSuggestion = (EventSuggestion) o;
		return id.equals(eventSuggestion.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


}