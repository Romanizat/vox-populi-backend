package romanizat.voxpopuli.service;

import romanizat.voxpopuli.entity.Event;

import java.util.List;

public interface EventService {

	List<Event> findAll();

	Event save(Event event);

	Event update(Event event);

	Event findById(Integer idEvent);

	void deleteById(Integer idEvent);

	List<Event> findAllEventsByUserId(Integer userId);

}