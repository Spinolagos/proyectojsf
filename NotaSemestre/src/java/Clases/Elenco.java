package Clases;
// Generated 30-sep-2014 16:43:33 by Hibernate Tools 3.6.0



/**
 * Elenco generated by hbm2java
 */
public class Elenco  implements java.io.Serializable {


     private ElencoId id;
     private Actor actor;
     private Pelicula pelicula;
     private Boolean elProtagonista;

    public Elenco() {
    }

	
    public Elenco(ElencoId id, Actor actor, Pelicula pelicula) {
        this.id = id;
        this.actor = actor;
        this.pelicula = pelicula;
    }
    public Elenco(ElencoId id, Actor actor, Pelicula pelicula, Boolean elProtagonista) {
       this.id = id;
       this.actor = actor;
       this.pelicula = pelicula;
       this.elProtagonista = elProtagonista;
    }
   
    public ElencoId getId() {
        return this.id;
    }
    
    public void setId(ElencoId id) {
        this.id = id;
    }
    public Actor getActor() {
        return this.actor;
    }
    
    public void setActor(Actor actor) {
        this.actor = actor;
    }
    public Pelicula getPelicula() {
        return this.pelicula;
    }
    
    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }
    public Boolean getElProtagonista() {
        return this.elProtagonista;
    }
    
    public void setElProtagonista(Boolean elProtagonista) {
        this.elProtagonista = elProtagonista;
    }




}


