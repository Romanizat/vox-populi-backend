package romanizat.voxpopuli.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import romanizat.voxpopuli.entity.DTOs.VoteDTO;
import romanizat.voxpopuli.entity.Vote;
import romanizat.voxpopuli.repository.VoteRepository;
import romanizat.voxpopuli.service.EventSuggestionService;
import romanizat.voxpopuli.service.UserService;
import romanizat.voxpopuli.service.VoteService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;
    private final EventSuggestionService eventSuggestionService;
    private final UserService userService;

    @Override
    public VoteDTO save(VoteDTO voteDTO) {
        return voteRepository.save(mapToEntity(voteDTO)).mapToDTO();
    }

    @Override
    public VoteDTO update(VoteDTO voteDTO) {
        return voteRepository.save(mapToEntity(voteDTO)).mapToDTO();
    }

    @Override
    public void delete(Integer idVote) {
        voteRepository.deleteById(idVote);
    }

    @Override
    public Vote getVoteById(Integer idVote) {
        return voteRepository.findById(idVote)
                .orElseThrow(() -> new NoSuchElementException("VoteService.notFound"));
    }

    @Override
    public List<VoteDTO> getAllVotesForEventSuggestion(Integer idEventSuggestion) {
        return eventSuggestionService.findById(idEventSuggestion).getVotes()
                .stream()
                .map(Vote::mapToDTO)
                .collect(Collectors.toList());
    }

    private Vote mapToEntity(VoteDTO voteDTO) {
        Vote vote = new Vote();
        vote.setId(voteDTO.getId());
        vote.setEventSuggestion(eventSuggestionService.findById(voteDTO.getEventSuggestionId()));
        vote.setUser(userService.findById(voteDTO.getUserId()));
        vote.setUpvote(voteDTO.getUpvote());
        return vote;
    }
}
