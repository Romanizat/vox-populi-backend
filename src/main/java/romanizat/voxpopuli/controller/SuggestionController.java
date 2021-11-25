package romanizat.voxpopuli.controller;

import java.util.List;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import romanizat.voxpopuli.entity.*;
import romanizat.voxpopuli.service.*;

@RestController
@RequestMapping("/suggestions")
@RequiredArgsConstructor
public class SuggestionController {
    private final SuggestionService suggestionService;

    @GetMapping
    public ResponseEntity<List<Suggestion>> getAllSuggestions() {
        return ResponseEntity.ok(suggestionService.findAll());
    }

    @GetMapping("/{idSuggestion}")
    public ResponseEntity<Suggestion> getSuggestionById(@PathVariable Integer idSuggestion) {
        return ResponseEntity.ok(suggestionService.findById(idSuggestion));
    }

    @PostMapping
    public ResponseEntity<Suggestion> saveSuggestion(@RequestBody Suggestion suggestion) {
        return ResponseEntity.status(HttpStatus.CREATED).body(suggestionService.save(suggestion));
    }

    @PutMapping
    public ResponseEntity<Suggestion> updateSuggestion(@RequestBody Suggestion suggestion) {
        return ResponseEntity.ok(suggestionService.update(suggestion));
    }

    @DeleteMapping("/{idSuggestion}")
    public void deleteSuggestionById(@PathVariable Integer idSuggestion) {
        suggestionService.deleteById(idSuggestion);
    }

}

