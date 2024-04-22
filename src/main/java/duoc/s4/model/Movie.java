package duoc.s4.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
@Entity
@Table(name = "movies")
public class Movie extends RepresentationModel<Movie> {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String director;
    private int year;
    private String genre;
    private String synopsis;
}
