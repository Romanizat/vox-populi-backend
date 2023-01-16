package romanizat.voxpopuli.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import romanizat.voxpopuli.entity.DTOs.EventSuggestionDTO;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "event_suggestion")
public class EventSuggestion extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_event_suggestion")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "id_event", referencedColumnName = "id_event")
    private Event event;
    @Column(name = "position")
    private Integer position;
    @Column(name = "title")
    private String title;
    @Column(name = "url")
    private String url;
    @ManyToOne()
    @JoinColumn(name = "creator_user_fk", referencedColumnName = "id_user")
    private User user;
    @OneToMany(mappedBy = "eventSuggestions")
    private List<Vote> votes;

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

    public EventSuggestionDTO mapToDTO() {
        return new EventSuggestionDTO(
                id,
                event,
                position,
                title,
                url,
                user.getId(),
                votes.stream().map(Vote::mapToDTO).collect(Collectors.toList())
        );
    }
}