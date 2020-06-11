package project.home.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.home.webapp.data.WorkoutExercise;


public interface WorkoutExerciseRepository extends JpaRepository<WorkoutExercise, Integer>{
    
    
    
}
