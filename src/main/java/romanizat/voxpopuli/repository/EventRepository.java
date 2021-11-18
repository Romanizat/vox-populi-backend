package romanizat.voxpopuli.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import romanizat.voxpopuli.entity.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

}