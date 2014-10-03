/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Beans;

import Clases.Actor;
import Clases.ElencoId;
import Clases.Pelicula;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Nanami
 */
@ManagedBean
@RequestScoped
public class ElencoBeans {

    /**
     * Creates a new instance of ElencoBeans
     */
    private ElencoId id;
    private Actor actor;
    private Pelicula pelicula;
    private Boolean elProtagonista;
    private int actorAcId;
    private int peliculaPlId;
    
    public ElencoBeans() {
    }
    
    
    
}
