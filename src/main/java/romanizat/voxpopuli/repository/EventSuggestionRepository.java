package romanizat.voxpopuli.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import romanizat.voxpopuli.entity.EventSuggestion;

import java.util.List;

@Repository
public interface EventSuggestionRepository extends JpaRepository<EventSuggestion, Integer> {

    @Query(value = "SELECT es.* " +
            "FROM event_suggestion es " +
            "WHERE es.id_event = :eventId order by es.position ", nativeQuery = true)
    List<EventSuggestion> findAllEventSuggestionsForEvent(@Param("eventId") Integer eventId);

}