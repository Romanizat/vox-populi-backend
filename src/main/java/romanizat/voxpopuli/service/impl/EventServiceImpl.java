package romanizat.voxpopuli.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import romanizat.voxpopuli.entity.Event;
import romanizat.voxpopuli.entity.EventParticipant;
import romanizat.voxpopuli.repository.EventRepository;
import romanizat.voxpopuli.service.EventParticipantService;
import romanizat.voxpopuli.service.EventService;
import romanizat.voxpopuli.service.EventSuggestionService;
import romanizat.voxpopuli.service.UserService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final EventParticipantService eventParticipantService;
    private final UserService userService;
    private final EventSuggestionService eventSuggestionService;

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public Event findById(Integer idEvent) {
        return eventRepository.findById(idEvent)
                .orElseThrow(() -> new NoSuchElementException("EventService.notFound"));
    }

    @Override
    public Event save(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event update(Event event) {
        return eventRepository.save(event);
    }

    @Override
    @Transactional
    public void deleteById(Integer idEvent) {
        this.eventParticipantService.removeAllParticipantsForEvent(idEvent);
        this.eventSuggestionService.removeAllSuggestionsForEvent(idEvent);
        eventRepository.deleteById(idEvent);
    }

    @Override
    public List<Event> findAllEventsByUserId(Integer userId) {
        return eventRepository.findAllEventsByUserId(userId);
    }

    @Override
    public Event createEventByUserId(Event event, Integer userId) {
        event = eventRepository.save(event);
        EventParticipant eventParticipant = new EventParticipant();
        eventParticipant.setEvent(event);
        eventParticipant.setUser(userService.findById(userId));
        eventParticipant.setOrganizer(true);
        eventParticipantService.save(eventParticipant);
        return event;
    }


}