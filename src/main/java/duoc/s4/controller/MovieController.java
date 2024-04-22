package duoc.s4.controller;

import duoc.s4.model.Movie;
import duoc.s4.service.MovieService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import jakarta.annotation.PostConstruct;

import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/peliculas")
public class MovieController {

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
        System.out.println("Peticion Listado de Peliculas");
        return movieService.getAllMovies();
    }
    
    @GetMapping("/{id}")
    public Optional<Movie> getMovieById(@PathVariable Long id) {
        System.out.println("Peticion de Pelicula " + id);
        return movieService.getMovieById(id);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createMovie(@RequestBody Movie movie) {
        System.out.println("Peticion de crear pelicula ");
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
        System.out.println("Peticion de actualizar pelicula ");
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
    public ResponseEntity<Map<String, Object>> deleteStudent(@PathVariable Long id){
        System.out.println("Peticion de eliminar pelicula ");
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
