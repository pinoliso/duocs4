package duoc.s4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import duoc.s4.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    
} 
