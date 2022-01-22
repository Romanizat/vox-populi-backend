package romanizat.voxpopuli.service;

import romanizat.voxpopuli.entity.EventParticipant;

import java.util.List;

public interface EventParticipantService {

    List<EventParticipant> findAll();

    EventParticipant save(EventParticipant eventParticipant);

    EventParticipant update(EventParticipant eventParticipant);

    EventParticipant findById(Integer idEventParticipant);

    void deleteById(Integer idEventParticipant);

}