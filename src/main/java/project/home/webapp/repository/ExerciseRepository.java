package project.home.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.home.webapp.data.Exercise;

public interface ExerciseRepository extends JpaRepository<Exercise, Integer>{
    
}
