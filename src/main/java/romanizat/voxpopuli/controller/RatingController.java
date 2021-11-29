package romanizat.voxpopuli.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import romanizat.voxpopuli.entity.Rating;
import romanizat.voxpopuli.service.RatingService;

import java.util.List;

@RestController
@RequestMapping("/ratings")
@RequiredArgsConstructor
public class RatingController {
    private final RatingService ratingService;

    @GetMapping
    @ApiOperation(value = "", nickname = "getAllRatings")
    public ResponseEntity<List<Rating>> getAllRatings() {
        return ResponseEntity.ok(ratingService.findAll());
    }

    @GetMapping("/{idRating}")
    @ApiOperation(value = "", nickname = "getRatingById")
    public ResponseEntity<Rating> getRatingById(@PathVariable Integer idRating) {
        return ResponseEntity.ok(ratingService.findById(idRating));
    }

    @PostMapping
    @ApiOperation(value = "", nickname = "saveRating")
    public ResponseEntity<Rating> saveRating(@RequestBody Rating rating) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.save(rating));
    }

    @PutMapping
    @ApiOperation(value = "", nickname = "updateRating")
    public ResponseEntity<Rating> updateRating(@RequestBody Rating rating) {
        return ResponseEntity.ok(ratingService.update(rating));
    }

    @DeleteMapping("/{idRating}")
    @ApiOperation(value = "", nickname = "deleteRatingById")
    public void deleteRatingById(@PathVariable Integer idRating) {
        ratingService.deleteById(idRating);
    }

}

