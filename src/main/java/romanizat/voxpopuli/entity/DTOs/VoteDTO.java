package romanizat.voxpopuli.entity.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteDTO {
    private Integer id;
    private Integer eventSuggestionId;
    private Integer userId;
    private Boolean upvote;
}
