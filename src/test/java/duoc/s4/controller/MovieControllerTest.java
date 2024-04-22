package duoc.s4.controller;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import duoc.s4.model.Movie;
import duoc.s4.service.MovieService;
import java.util.List;

@WebMvcTest(MovieController.class)
public class MovieControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieServiceMock;

    @Test
    public void testGetAllMovies() throws Exception {
        Movie movie1 = new Movie();
        movie1.setTitle("Titanic");
        Movie movie2 = new Movie();
        movie2.setTitle("Spiderman");
        List<Movie> movies = List.of(movie1, movie2);
        when(movieServiceMock.getAllMovies()).thenReturn(movies);

        mockMvc.perform(MockMvcRequestBuilders.get("/peliculas"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Titanic"))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].title").value("Spiderman"));
    }
}
