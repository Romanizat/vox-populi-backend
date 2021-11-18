package romanizat.voxpopuli.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.*;
import java.util.*;
import javax.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "suggestion")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Suggestion extends Auditable {
	@Id
	@EqualsAndHashCode.Include
	@Column(name = "id_suggestion")
	private Integer id;
	@JoinColumn(name = "id_event_suggestion", referencedColumnName = "id_event_suggestion")
	@ManyToOne
	private EventSuggestion idEventSuggestion;
	@Column(name = "title")
	private String title;
	@Column(name = "url")
	private String url;
	
}