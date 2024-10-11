package dev.aggarwal.movies;

import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping()
    public ResponseEntity<List<Movie>> allMovies(){
        return new ResponseEntity<List<Movie>>(movieService.AllMovies(), HttpStatus.OK);
    }
//
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Movie>> getMovieById(@PathVariable() ObjectId id){
        return new ResponseEntity<Optional<Movie>>(movieService.getMovieById(id), HttpStatus.OK);
    }
//
    @GetMapping("/imdb/{imdbid}")
    public ResponseEntity<Optional<Movie>> getMoviebyImdbId(@PathVariable("imdbid") String id){
        return new ResponseEntity<Optional<Movie>>(movieService.getMovieByImdbId(id), HttpStatus.OK);
    }



//    @PostMapping("/abcd")
//    public void addMovie(@RequestBody Movie movie) {
//        movieService.CreateMovie(movie);
//    }


}
