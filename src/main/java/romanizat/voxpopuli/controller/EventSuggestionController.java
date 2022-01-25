package romanizat.voxpopuli.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import romanizat.voxpopuli.entity.EventSuggestion;
import romanizat.voxpopuli.service.EventSuggestionService;

import java.util.List;

@RestController
@RequestMapping("/event-suggestions")
@RequiredArgsConstructor
public class EventSuggestionController {
    private final EventSuggestionService eventSuggestionService;

    @GetMapping
    @ApiOperation(value = "", nickname = "getAllEventSuggestions")
    public ResponseEntity<List<EventSuggestion>> getAllEventSuggestions() {
        return ResponseEntity.ok(eventSuggestionService.findAll());
    }

    @GetMapping("/{idEventSuggestion}")
    @ApiOperation(value = "", nickname = "getEventSuggestionById")
    public ResponseEntity<EventSuggestion> getEventSuggestionById(@PathVariable Integer idEventSuggestion) {
        return ResponseEntity.ok(eventSuggestionService.findById(idEventSuggestion));
    }

    @PostMapping
    @ApiOperation(value = "", nickname = "saveEventSuggestion")
    public ResponseEntity<EventSuggestion> saveEventSuggestion(@RequestBody EventSuggestion eventSuggestion) {
        System.out.println(eventSuggestion);
        return ResponseEntity.status(HttpStatus.CREATED).body(eventSuggestionService.save(eventSuggestion));
    }

    @PutMapping
    @ApiOperation(value = "", nickname = "updateEventSuggestion")
    public ResponseEntity<EventSuggestion> updateEventSuggestion(@RequestBody EventSuggestion eventSuggestion) {
        return ResponseEntity.ok(eventSuggestionService.update(eventSuggestion));
    }

    @DeleteMapping("/{idEventSuggestion}")
    @ApiOperation(value = "", nickname = "deleteEventSuggestionById")
    public void deleteEventSuggestionById(@PathVariable Integer idEventSuggestion) {
        eventSuggestionService.deleteById(idEventSuggestion);
    }

    @GetMapping("get-all-by-event/{idEvent}")
    @ApiOperation(value = "", nickname = "getAllEventSuggestionsForEvent")
    public ResponseEntity<List<EventSuggestion>> getAllEventSuggestionsForEvent(@PathVariable Integer idEvent) {
        return ResponseEntity.ok(eventSuggestionService.getAllEventSuggestionsForEvent(idEvent));
    }
}

