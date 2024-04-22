package duoc.s4.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import duoc.s4.model.Movie;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Test
    @DisplayName("Save movie test")
    public void saveMovieRepositoryTest() {
        Movie movie = new Movie();
        movie.setTitle("Titanic");
        movie.setDirector("James Cameron");
        movie.setYear(1997);
        movie.setGenre("Drama");
        movie.setSynopsis("Sinking of the RMS Titanic");

        Movie savedMovie = movieRepository.save(movie);

        assertNotNull(savedMovie);
        assertEquals(movie.getTitle(), savedMovie.getTitle());
        assertEquals(movie.getDirector(), savedMovie.getDirector());
        assertEquals(movie.getYear(), savedMovie.getYear());
        assertEquals(movie.getGenre(), savedMovie.getGenre());
        assertEquals(movie.getSynopsis(), savedMovie.getSynopsis());
    }
    
}
