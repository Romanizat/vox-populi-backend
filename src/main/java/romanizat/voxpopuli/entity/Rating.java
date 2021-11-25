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
@Table(name = "rating")
@RequiredArgsConstructor
public class Rating extends Auditable {
    @Id
    @Column(name = "id_rating")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "id_suggestion", referencedColumnName = "id_suggestion")
    private Suggestion suggestion;
    @ManyToOne
    @JoinColumn(name = "id_event_participant", referencedColumnName = "id_event_participant")
    private EventParticipant eventParticipant;
    @Column(name = "rating")
    private Integer rating;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating = (Rating) o;
        return id.equals(rating.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}