package duoc.s4.service;

import duoc.s4.model.Movie;
import duoc.s4.repository.MovieRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> getMovieById(Long id) {
        return movieRepository.findById(id);
    }

    public void createMovie(Movie movie) {
        movieRepository.save(movie);
    }

    public Movie updateMovie(Long id, Movie movie){
        if(movieRepository.existsById(id)){
            movie.setId(id);
            return movieRepository.save(movie);
        }else {
            return null;
        }
    }

    public void deleteMovie(Long id){
        movieRepository.deleteById(id);
    }
}
