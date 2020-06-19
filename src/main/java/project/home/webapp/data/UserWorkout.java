package project.home.webapp.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Objects;

@Entity
public class UserWorkout {
	enum WorkoutType {
		CHESTWORKOUT,
		BICEPS,
		TRICEPS,
		BELLY,
		LEGS,
		BACK,
		CARDIO,
		UNKNOWN
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String title;
	private WorkoutType workoutType;
	private Instant start;
	private Instant end;
	@Size(min = 0, max = 1024, message = "Bitte geben Sie eine Beschreibung mit mindestens fünf und maximal 1k Zeichen ein.")
	private String note;
	private String color;

	public UserWorkout() {
	}

	public UserWorkout(int id, String title, WorkoutType workoutType, Instant start, Instant end, String note, String color) {
		this.id = id;
		this.workoutType = workoutType;
		this.title = title;
		this.start = start;
		this.end = end;
		this.note = note;
		this.color = color;
	}

	public int getId() {
		return id;
	}

	public void setId(int WorkoutID) {
		this.id = WorkoutID;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public WorkoutType getWorkoutType() {
		return this.workoutType;
	}

	public void setWorkoutType(WorkoutType workoutType) {
		this.workoutType = workoutType;
	}

	public Instant getStart() {
		return this.start;
	}

	public void setStart(Instant start) {
		this.start = start;
	}

	public Instant getEnd() {
		return this.end;
	}

	public void setEnd(Instant end) {
		this.end = end;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "UserWorkout{" + "WorkoutID=" + id + "Title=" + title + ", Start=" + start + ", End=" + end + ", Notiz=" + note + '}';
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 97 * hash + this.id;
		hash = 97 * hash + Objects.hashCode(this.title);
		hash = 97 * hash + Objects.hashCode(this.start);
		hash = 97 * hash + Objects.hashCode(this.end);
		hash = 97 * hash + Objects.hashCode(this.note);
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
		if (this.id != other.id) {
			return false;
		}
		if (!Objects.equals(this.title, other.title)) {
			return false;
		}
		if (!Objects.equals(this.note, other.note)) {
			return false;
		}
		if (!Objects.equals(this.start, other.start)) {
			return false;
		}
		if (!Objects.equals(this.end, other.end)) {
			return false;
		}

		return true;
	}
}

