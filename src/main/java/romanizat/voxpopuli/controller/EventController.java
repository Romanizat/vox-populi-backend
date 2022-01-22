package romanizat.voxpopuli.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import romanizat.voxpopuli.entity.Event;
import romanizat.voxpopuli.service.EventService;

import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping
    @ApiOperation(value = "", nickname = "getAllEvents")
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(eventService.findAll());
    }

    @GetMapping("/{idEvent}")
    @ApiOperation(value = "", nickname = "getEventById")
    public ResponseEntity<Event> getEventById(@PathVariable Integer idEvent) {
        return ResponseEntity.ok(eventService.findById(idEvent));
    }

    @PostMapping
    @ApiOperation(value = "", nickname = "saveEvent")
    public ResponseEntity<Event> saveEvent(@RequestBody Event event) {
        return ResponseEntity.status(HttpStatus.CREATED).body(eventService.save(event));
    }

    @PutMapping
    @ApiOperation(value = "", nickname = "updateEvent")
    public ResponseEntity<Event> updateEvent(@RequestBody Event event) {
        return ResponseEntity.ok(eventService.update(event));
    }

    @DeleteMapping("/{idEvent}")
    @ApiOperation(value = "", nickname = "deleteEventById")
    public void deleteEventById(@PathVariable Integer idEvent) {
        eventService.deleteById(idEvent);
    }

    @GetMapping("/all-by-user-id/{idUser}")
    @ApiOperation(value = "", nickname = "getEventsByUserId")
    public ResponseEntity<List<Event>> getAllEventsByUserId(@PathVariable Integer idUser) {
        return ResponseEntity.ok(eventService.findAllEventsByUserId(idUser));
    }

    @PostMapping("/create-event/{idUser}")
    @ApiOperation(value = "", nickname = "createEventByUserId")
    public ResponseEntity<Event> createEventByUserId(@RequestBody Event event, @PathVariable Integer idUser) {
        return ResponseEntity.status(HttpStatus.CREATED).body(eventService.createEventByUserId(event, idUser));
    }
}

