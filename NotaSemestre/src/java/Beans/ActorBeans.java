/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Beans;

import Clases.Actor;
import hibernate.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlDataTable;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Nanami
 */
@ManagedBean
@RequestScoped
public class ActorBeans {
    Configuration configuration = new Configuration();
    SessionFactory sessionFactory = configuration.configure().buildSessionFactory();
    Session session = sessionFactory.openSession();
    Transaction transaction = session.beginTransaction();

     private Integer acId;
     private String acNombre;
     
     private static List<Actor> Lista;
     private static Actor actor;
     private static boolean edicion;
     private HtmlDataTable tabla;
    
    /**
     * Creates a new instance of ActorBean
     */
    public ActorBeans() {
        if (Lista == null) {
            Lista = new ArrayList();
        } 
    }

  
    public List<Actor> getLista() {
        return Lista;
    }

    public void setLista(List<Actor> Lista) {
        ActorBeans.Lista = Lista;
    }

    
    public HtmlDataTable getTabla() {
        return tabla;
    }

    public void setTabla(HtmlDataTable tabla) {
        this.tabla = tabla;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Integer getAcId() {
        return acId;
    }

    public void setAcId(Integer acId) {
        this.acId = acId;
    }

    public String getAcNombre() {
        return acNombre;
    }

    public void setAcNombre(String acNombre) {
        this.acNombre = acNombre;
    }
    
    
     public void Ver() {
        Transaction t = null;
       
        try {
            t = session.beginTransaction();
            String consulta = "from Actor";
            Query query = session.createQuery(consulta);
            Lista = query.list();

        } catch (HibernateException ex) {
            if (t != null) {
                t.rollback();
            }
        } finally {
            session.flush();
            session.close();
        }
    }
     
    
    public String enviar() {

         Actor act = new Actor();
        if(edicion)
        {
           act = actor;
        }
        
        act.setAcNombre(this.getAcNombre());
        
        this.setActor(act);
        

        if (!edicion) {
            session.save(this.getActor());
            transaction.commit();
        } else {
            Transaction t = null;
            session = HibernateUtil.getSessionFactory().openSession();
            t = session.beginTransaction();
            session.update(actor);
            t.commit();
            edicion = false;
        }
        Ver();
        return "actorLista.xhtml";
    } 
    
     public String modificar() {
        
        Actor elegido = (Actor) this.getTabla().getRowData();
        
        this.setAcId(elegido.getAcId());
        this.setAcNombre(elegido.getAcNombre());
        // Staticos
        actor.setAcId(elegido.getAcId());
        actor.setAcNombre(elegido.getAcNombre());
        
        edicion = true;
        return "actor.xhtml";
    }
     
     public String listita(){
        Ver();
        return "actorLista.xhtml";
     } 
      public String ir(){
        return "actor.xhtml";
     } 
      
      public String volver(){
        edicion = false;
        return "index.xhtml";
     } 
       public void cancelarModificar(){
        edicion = false;
        this.setAcNombre("");
     }
       public void limpiar() {
        this.setAcNombre("");
    }
}
