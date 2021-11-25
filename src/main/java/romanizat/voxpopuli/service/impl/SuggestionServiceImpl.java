package romanizat.voxpopuli.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import romanizat.voxpopuli.entity.*;
import romanizat.voxpopuli.repository.SuggestionRepository;
import romanizat.voxpopuli.service.SuggestionService;

@Service
@RequiredArgsConstructor
public class SuggestionServiceImpl implements SuggestionService {
	private final SuggestionRepository suggestionRepository;

	@Override
	public List<Suggestion> findAll() {
		return suggestionRepository.findAll();
	}

	@Override
	public Suggestion findById(Integer idSuggestion) {
		return suggestionRepository.findById(idSuggestion)
				.orElseThrow(() -> new NoSuchElementException("SuggestionService.notFound"));
	}

	@Override
	public Suggestion save(Suggestion suggestion) {
		return suggestionRepository.save(suggestion);
	}

	@Override
	public Suggestion update(Suggestion suggestion) {
		return suggestionRepository.save(suggestion);
	}

	@Override
	public void deleteById(Integer idSuggestion) {
		suggestionRepository.deleteById(idSuggestion);
	}


}