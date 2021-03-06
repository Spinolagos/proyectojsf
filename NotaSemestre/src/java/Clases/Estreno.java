package Clases;
// Generated 30-sep-2014 16:43:33 by Hibernate Tools 3.6.0


import java.util.Date;

/**
 * Estreno generated by hbm2java
 */
public class Estreno  implements java.io.Serializable {


     private EstrenoId id;
     private Cine cine;
     private Pelicula pelicula;
     private Integer esAsistentes;
     private Date esFecha;

    public Estreno() {
    }

	
    public Estreno(EstrenoId id, Cine cine, Pelicula pelicula) {
        this.id = id;
        this.cine = cine;
        this.pelicula = pelicula;
    }
    public Estreno(EstrenoId id, Cine cine, Pelicula pelicula, Integer esAsistentes, Date esFecha) {
       this.id = id;
       this.cine = cine;
       this.pelicula = pelicula;
       this.esAsistentes = esAsistentes;
       this.esFecha = esFecha;
    }
   
    public EstrenoId getId() {
        return this.id;
    }
    
    public void setId(EstrenoId id) {
        this.id = id;
    }
    public Cine getCine() {
        return this.cine;
    }
    
    public void setCine(Cine cine) {
        this.cine = cine;
    }
    public Pelicula getPelicula() {
        return this.pelicula;
    }
    
    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }
    public Integer getEsAsistentes() {
        return this.esAsistentes;
    }
    
    public void setEsAsistentes(Integer esAsistentes) {
        this.esAsistentes = esAsistentes;
    }
    public Date getEsFecha() {
        return this.esFecha;
    }
    
    public void setEsFecha(Date esFecha) {
        this.esFecha = esFecha;
    }




}


