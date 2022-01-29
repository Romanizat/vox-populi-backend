package romanizat.voxpopuli.service;

import romanizat.voxpopuli.entity.EventSuggestion;

import java.util.List;

public interface EventSuggestionService {

    List<EventSuggestion> findAll();

    EventSuggestion save(EventSuggestion eventSuggestion);

    EventSuggestion update(EventSuggestion eventSuggestion);

    EventSuggestion findById(Integer idEventSuggestion);

    void deleteById(Integer idEventSuggestion);

    List<EventSuggestion> getAllEventSuggestionsForEvent(Integer idEvent);

    void removeAllSuggestionsForEvent(Integer idEvent);

    void changeEventSuggestionOrderInEvent(Integer oldPosition, Integer newPosition, Integer eventId);
}