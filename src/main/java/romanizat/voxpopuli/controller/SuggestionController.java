package romanizat.voxpopuli.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import romanizat.voxpopuli.entity.Suggestion;
import romanizat.voxpopuli.service.SuggestionService;

import java.util.List;

@RestController
@RequestMapping("/suggestions")
@RequiredArgsConstructor
public class SuggestionController {
    private final SuggestionService suggestionService;

    @GetMapping
    @ApiOperation(value = "", nickname = "getAllSuggestions")
    public ResponseEntity<List<Suggestion>> getAllSuggestions() {
        return ResponseEntity.ok(suggestionService.findAll());
    }

    @GetMapping("/{idSuggestion}")
    @ApiOperation(value = "", nickname = "getSuggestionById")
    public ResponseEntity<Suggestion> getSuggestionById(@PathVariable Integer idSuggestion) {
        return ResponseEntity.ok(suggestionService.findById(idSuggestion));
    }

    @PostMapping
    @ApiOperation(value = "", nickname = "saveSuggestion")
    public ResponseEntity<Suggestion> saveSuggestion(@RequestBody Suggestion suggestion) {
        return ResponseEntity.status(HttpStatus.CREATED).body(suggestionService.save(suggestion));
    }

    @PutMapping
    @ApiOperation(value = "", nickname = "updateSuggestion")
    public ResponseEntity<Suggestion> updateSuggestion(@RequestBody Suggestion suggestion) {
        return ResponseEntity.ok(suggestionService.update(suggestion));
    }

    @DeleteMapping("/{idSuggestion}")
    @ApiOperation(value = "", nickname = "deleteSuggestionById")
    public void deleteSuggestionById(@PathVariable Integer idSuggestion) {
        suggestionService.deleteById(idSuggestion);
    }

}

