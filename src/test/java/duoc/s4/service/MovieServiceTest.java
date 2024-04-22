package duoc.s4.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;

import duoc.s4.model.Movie;
import duoc.s4.repository.MovieRepository;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

    @InjectMocks
    private MovieService movieService;

    @Mock
    private MovieRepository movieRepositoryMock;

    @Test
    public void saveMovieServiceTest() {
        
        Movie movie = new Movie();
        movie.setTitle("Titanic");

        when(movieRepositoryMock.save(any())).thenReturn(movie);

        Movie savedMovie = movieService.createMovie(movie);

        assertEquals("Titanic", savedMovie.getTitle());
    }
    
}
