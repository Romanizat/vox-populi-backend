package romanizat.voxpopuli.service;

import java.util.Collection;
import java.util.List;
import romanizat.voxpopuli.entity.*;

public interface EventParticipantService {

	List<EventParticipant> findAll();

	EventParticipant save(EventParticipant eventParticipant);

	EventParticipant update(EventParticipant eventParticipant);

	EventParticipant findById(Integer idEventParticipant);

	void deleteById(Integer idEventParticipant);

}