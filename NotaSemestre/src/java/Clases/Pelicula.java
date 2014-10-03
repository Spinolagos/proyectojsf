package Clases;
// Generated 30-sep-2014 16:43:33 by Hibernate Tools 3.6.0


import java.util.HashSet;
import java.util.Set;

/**
 * Pelicula generated by hbm2java
 */
public class Pelicula  implements java.io.Serializable {


     private Integer plId;
     private Director director;
     private String plNombre;
     private Integer plYear;
     private Set elencos = new HashSet(0);
     private Set estrenos = new HashSet(0);

    public Pelicula() {
    }

	
    public Pelicula(Director director) {
        this.director = director;
    }
    public Pelicula(Director director, String plNombre, Integer plYear, Set elencos, Set estrenos) {
       this.director = director;
       this.plNombre = plNombre;
       this.plYear = plYear;
       this.elencos = elencos;
       this.estrenos = estrenos;
    }
   
    public Integer getPlId() {
        return this.plId;
    }
    
    public void setPlId(Integer plId) {
        this.plId = plId;
    }
    public Director getDirector() {
        return this.director;
    }
    
    public void setDirector(Director director) {
        this.director = director;
    }
    public String getPlNombre() {
        return this.plNombre;
    }
    
    public void setPlNombre(String plNombre) {
        this.plNombre = plNombre;
    }
    public Integer getPlYear() {
        return this.plYear;
    }
    
    public void setPlYear(Integer plYear) {
        this.plYear = plYear;
    }
    public Set getElencos() {
        return this.elencos;
    }
    
    public void setElencos(Set elencos) {
        this.elencos = elencos;
    }
    public Set getEstrenos() {
        return this.estrenos;
    }
    
    public void setEstrenos(Set estrenos) {
        this.estrenos = estrenos;
    }




}

