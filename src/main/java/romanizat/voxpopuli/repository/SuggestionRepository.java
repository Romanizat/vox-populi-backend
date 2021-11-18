package romanizat.voxpopuli.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import romanizat.voxpopuli.entity.Suggestion;

@Repository
public interface SuggestionRepository extends JpaRepository<Suggestion, Integer> {

}