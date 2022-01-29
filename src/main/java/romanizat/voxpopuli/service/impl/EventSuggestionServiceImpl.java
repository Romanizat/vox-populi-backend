package romanizat.voxpopuli.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import romanizat.voxpopuli.entity.EventSuggestion;
import romanizat.voxpopuli.exception.UrlExistsException;
import romanizat.voxpopuli.repository.EventSuggestionRepository;
import romanizat.voxpopuli.service.EventSuggestionService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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
        String url = eventSuggestion.getUrl().trim();
        eventSuggestion.setUrl(prettifyUrl(url));
        if (songExistsInEvent(eventSuggestion.getUrl(), eventSuggestion.getEvent().getId())) {
            throw new UrlExistsException();
        }
        // get maximum position of event suggestion for event and increment the value for the new event suggestion
        eventSuggestion.setPosition(eventSuggestionRepository.findMaxPositionForEventSuggestionByEventId(eventSuggestion.getEvent().getId()) + 1);

        return eventSuggestionRepository.save(eventSuggestion);
    }

    @Override
    public EventSuggestion update(EventSuggestion eventSuggestion) {
        return eventSuggestionRepository.save(eventSuggestion);
    }

    @Override
    public void deleteById(Integer idEventSuggestion) {
        Integer position = this.findById(idEventSuggestion).getPosition();
        eventSuggestionRepository.deleteById(idEventSuggestion);
        // Get all Event Suggestions after the position of the deleted Event Suggestion
        List<EventSuggestion> eventSuggestionList = eventSuggestionRepository.findAllWithPositionGreaterThanSelectedPosition(position);
        // Iterate through the list and decrement the positions
        for (EventSuggestion eventSuggestion : eventSuggestionList) {
            eventSuggestion.setPosition(eventSuggestion.getPosition() - 1);
            eventSuggestionRepository.save(eventSuggestion);
        }

    }

    @Override
    public List<EventSuggestion> getAllEventSuggestionsForEvent(Integer idEvent) {
        return eventSuggestionRepository.findAllEventSuggestionsForEventByEventIdOrderedByPosition(idEvent);
    }

    @Override
    public void removeAllSuggestionsForEvent(Integer idEvent) {
        eventSuggestionRepository.deleteAllByEventId(idEvent);
    }

    private boolean songExistsInEvent(String url, Integer eventId) {
        List<EventSuggestion> eventSuggestionList = eventSuggestionRepository.findAllByEventId(eventId);
        for (EventSuggestion eventSuggestion : eventSuggestionList) {
            if (eventSuggestion.getUrl().equals(url) && eventSuggestion.getEvent().getId().equals(eventId)) {
                return true;
            }
        }
        return false;
    }

    private String prettifyUrl(String youTubeUrl) {
        String[] splitUrl;
        if (youTubeUrl.contains("watch")) {
            splitUrl = youTubeUrl.split("=");
        } else {
            splitUrl = youTubeUrl.split("/");
        }
        return splitUrl[splitUrl.length - 1];
    }

    //TODO: method to set order on change
    private void changeEventSuggestionOrderInEvent(EventSuggestion eventSuggestion, Integer newPosition) {
        List<EventSuggestion> eventSuggestionList = eventSuggestionRepository.findAllByEventId(eventSuggestion.getEvent().getId());
        eventSuggestionList.remove(eventSuggestion);
        List<EventSuggestion> eventSuggestionListBeforePosition = new ArrayList<>();
        List<EventSuggestion> eventSuggestionListAfterPosition = new ArrayList<>();
        int oldPosition = eventSuggestion.getPosition();
        eventSuggestionList.forEach(eventSuggestion1 -> {
            if (eventSuggestion1.getPosition() < oldPosition) {
                eventSuggestionListBeforePosition.add(eventSuggestion1);
            } else {
                eventSuggestionListAfterPosition.add(eventSuggestion1);
            }
        });
    }

}