package romanizat.voxpopuli.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import romanizat.voxpopuli.entity.*;
import romanizat.voxpopuli.repository.RatingRepository;
import romanizat.voxpopuli.service.RatingService;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {
	private final RatingRepository ratingRepository;

	@Override
	public List<Rating> findAll() {
		return ratingRepository.findAll();
	}

	@Override
	public Rating findById(Integer idRating) {
		return ratingRepository.findById(idRating)
				.orElseThrow(() -> new NoSuchElementException("RatingService.notFound"));
	}

	@Override
	public Rating save(Rating rating) {
		return ratingRepository.save(rating);
	}

	@Override
	public Rating update(Rating rating) {
		return ratingRepository.save(rating);
	}

	@Override
	public void deleteById(Integer idRating) {
		ratingRepository.deleteById(idRating);
	}


}