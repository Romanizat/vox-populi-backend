package romanizat.voxpopuli.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import romanizat.voxpopuli.entity.Event;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

    @Query(value = "SELECT e.* " +
            "FROM event e, event_participant ep " +
            "WHERE e.id_event = ep.id_event AND ep.id_user=:userId ", nativeQuery = true)
    List<Event> findAllEventsByUserId(@Param("userId") Integer userId);
}