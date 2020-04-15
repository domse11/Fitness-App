package project.home.webapp.data;

import java.io.Serializable;

public class Bmi implements Serializable{
    
   private int groesse;
   private int gewicht;
   private int alter;
   
   public Bmi(){       
   }

    public Bmi(int groesse, int gewicht, int alter) {
        this.groesse = groesse;
        this.gewicht = gewicht;
        this.alter = alter;
    }

    public int getGroesse() {
        return groesse;
    }

    public void setGroesse(int groesse) {
        this.groesse = groesse;
    }

    public int getGewicht() {
        return gewicht;
    }

    public void setGewicht(int gewicht) {
        this.gewicht = gewicht;
    }

    public int getAlter() {
        return alter;
    }

    public void setAlter(int alter) {
        this.alter = alter;
    }
    
      public int getBmi() {
         return gewicht / (groesse * groesse);
          }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + this.groesse;
        hash = 73 * hash + this.gewicht;
        hash = 73 * hash + this.alter;
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
        if (this.groesse != other.groesse) {
            return false;
        }
        if (this.gewicht != other.gewicht) {
            return false;
        }
        if (this.alter != other.alter) {
            return false;
        }
        return true;
        
        
    }

    @Override
    public String toString() {
        return "Bmi{" + "groesse=" + groesse + ", gewicht=" + gewicht + ", alter=" + alter + '}';
    }
   
}
