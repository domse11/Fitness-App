package project.home.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.home.webapp.data.Bmi;

public interface BmiRepository extends JpaRepository<Bmi, Integer>{
    
    
    
}
