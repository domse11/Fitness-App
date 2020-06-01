package project.home.webapp.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bmi {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;

    private double groesse;

    private double gewicht;
    private double bmi;

    public Bmi() {
    }

    public Bmi(double groesse, double gewicht) {
        this.groesse = groesse;
        this.gewicht = gewicht;
        getBmi();
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.ID) ^ (Double.doubleToLongBits(this.ID) >>> 32));
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
        if (Double.doubleToLongBits(this.ID) != Double.doubleToLongBits(other.ID)) {
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
        return "Bmi{" + "id=" + ID + ", groesse=" + groesse + ", gewicht=" + gewicht + ", bmi=" + bmi + '}';
    }

}
