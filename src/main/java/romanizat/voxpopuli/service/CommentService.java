package romanizat.voxpopuli.service;

import java.util.Collection;
import java.util.List;
import romanizat.voxpopuli.entity.*;

public interface CommentService {

	List<Comment> findAll();

	Comment save(Comment comment);

	Comment update(Comment comment);

	Comment findById(Integer idComment);

	void deleteById(Integer idComment);

}