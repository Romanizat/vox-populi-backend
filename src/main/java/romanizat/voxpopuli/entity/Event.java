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
@Table(name = "event")
@RequiredArgsConstructor
public class Event extends Auditable {
	@Id
	@Column(name = "id_event")
	private Integer id;
	@Column(name = "name")
	private String name;
	@Column(name = "date")
	private LocalDateTime date;
	@Column(name = "banned")
	private Boolean banned;
	@Column(name = "location")
	private String location;
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Event event = (Event) o;
		return id.equals(event.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


}