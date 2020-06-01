package project.home.webapp.data;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "exercise")
public class Exercise implements Serializable {

    public Exercise() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Exerciseid;

    @NotNull
    private String ExerciseType;

    public Exercise(Integer Exerciseid, String ExerciseType) {
        this.Exerciseid = Exerciseid;
        this.ExerciseType = ExerciseType;
    }

    public Integer getExerciseid() {
        return Exerciseid;
    }

    public void setExerciseid(Integer Exerciseid) {
        this.Exerciseid = Exerciseid;
    }

    public String getExerciseType() {
        return ExerciseType;
    }

    public void setExerciseType(String ExerciseType) {
        this.ExerciseType = ExerciseType;
    }

    @Override
    public String toString() {
        return "Exercise{" + "Exerciseid=" + Exerciseid + ", ExerciseType=" + ExerciseType + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.Exerciseid);
        hash = 43 * hash + Objects.hashCode(this.ExerciseType);
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
        final Exercise other = (Exercise) obj;
        if (!Objects.equals(this.ExerciseType, other.ExerciseType)) {
            return false;
        }
        if (!Objects.equals(this.Exerciseid, other.Exerciseid)) {
            return false;
        }
        return true;
    }

}
