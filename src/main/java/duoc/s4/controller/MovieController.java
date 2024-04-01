package duoc.s4.controller;

import duoc.s4.model.Movie;
import duoc.s4.service.MovieService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/peliculas")
public class MovieController {


    @Autowired
    private MovieService movieService;

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
}
