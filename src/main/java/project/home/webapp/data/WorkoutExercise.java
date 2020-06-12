package project.home.webapp.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class WorkoutExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ExerciseID;
        
    /*@Min(140)
    @Max(220) */
    private int WorkoutID; 

    //@NotNull
    /*@Min(40)
    @Max(120)*/
    // @NotEmpty(message="Bitte ein Gewicht eingeben")
     //@NotNull(message="Bitte ein Gewicht eingeben")
    private int Wiederholungen;
        
    private int Uebungsgewicht;

    public WorkoutExercise(int ExerciseID, int WorkoutID, int Wiederholungen, int Uebungsgewicht) {
        this.ExerciseID = ExerciseID;
        this.WorkoutID = WorkoutID;
        this.Wiederholungen = Wiederholungen;
        this.Uebungsgewicht = Uebungsgewicht;
    }
    
    
    public WorkoutExercise() {
    }

    public int getExerciseID() {
        return ExerciseID;
    }

    public void setExerciseID(int ExerciseID) {
        this.ExerciseID = ExerciseID;
    }

    public int getWorkoutID() {
        return WorkoutID;
    }

    public void setWorkoutID(int WorkoutID) {
        this.WorkoutID = WorkoutID;
    }

    public int getWiederholungen() {
        return Wiederholungen;
    }

    public void setWiederholungen(int Wiederholungen) {
        this.Wiederholungen = Wiederholungen;
    }

    public int getUebungsgewicht() {
        return Uebungsgewicht;
    }

    public void setUebungsgewicht(int Uebungsgewicht) {
        this.Uebungsgewicht = Uebungsgewicht;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + this.ExerciseID;
        hash = 31 * hash + this.WorkoutID;
        hash = 31 * hash + this.Wiederholungen;
        hash = 31 * hash + this.Uebungsgewicht;
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
        final WorkoutExercise other = (WorkoutExercise) obj;
        if (this.ExerciseID != other.ExerciseID) {
            return false;
        }
        if (this.WorkoutID != other.WorkoutID) {
            return false;
        }
        if (this.Wiederholungen != other.Wiederholungen) {
            return false;
        }
        if (this.Uebungsgewicht != other.Uebungsgewicht) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "WorkoutExercise{" + "ExerciseID=" + ExerciseID + ", WorkoutID=" + WorkoutID + ", Wiederholungen=" + Wiederholungen + ", Uebungsgewicht=" + Uebungsgewicht + '}';
    }

    

}
