package romanizat.voxpopuli.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import romanizat.voxpopuli.entity.*;
import romanizat.voxpopuli.exception.UrlExistsException;
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
        String url = eventSuggestion.getUrl().trim();
        String[] splitUrl;
        if (url.contains("watch")) {
            splitUrl = url.split("=");
        } else {
            splitUrl = url.split("/");
        }
        url = splitUrl[splitUrl.length - 1];
        eventSuggestion.setUrl(url);
        if (songExistsInEvent(eventSuggestion.getUrl(), eventSuggestion.getEvent().getId())) {
            throw new UrlExistsException();
        }

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

    @Override
    public List<EventSuggestion> getAllEventSuggestionsForEvent(Integer idEvent) {
        return eventSuggestionRepository.findAllEventSuggestionsForEvent(idEvent);
    }

    private boolean songExistsInEvent(String url, Integer eventId) {
        List<EventSuggestion> eventSuggestionList = this.findAll();
        for (EventSuggestion eventSuggestion : eventSuggestionList) {
            if (eventSuggestion.getUrl().equals(url) && eventSuggestion.getEvent().getId().equals(eventId)) {
                return true;
            }
        }
        return false;
    }

}