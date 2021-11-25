package romanizat.voxpopuli.controller;

import java.util.List;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import romanizat.voxpopuli.entity.*;
import romanizat.voxpopuli.service.*;

@RestController
@RequestMapping("/event-suggestions")
@RequiredArgsConstructor
public class EventSuggestionController {
    private final EventSuggestionService eventSuggestionService;

    @GetMapping
    public ResponseEntity<List<EventSuggestion>> getAllEventSuggestions() {
        return ResponseEntity.ok(eventSuggestionService.findAll());
    }

    @GetMapping("/{idEventSuggestion}")
    public ResponseEntity<EventSuggestion> getEventSuggestionById(@PathVariable Integer idEventSuggestion) {
        return ResponseEntity.ok(eventSuggestionService.findById(idEventSuggestion));
    }

    @PostMapping
    public ResponseEntity<EventSuggestion> saveEventSuggestion(@RequestBody EventSuggestion eventSuggestion) {
        return ResponseEntity.status(HttpStatus.CREATED).body(eventSuggestionService.save(eventSuggestion));
    }

    @PutMapping
    public ResponseEntity<EventSuggestion> updateEventSuggestion(@RequestBody EventSuggestion eventSuggestion) {
        return ResponseEntity.ok(eventSuggestionService.update(eventSuggestion));
    }

    @DeleteMapping("/{idEventSuggestion}")
    public void deleteEventSuggestionById(@PathVariable Integer idEventSuggestion) {
        eventSuggestionService.deleteById(idEventSuggestion);
    }

}

