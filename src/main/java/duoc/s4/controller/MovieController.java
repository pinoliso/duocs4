package duoc.s4.controller;

import duoc.s4.model.Movie;
import duoc.s4.service.MovieService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import jakarta.annotation.PostConstruct;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/peliculas")
public class MovieController {

    private static final Logger log = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private MovieService movieService;

    @PostConstruct
    public void insertTestData() {
        for(int i = 0; i < 3; i++) {
            Movie movie = new Movie();
            movie.setTitle("Título de la película " + i);
            movie.setDirector("Director " + i);
            movie.setYear(2000 + i);
            movie.setGenre("Acción");
            movie.setSynopsis("Lorem ipsum");
            movieService.createMovie(movie);
        }
    }

    @GetMapping
    public List<Movie> getAllMovies() {
        log.info("Peticion Listado de Peliculas");
        return movieService.getAllMovies();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        log.info("Peticion de Pelicula " + id);

        Optional<Movie> optionalMovie = movieService.getMovieById(id);
        if (optionalMovie.isPresent()) {
            Movie movie = optionalMovie.get();
            Link selfLink = WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).getMovieById(id)).withSelfRel();
            movie.add(selfLink);
            Link deleteLink = WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).deleteMovie(id)).withRel("delete");
            movie.add(deleteLink);
            return new ResponseEntity<>(movie, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createMovie(@RequestBody Movie movie) {
        log.info("Peticion de crear pelicula ");
        Map<String, Object> response = new HashMap<>();
        try {
            movieService.createMovie(movie);
            response.put("message", "Pelicula creada satisfactoriamente");
            response.put("data", movie);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "Error error al crear la pelicula: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateMovie(@PathVariable Long id, @RequestBody Movie movie) {
        log.info("Peticion de actualizar pelicula ");
        Map<String, Object> response = new HashMap<>();
        try {
            movieService.updateMovie(id, movie);
            response.put("message", "Pelicula actualizada satisfactoriamente");
            response.put("data", movie);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "Error error al actualizar la pelicula: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteMovie(@PathVariable Long id){
        log.info("Peticion de eliminar pelicula ");
        Map<String, Object> response = new HashMap<>();
        try {
            movieService.deleteMovie(id);
            response.put("message", "Pelicula eliminada satisfactoriamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "Error error al eliminar la pelicula: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
