package romanizat.voxpopuli.service;

import java.util.Collection;
import java.util.List;
import org.springframework.data.domain.Sort;
import romanizat.voxpopuli.entity.*;

public interface EventService {

	List<Event> findAll();

	Event save(Event event);

	Event update(Event event);

	Event findById(Integer idEvent);

	void deleteById(Integer idEvent);

}