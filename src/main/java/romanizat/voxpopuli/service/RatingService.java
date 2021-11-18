package romanizat.voxpopuli.service;

import java.util.Collection;
import java.util.List;
import org.springframework.data.domain.Sort;
import romanizat.voxpopuli.entity.*;

public interface RatingService {

	List<Rating> findAll();

	Rating save(Rating rating);

	Rating update(Rating rating);

	Rating findById(Integer idRating);

	void deleteById(Integer idRating);

}