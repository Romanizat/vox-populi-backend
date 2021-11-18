package romanizat.voxpopuli.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.*;
import java.util.*;
import javax.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "rating")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Rating extends Auditable {
	@Id
	@EqualsAndHashCode.Include
	@Column(name = "id_rating")
	private Integer id;
	@JoinColumn(name = "id_suggestion", referencedColumnName = "id_suggestion")
	@ManyToOne
	private Suggestion idSuggestion;
	@JoinColumn(name = "id_event_participant", referencedColumnName = "id_event_participant")
	@ManyToOne
	private EventParticipant idEventParticipant;
	@Column(name = "rating")
	private Integer rating;
	
}