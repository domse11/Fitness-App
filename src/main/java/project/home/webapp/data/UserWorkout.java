package project.home.webapp.data;

import java.sql.Time;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import java.util.Objects;

@Entity
public class UserWorkout {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int WorkoutID;
   
    //@DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date WorkoutDatum; 

    private Time WorkoutStart;
        
    private Time WorkoutEnde;
    
    @Size(min = 0, max = 1024, message = "Bitte geben Sie eine Beschreibung mit mindestens fünf und maximal 1k Zeichen ein.")
    private String Notiz;

    public UserWorkout() {
    }

    public int getWorkoutID() {
        return WorkoutID;
    }

    public void setWorkoutID(int WorkoutID) {
        this.WorkoutID = WorkoutID;
    }

    public Date getWorkoutDatum() {
        return WorkoutDatum;
    }

    public void setWorkoutDatum(Date WorkoutDatum) {
        this.WorkoutDatum = WorkoutDatum;
    }

    public Time getWorkoutStart() {
        return WorkoutStart;
    }

    public void setWorkoutStart(Time WorkoutStart) {
        this.WorkoutStart = WorkoutStart;
    }

    public Time getWorkoutEnde() {
        return WorkoutEnde;
    }

    public void setWorkoutEnde(Time WorkoutEnde) {
        this.WorkoutEnde = WorkoutEnde;
    }

    public String getNotiz() {
        return Notiz;
    }

    public void setNotiz(String Notiz) {
        this.Notiz = Notiz;
    }

    @Override
    public String toString() {
        return "UserWorkout{" + "WorkoutID=" + WorkoutID + ", WorkoutDatum=" + WorkoutDatum + ", WorkoutStart=" + WorkoutStart + ", WorkoutEnde=" + WorkoutEnde + ", Notiz=" + Notiz + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.WorkoutID;
        hash = 97 * hash + Objects.hashCode(this.WorkoutDatum);
        hash = 97 * hash + Objects.hashCode(this.WorkoutStart);
        hash = 97 * hash + Objects.hashCode(this.WorkoutEnde);
        hash = 97 * hash + Objects.hashCode(this.Notiz);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserWorkout other = (UserWorkout) obj;
        if (this.WorkoutID != other.WorkoutID) {
            return false;
        }
        if (!Objects.equals(this.Notiz, other.Notiz)) {
            return false;
        }
        if (!Objects.equals(this.WorkoutDatum, other.WorkoutDatum)) {
            return false;
        }
        if (!Objects.equals(this.WorkoutStart, other.WorkoutStart)) {
            return false;
        }
        if (!Objects.equals(this.WorkoutEnde, other.WorkoutEnde)) {
            return false;
        }
        return true;
    }
    
    
}

    