package romanizat.voxpopuli.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import romanizat.voxpopuli.entity.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {

}