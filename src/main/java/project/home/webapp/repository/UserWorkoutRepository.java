package project.home.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.home.webapp.data.UserWorkout;

import java.time.Instant;
import java.util.List;

public interface UserWorkoutRepository extends JpaRepository<UserWorkout, Integer>{

    @Query("select u from UserWorkout u where u.start >= :start and u.end <= :end")
    List<UserWorkout> findByStartAfterAndEndBefore(Instant start, Instant end);
    
}
