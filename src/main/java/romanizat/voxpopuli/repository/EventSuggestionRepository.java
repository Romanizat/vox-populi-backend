package romanizat.voxpopuli.repository;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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


    @Query(value = "select es.* " +
            "from event_suggestion es " +
            "where es.position > :position order by es.position asc ", nativeQuery = true)
    List<EventSuggestion> findAllWithPositionGreaterThanSelectedPosition(@Param("position") Integer position);

    void deleteAllByEventId(Integer idEvent);

    EventSuggestion findByEventIdAndPosition(Integer eventId, Integer position);

    @Query(value = "select es.* " +
            "from event_suggestion es " +
            "where es.position > :startPosition and es.position<= :endPosition and es.id_event=:idEvent " +
            "order by es.position asc ", nativeQuery = true)
    List<EventSuggestion> findAllBetweenPositionsIncludingEnd(@Param("startPosition") Integer startPosition, @Param("endPosition") Integer endPosition, @Param("idEvent") Integer idEvent);

    @Query(value = "select es.* " +
            "from event_suggestion es " +
            "where es.position >= :startPosition and es.position< :endPosition and es.id_event=:idEvent " +
            "order by es.position asc ", nativeQuery = true)
    List<EventSuggestion> findAllBetweenPositionsAndIncludingStart(@Param("startPosition") Integer startPosition, @Param("endPosition") Integer endPosition, @Param("idEvent") Integer idEvent);

    @Query(value = "select es.* " +
            "from event_suggestion es " +
            "where es.position > :position and es.id_event=:idEvent order by es.position asc ", nativeQuery = true)
    List<EventSuggestion> findAllWithPositionGreaterThanSelectedPosition(@Param("position") Integer position, @Param("idEvent") Integer idEvent);
}