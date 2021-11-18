package romanizat.voxpopuli.controller;

import java.util.List;
import lombok.*;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import romanizat.voxpopuli.entity.*;
import romanizat.voxpopuli.service.*;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
	private final EventService eventService;

	@GetMapping
	public ResponseEntity<List<Event>> getAllEvents() {
		return ResponseEntity.ok(eventService.findAll());
	}

	@GetMapping("/{idEvent}")
	public ResponseEntity<Event> getEventById(@PathVariable Integer idEvent) {
		return ResponseEntity.ok(eventService.findById(idEvent));
	}

	@PostMapping
	public ResponseEntity<Event> saveEvent(@RequestBody Event event) {
		return ResponseEntity.status(HttpStatus.CREATED).body(eventService.save(event));
	}

	@PutMapping
	public ResponseEntity<Event> updateEvent(@RequestBody Event event) {
		return ResponseEntity.ok(eventService.update(event));
	}

	@DeleteMapping("/{idEvent}")
	public void deleteEventById(@PathVariable Integer idEvent) {
		eventService.deleteById(idEvent);
	}

}

