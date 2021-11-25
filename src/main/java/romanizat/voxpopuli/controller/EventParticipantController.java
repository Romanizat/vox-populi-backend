package romanizat.voxpopuli.controller;

import java.util.List;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import romanizat.voxpopuli.entity.*;
import romanizat.voxpopuli.service.*;

@RestController
@RequestMapping("/event-participants")
@RequiredArgsConstructor
public class EventParticipantController {
    private final EventParticipantService eventParticipantService;

    @GetMapping
    public ResponseEntity<List<EventParticipant>> getAllEventParticipants() {
        return ResponseEntity.ok(eventParticipantService.findAll());
    }

    @GetMapping("/{idEventParticipant}")
    public ResponseEntity<EventParticipant> getEventParticipantById(@PathVariable Integer idEventParticipant) {
        return ResponseEntity.ok(eventParticipantService.findById(idEventParticipant));
    }

    @PostMapping
    public ResponseEntity<EventParticipant> saveEventParticipant(@RequestBody EventParticipant eventParticipant) {
        return ResponseEntity.status(HttpStatus.CREATED).body(eventParticipantService.save(eventParticipant));
    }

    @PutMapping
    public ResponseEntity<EventParticipant> updateEventParticipant(@RequestBody EventParticipant eventParticipant) {
        return ResponseEntity.ok(eventParticipantService.update(eventParticipant));
    }

    @DeleteMapping("/{idEventParticipant}")
    public void deleteEventParticipantById(@PathVariable Integer idEventParticipant) {
        eventParticipantService.deleteById(idEventParticipant);
    }

}

