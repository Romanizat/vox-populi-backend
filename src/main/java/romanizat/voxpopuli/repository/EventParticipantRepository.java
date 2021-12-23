package romanizat.voxpopuli.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import romanizat.voxpopuli.entity.Event;
import romanizat.voxpopuli.entity.EventParticipant;

import java.util.List;

@Repository
public interface EventParticipantRepository extends JpaRepository<EventParticipant, Integer> {

    List<Event> findAllEventsByUserId(Integer userId);
}