package project.home.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.home.webapp.data.UserWorkout;


public interface UserWorkoutRepository extends JpaRepository<UserWorkout, Integer>{
    
    
    
}
