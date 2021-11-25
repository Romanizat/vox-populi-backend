package romanizat.voxpopuli.service;

import java.util.Collection;
import java.util.List;
import romanizat.voxpopuli.entity.*;

public interface EventSuggestionService {

	List<EventSuggestion> findAll();

	EventSuggestion save(EventSuggestion eventSuggestion);

	EventSuggestion update(EventSuggestion eventSuggestion);

	EventSuggestion findById(Integer idEventSuggestion);

	void deleteById(Integer idEventSuggestion);

}