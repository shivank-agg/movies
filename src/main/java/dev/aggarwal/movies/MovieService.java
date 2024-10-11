package dev.aggarwal.movies;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> AllMovies() {
        return movieRepository.findAll();
    }

    public void CreateMovie(Movie movie) {
        movieRepository.save(movie);
    }

    public void addMovies(List<Movie> movies) {
        movieRepository.saveAll(movies);
    }
    public Optional<Movie> getMovieById(ObjectId id) {
        return Optional.ofNullable((movieRepository.findById(id).orElse(null)));
    }

    public Optional<Movie> getMovieByImdbId(String id) {
        return Optional.ofNullable(movieRepository.findMovieByImdbId(id).orElse(null));
    }


}
