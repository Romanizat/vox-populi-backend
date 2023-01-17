package romanizat.voxpopuli.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import romanizat.voxpopuli.entity.DTOs.VoteDTO;
import romanizat.voxpopuli.service.VoteService;

import java.util.List;

@RestController
@RequestMapping("/votes")
@RequiredArgsConstructor
public class VoteController {
    private final VoteService voteService;

    @GetMapping("/get-by-event-suggestion/{idEventSuggestion}")
    @ApiOperation(value = "", nickname = "getAllVotesForEventSuggestion")
    public ResponseEntity<List<VoteDTO>> getAllVotesForEventSuggestion(@PathVariable Integer idEventSuggestion) {
        return ResponseEntity.ok(voteService.getAllVotesForEventSuggestion(idEventSuggestion));
    }

    @PostMapping
    @ApiOperation(value = "", nickname = "saveVote")
    public ResponseEntity<VoteDTO> saveVote(@RequestBody VoteDTO voteDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(voteService.save(voteDTO));
    }

    @PutMapping
    @ApiOperation(value = "", nickname = "updateVote")
    public ResponseEntity<VoteDTO> updateVote(@RequestBody VoteDTO voteDTO) {
        return ResponseEntity.ok(voteService.update(voteDTO));
    }

    @DeleteMapping("/{idVote}")
    @ApiOperation(value = "", nickname = "deleteVoteById")
    public void deleteVoteById(@PathVariable Integer idVote) {
        voteService.delete(idVote);
    }
}

