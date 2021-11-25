package romanizat.voxpopuli.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import romanizat.voxpopuli.entity.*;
import romanizat.voxpopuli.repository.EventParticipantRepository;
import romanizat.voxpopuli.service.EventParticipantService;

@Service
@RequiredArgsConstructor
public class EventParticipantServiceImpl implements EventParticipantService {
	private final EventParticipantRepository eventParticipantRepository;

	@Override
	public List<EventParticipant> findAll() {
		return eventParticipantRepository.findAll();
	}

	@Override
	public EventParticipant findById(Integer idEventParticipant) {
		return eventParticipantRepository.findById(idEventParticipant)
				.orElseThrow(() -> new NoSuchElementException("EventParticipantService.notFound"));
	}

	@Override
	public EventParticipant save(EventParticipant eventParticipant) {
		return eventParticipantRepository.save(eventParticipant);
	}

	@Override
	public EventParticipant update(EventParticipant eventParticipant) {
		return eventParticipantRepository.save(eventParticipant);
	}

	@Override
	public void deleteById(Integer idEventParticipant) {
		eventParticipantRepository.deleteById(idEventParticipant);
	}


}