package romanizat.voxpopuli.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "event_participant")
public class EventParticipant extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_event_participant")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private User user;
    @ManyToOne
    @JoinColumn(name = "id_event", referencedColumnName = "id_event")
    private Event event;
    @Column(name = "organizer")
    private Boolean organizer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventParticipant eventParticipant = (EventParticipant) o;
        return id.equals(eventParticipant.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}