package romanizat.voxpopuli.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import romanizat.voxpopuli.entity.*;
import romanizat.voxpopuli.repository.EventSuggestionRepository;
import romanizat.voxpopuli.service.EventSuggestionService;

@Service
@RequiredArgsConstructor
public class EventSuggestionServiceImpl implements EventSuggestionService {
	private final EventSuggestionRepository eventSuggestionRepository;

	@Override
	public List<EventSuggestion> findAll() {
		return eventSuggestionRepository.findAll();
	}

	@Override
	public EventSuggestion findById(Integer idEventSuggestion) {
		return eventSuggestionRepository.findById(idEventSuggestion)
				.orElseThrow(() -> new NoSuchElementException("EventSuggestionService.notFound"));
	}

	@Override
	public EventSuggestion save(EventSuggestion eventSuggestion) {
		return eventSuggestionRepository.save(eventSuggestion);
	}

	@Override
	public EventSuggestion update(EventSuggestion eventSuggestion) {
		return eventSuggestionRepository.save(eventSuggestion);
	}

	@Override
	public void deleteById(Integer idEventSuggestion) {
		eventSuggestionRepository.deleteById(idEventSuggestion);
	}


}