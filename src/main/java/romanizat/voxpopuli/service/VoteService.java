package romanizat.voxpopuli.service;

import romanizat.voxpopuli.entity.DTOs.VoteDTO;
import romanizat.voxpopuli.entity.Vote;

import java.util.List;

public interface VoteService {
    VoteDTO save(VoteDTO voteDTO);

    VoteDTO update(VoteDTO voteDTO);

    void delete(Integer idVote);

    Vote getVoteById(Integer idVote);

    List<VoteDTO> getAllVotesForEventSuggestion(Integer idEventSuggestion);

    List<VoteDTO> getAllVotesByUserId(Integer idUser);
}
