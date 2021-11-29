package romanizat.voxpopuli.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import romanizat.voxpopuli.entity.Comment;
import romanizat.voxpopuli.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping
    @ApiOperation(value = "", nickname = "getAllComments")
    public ResponseEntity<List<Comment>> getAllComments() {
        return ResponseEntity.ok(commentService.findAll());
    }

    @GetMapping("/{idComment}")
    @ApiOperation(value = "", nickname = "getCommentById")
    public ResponseEntity<Comment> getCommentById(@PathVariable Integer idComment) {
        return ResponseEntity.ok(commentService.findById(idComment));
    }

    @PostMapping
    @ApiOperation(value = "", nickname = "saveComment")
    public ResponseEntity<Comment> saveComment(@RequestBody Comment comment) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.save(comment));
    }

    @PutMapping
    @ApiOperation(value = "", nickname = "updateComment")
    public ResponseEntity<Comment> updateComment(@RequestBody Comment comment) {
        return ResponseEntity.ok(commentService.update(comment));
    }

    @DeleteMapping("/{idComment}")
    @ApiOperation(value = "", nickname = "deleteCommentById")
    public void deleteCommentById(@PathVariable Integer idComment) {
        commentService.deleteById(idComment);
    }

}

