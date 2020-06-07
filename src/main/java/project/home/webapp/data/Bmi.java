package project.home.webapp.data;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Bmi {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    /*@NotNull
    @Min(140)
    @Max(220)*/
    
    //@NotEmpty(message="Bitte eine Größe eingeben")
    private double groesse; 

    /*@NotNull
    @Min(40)
    @Max(120)*/
    //@NotEmpty(message="Bitte ein Gewicht eingeben")
    private double gewicht;
        
    private double bmi;
    
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDateTime tageszeit;

    public Bmi() {
    }

    public Bmi(int id, double groesse, double gewicht) {
        this.id = id;
        this.groesse = groesse;
        this.gewicht = gewicht;
        setBmi(bmi);
        getBmi();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getGroesse() {
        return groesse;
    }

    public void setGroesse(double groesse) {
        this.groesse = groesse;
    }

    public double getGewicht() {
        return gewicht;
    }

    public void setGewicht(double gewicht) {
        this.gewicht = gewicht;
    }

    public final double getBmi() {
        bmi = gewicht / (groesse * groesse) * 10000;
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.id) ^ (Double.doubleToLongBits(this.id) >>> 32));
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.groesse) ^ (Double.doubleToLongBits(this.groesse) >>> 32));
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.gewicht) ^ (Double.doubleToLongBits(this.gewicht) >>> 32));
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.bmi) ^ (Double.doubleToLongBits(this.bmi) >>> 32));
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
        final Bmi other = (Bmi) obj;
        if (Double.doubleToLongBits(this.id) != Double.doubleToLongBits(other.id)) {
            return false;
        }
        if (Double.doubleToLongBits(this.groesse) != Double.doubleToLongBits(other.groesse)) {
            return false;
        }
        if (Double.doubleToLongBits(this.gewicht) != Double.doubleToLongBits(other.gewicht)) {
            return false;
        }
        if (Double.doubleToLongBits(this.bmi) != Double.doubleToLongBits(other.bmi)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Bmi{" + "id=" + id + ", groesse=" + groesse + ", gewicht=" + gewicht + ", bmi=" + bmi + '}';
    }

    public LocalDateTime getTageszeit() {
        return tageszeit;
    }

    public void setTageszeit() {
        this.tageszeit = LocalDateTime.now();
    }

}
