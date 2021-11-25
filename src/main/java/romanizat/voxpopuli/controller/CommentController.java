package romanizat.voxpopuli.controller;

import java.util.List;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import romanizat.voxpopuli.entity.*;
import romanizat.voxpopuli.service.*;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments() {
        return ResponseEntity.ok(commentService.findAll());
    }

    @GetMapping("/{idComment}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Integer idComment) {
        return ResponseEntity.ok(commentService.findById(idComment));
    }

    @PostMapping
    public ResponseEntity<Comment> saveComment(@RequestBody Comment comment) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.save(comment));
    }

    @PutMapping
    public ResponseEntity<Comment> updateComment(@RequestBody Comment comment) {
        return ResponseEntity.ok(commentService.update(comment));
    }

    @DeleteMapping("/{idComment}")
    public void deleteCommentById(@PathVariable Integer idComment) {
        commentService.deleteById(idComment);
    }

}

