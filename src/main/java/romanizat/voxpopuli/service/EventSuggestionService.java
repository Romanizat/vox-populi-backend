package romanizat.voxpopuli.service;

import romanizat.voxpopuli.entity.EventSuggestion;
import romanizat.voxpopuli.entity.DTOs.EventSuggestionDTO;

import java.util.List;

public interface EventSuggestionService {

    List<EventSuggestionDTO> findAll();

    EventSuggestionDTO save(EventSuggestionDTO eventSuggestionDTO);

    EventSuggestionDTO update(EventSuggestionDTO eventSuggestionDTO);

    EventSuggestion findById(Integer idEventSuggestion);
    EventSuggestionDTO getEventSuggestionDTOById(Integer idEventSuggestion);

    void deleteById(Integer idEventSuggestion);

    List<EventSuggestionDTO> getAllEventSuggestionsForEvent(Integer idEvent);

    void removeAllSuggestionsForEvent(Integer idEvent);

    void changeEventSuggestionOrderInEvent(Integer oldPosition, Integer newPosition, Integer eventId);
}