package romanizat.voxpopuli.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import romanizat.voxpopuli.entity.EventSuggestion;

@Repository
public interface EventSuggestionRepository extends JpaRepository<EventSuggestion, Integer> {

}