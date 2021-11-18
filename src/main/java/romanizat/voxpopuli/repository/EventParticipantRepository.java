package romanizat.voxpopuli.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import romanizat.voxpopuli.entity.EventParticipant;

@Repository
public interface EventParticipantRepository extends JpaRepository<EventParticipant, Integer> {

}