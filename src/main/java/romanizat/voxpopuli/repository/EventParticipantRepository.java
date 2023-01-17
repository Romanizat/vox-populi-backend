package romanizat.voxpopuli.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import romanizat.voxpopuli.entity.EventParticipant;

import java.util.List;

@Repository
public interface EventParticipantRepository extends JpaRepository<EventParticipant, Integer> {
    @Query(value = "select * from event_participant " +
            "where event_participant.id_event = :idEvent " +
            "and event_participant.id_user = :idUser ", nativeQuery = true)
    EventParticipant findByEventIdAndUserId(@Param("idEvent") Integer idEvent, @Param("idUser") Integer idUser);

    void deleteAllByEventId(Integer idEvent);

    List<EventParticipant> findAllByEventId(Integer idEvent);

    @Query(value = "select count(*) " +
            "from event_participant " +
            "where id_user = :idUser " +
            "  and organizer = 1", nativeQuery = true)
    Integer getNumberOfEventsOrganizedByUserId(@Param("idUser") Integer idUser);
}