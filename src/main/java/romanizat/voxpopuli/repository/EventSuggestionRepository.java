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
    List<EventSuggestion> findAllEventSuggestionsForEventByEventIdOrderedByPosition(@Param("eventId") Integer eventId);

//    @Query(value = "SELECT es.* " +
//            "FROM event_suggestion es " +
//            "WHERE es.id_event = :eventId and es.url = :url ", nativeQuery = true)
//    EventSuggestion findAllEventSuggestionsForEvent(@Param("url") String url, @Param("eventId") Integer eventId);

    List<EventSuggestion> findAllByEventId(Integer eventId);

    @Query(value = "select max(es.position) " +
            "from event_suggestion es " +
            "where es.id_event = :eventId", nativeQuery = true)
    Integer findMaxPositionForEventSuggestionByEventId(@Param("eventId") Integer eventId);

}