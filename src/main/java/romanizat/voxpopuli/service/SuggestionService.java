package romanizat.voxpopuli.service;

import java.util.Collection;
import java.util.List;
import romanizat.voxpopuli.entity.*;

public interface SuggestionService {

	List<Suggestion> findAll();

	Suggestion save(Suggestion suggestion);

	Suggestion update(Suggestion suggestion);

	Suggestion findById(Integer idSuggestion);

	void deleteById(Integer idSuggestion);

}