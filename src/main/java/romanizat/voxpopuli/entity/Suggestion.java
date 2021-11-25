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
@Table(name = "suggestion")
public class Suggestion extends Auditable {
    @Id
    @Column(name = "id_suggestion")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "id_event_suggestion", referencedColumnName = "id_event_suggestion")
    private EventSuggestion eventSuggestion;
    @Column(name = "title")
    private String title;
    @Column(name = "url")
    private String url;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Suggestion suggestion = (Suggestion) o;
        return id.equals(suggestion.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}