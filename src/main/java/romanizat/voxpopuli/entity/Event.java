package romanizat.voxpopuli.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.*;
import java.util.*;
import javax.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "event")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Event extends Auditable {
	@Id
	@EqualsAndHashCode.Include
	@Column(name = "id_event")
	private Integer id;
	@Column(name = "name")
	private String name;
	@Column(name = "date")
	private LocalDateTime date;
	@Column(name = "location")
	private String location;
	
}