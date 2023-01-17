package romanizat.voxpopuli.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import romanizat.voxpopuli.entity.DTOs.EventSuggestionDTO;
import romanizat.voxpopuli.entity.EventSuggestion;
import romanizat.voxpopuli.exception.UrlExistsException;
import romanizat.voxpopuli.repository.EventSuggestionRepository;
import romanizat.voxpopuli.service.EventSuggestionService;
import romanizat.voxpopuli.service.UserService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventSuggestionServiceImpl implements EventSuggestionService {
    private final EventSuggestionRepository eventSuggestionRepository;

    private final UserService userService;

    @Override
    public List<EventSuggestionDTO> findAll() {
        return eventSuggestionRepository.findAll().stream().map(EventSuggestion::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public EventSuggestion findById(Integer idEventSuggestion) {
        return eventSuggestionRepository.findById(idEventSuggestion)
                .orElseThrow(() -> new NoSuchElementException("EventSuggestionService.notFound"));
    }

    @Override
    public EventSuggestionDTO getEventSuggestionDTOById(Integer idEventSuggestion) {
        return findById(idEventSuggestion).mapToDTO();
    }

    @Override
    public EventSuggestionDTO save(EventSuggestionDTO eventSuggestionDTO) {
        EventSuggestion eventSuggestion = mapToEntity(eventSuggestionDTO);
        eventSuggestion.setUrl(prettifyUrl(eventSuggestion.getUrl().trim()));
        if (songExistsInEvent(eventSuggestion.getUrl(), eventSuggestion.getEvent().getId())) {
            throw new UrlExistsException();
        }
        // get maximum position of event suggestion for event and increment the value for the new event suggestion
        Integer maxPosition = eventSuggestionRepository.findMaxPositionForEventSuggestionByEventId(eventSuggestion.getEvent().getId());
        eventSuggestion.setPosition(maxPosition == null ? 0 : maxPosition + 1);

        return eventSuggestionRepository.save(eventSuggestion).mapToDTO();
    }

    @Override
    public EventSuggestionDTO update(EventSuggestionDTO eventSuggestionDTO) {
        return eventSuggestionRepository.save(mapToEntity(eventSuggestionDTO)).mapToDTO();
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
    public List<EventSuggestionDTO> getAllEventSuggestionsForEvent(Integer idEvent) {
        return eventSuggestionRepository.findAllEventSuggestionsForEventByEventIdOrderedByPosition(idEvent)
                .stream()
                .map(EventSuggestion::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void removeAllSuggestionsForEvent(Integer idEvent) {
        eventSuggestionRepository.deleteAllByEventId(idEvent);
    }

    @Override
    public void changeEventSuggestionOrderInEvent(Integer oldPosition, Integer newPosition, Integer eventId) {
        EventSuggestion eventSuggestion = eventSuggestionRepository.findByEventIdAndPosition(eventId, oldPosition);
        if (oldPosition < newPosition) {
            List<EventSuggestion> eventSuggestionsBetweenOldAndNewPosition = eventSuggestionRepository
                    .findAllBetweenPositionsIncludingEnd(oldPosition, newPosition, eventId);
            eventSuggestionsBetweenOldAndNewPosition.forEach(eventSuggestionBetween -> {
                eventSuggestionBetween.setPosition(eventSuggestionBetween.getPosition() - 1);
                eventSuggestionRepository.save(eventSuggestionBetween);
            });
        } else if (oldPosition > newPosition) {
            List<EventSuggestion> eventSuggestionsBetweenOldAndNewPosition = eventSuggestionRepository
                    .findAllBetweenPositionsAndIncludingStart(newPosition, oldPosition, eventId);
            eventSuggestionsBetweenOldAndNewPosition.forEach(eventSuggestionBetween -> {
                eventSuggestionBetween.setPosition(eventSuggestionBetween.getPosition() + 1);
                eventSuggestionRepository.save(eventSuggestionBetween);
            });
        }
        eventSuggestion.setPosition(newPosition);
        eventSuggestionRepository.save(eventSuggestion);
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

    private EventSuggestion mapToEntity(EventSuggestionDTO eventSuggestionDTO) {
        EventSuggestion eventSuggestion = new EventSuggestion();
        eventSuggestion.setId(eventSuggestionDTO.getId());
        eventSuggestion.setEvent(eventSuggestionDTO.getEvent());
        eventSuggestion.setPosition(eventSuggestionDTO.getPosition());
        eventSuggestion.setTitle(eventSuggestionDTO.getTitle());
        eventSuggestion.setUrl(eventSuggestionDTO.getUrl());
        eventSuggestion.setUser(userService.findById(eventSuggestionDTO.getCreatorUserId()));
        return eventSuggestion;
    }
}