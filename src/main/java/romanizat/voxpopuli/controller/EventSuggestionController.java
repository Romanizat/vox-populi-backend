package romanizat.voxpopuli.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import romanizat.voxpopuli.entity.DTOs.EventSuggestionDTO;
import romanizat.voxpopuli.service.EventSuggestionService;

import java.util.List;

@RestController
@RequestMapping("/event-suggestions")
@RequiredArgsConstructor
public class EventSuggestionController {
    private final EventSuggestionService eventSuggestionService;

    @GetMapping
    @ApiOperation(value = "", nickname = "getAllEventSuggestions")
    public ResponseEntity<List<EventSuggestionDTO>> getAllEventSuggestions() {
        return ResponseEntity.ok(eventSuggestionService.findAll());
    }

    @GetMapping("/{idEventSuggestion}")
    @ApiOperation(value = "", nickname = "getEventSuggestionById")
    public ResponseEntity<EventSuggestionDTO> getEventSuggestionById(@PathVariable Integer idEventSuggestion) {
        return ResponseEntity.ok(eventSuggestionService.getEventSuggestionDTOById(idEventSuggestion));
    }

    @PostMapping
    @ApiOperation(value = "", nickname = "saveEventSuggestion")
    public ResponseEntity<EventSuggestionDTO> saveEventSuggestion(@RequestBody EventSuggestionDTO eventSuggestion) {
        return ResponseEntity.status(HttpStatus.CREATED).body(eventSuggestionService.save(eventSuggestion));
    }

    @PutMapping
    @ApiOperation(value = "", nickname = "updateEventSuggestion")
    public ResponseEntity<EventSuggestionDTO> updateEventSuggestion(@RequestBody EventSuggestionDTO eventSuggestion) {
        return ResponseEntity.ok(eventSuggestionService.update(eventSuggestion));
    }

    @DeleteMapping("/{idEventSuggestion}")
    @ApiOperation(value = "", nickname = "deleteEventSuggestionById")
    public void deleteEventSuggestionById(@PathVariable Integer idEventSuggestion) {
        eventSuggestionService.deleteById(idEventSuggestion);
    }

    @GetMapping("get-all-by-event/{idEvent}")
    @ApiOperation(value = "", nickname = "getAllEventSuggestionsForEvent")
    public ResponseEntity<List<EventSuggestionDTO>> getAllEventSuggestionsForEvent(@PathVariable Integer idEvent) {
        return ResponseEntity.ok(eventSuggestionService.getAllEventSuggestionsForEvent(idEvent));
    }

    @PutMapping("update-positions/{oldPosition}/{newPosition}/{eventId}")
    @ApiOperation(value = "", nickname = "updateEventSuggestionPositionInEvent")
    public void updateEventSuggestionPosition(@PathVariable Integer oldPosition, @PathVariable Integer newPosition, @PathVariable Integer eventId) {
        eventSuggestionService.changeEventSuggestionOrderInEvent(oldPosition, newPosition, eventId);
    }

    @GetMapping("number-of-event-suggestions-by-user/{idUser}")
    @ApiOperation(value = "", nickname = "getNumberOfEventSuggestionsByUserId")
    public ResponseEntity<Integer> getNumberOfEventSuggestionsByUserId(@PathVariable Integer idUser) {
        return ResponseEntity.ok(eventSuggestionService.getNumberOfEventSuggestionsByUserId(idUser));
    }
}

