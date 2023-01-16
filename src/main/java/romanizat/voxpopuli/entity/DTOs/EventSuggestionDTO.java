package romanizat.voxpopuli.entity.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import romanizat.voxpopuli.entity.Event;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventSuggestionDTO {
    private Integer id;
    private Event event;
    private Integer position;
    private String title;
    private String url;
    private Integer userId;
    private List<VoteDTO> votes;
}
