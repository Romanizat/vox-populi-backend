package romanizat.voxpopuli.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import romanizat.voxpopuli.entity.DTOs.VoteDTO;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "vote")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vote")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "id_event_suggestion", referencedColumnName = "id_event_suggestion")
    private EventSuggestion eventSuggestion;
    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private User user;
    @Column(name = "upvote")
    private Boolean upvote;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vote eventSuggestion = (Vote) o;
        return id.equals(eventSuggestion.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    public VoteDTO mapToDTO() {
        return new VoteDTO(
                id,
                eventSuggestion.getId(),
                user.getId(),
                upvote
        );
    }
}