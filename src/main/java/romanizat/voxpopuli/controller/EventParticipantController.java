package romanizat.voxpopuli.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import romanizat.voxpopuli.entity.EventParticipant;
import romanizat.voxpopuli.service.EventParticipantService;

import java.util.List;

@RestController
@RequestMapping("/event-participants")
@RequiredArgsConstructor
public class EventParticipantController {
    private final EventParticipantService eventParticipantService;

    @GetMapping
    @ApiOperation(value = "", nickname = "getAllEventParticipants")
    public ResponseEntity<List<EventParticipant>> getAllEventParticipants() {
        return ResponseEntity.ok(eventParticipantService.findAll());
    }

    @GetMapping("/{idEventParticipant}")
    @ApiOperation(value = "", nickname = "getEventParticipantById")
    public ResponseEntity<EventParticipant> getEventParticipantById(@PathVariable Integer idEventParticipant) {
        return ResponseEntity.ok(eventParticipantService.findById(idEventParticipant));
    }

    @GetMapping("/{idEvent}/{idUser}")
    @ApiOperation(value = "", nickname = "getEventParticipantByEventIdAndUserId")
    public ResponseEntity<EventParticipant> getEventParticipantByEventIdAndUserId(@PathVariable Integer idEvent, @PathVariable Integer idUser) {
        return ResponseEntity.ok(eventParticipantService.findByIdEventIdAndUserId(idEvent, idUser));
    }

    @GetMapping("/event-id/{idEvent}")
    @ApiOperation(value = "", nickname = "getAllEventParticipantsByEventId")
    public ResponseEntity<List<EventParticipant>> getAllEventParticipantByEventId(@PathVariable Integer idEvent) {
        return ResponseEntity.ok(eventParticipantService.findAllEventParticipantsByEventId(idEvent));
    }

    @PostMapping
    @ApiOperation(value = "", nickname = "saveEventParticipant")
    public ResponseEntity<EventParticipant> saveEventParticipant(@RequestBody EventParticipant eventParticipant) {
        return ResponseEntity.status(HttpStatus.CREATED).body(eventParticipantService.save(eventParticipant));
    }

    @PutMapping
    @ApiOperation(value = "", nickname = "updateEventParticipant")
    public ResponseEntity<EventParticipant> updateEventParticipant(@RequestBody EventParticipant eventParticipant) {
        return ResponseEntity.ok(eventParticipantService.update(eventParticipant));
    }

    @DeleteMapping("/{idEventParticipant}")
    @ApiOperation(value = "", nickname = "deleteEventParticipantById")
    public void deleteEventParticipantById(@PathVariable Integer idEventParticipant) {
        eventParticipantService.deleteById(idEventParticipant);
    }

}

