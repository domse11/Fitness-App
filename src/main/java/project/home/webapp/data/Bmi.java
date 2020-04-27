package project.home.webapp.data;

import java.io.Serializable;



public class Bmi implements Serializable{
    
   private double groesse;
       
    private double gewicht;
   
   public Bmi(){       
   }

    public Bmi(double groesse, double gewicht) {
        this.groesse = groesse;
        this.gewicht = gewicht;
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
    
    public double getBmi() {
        return gewicht / (groesse * groesse);
    }


    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.groesse) ^ (Double.doubleToLongBits(this.groesse) >>> 32));
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.gewicht) ^ (Double.doubleToLongBits(this.gewicht) >>> 32));
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
        if (Double.doubleToLongBits(this.groesse) != Double.doubleToLongBits(other.groesse)) {
            return false;
        }
        if (Double.doubleToLongBits(this.gewicht) != Double.doubleToLongBits(other.gewicht)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Bmi{" + "groesse=" + groesse + ", gewicht=" + gewicht + '}';
    }

 
   
    }