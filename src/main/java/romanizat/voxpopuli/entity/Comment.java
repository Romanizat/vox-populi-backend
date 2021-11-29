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
@Table(name = "comment")
@RequiredArgsConstructor
public class Comment extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comment")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "id_suggestion", referencedColumnName = "id_suggestion")
    private Suggestion suggestion;
    @ManyToOne
    @JoinColumn(name = "id_event_participant", referencedColumnName = "id_event_participant")
    private EventParticipant eventParticipant;
    @Column(name = "comment")
    private String comment;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id.equals(comment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}