/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Beans;

import Clases.Director;
import Clases.Pelicula;
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
public class PeliculaBeans {
     private Integer plId;
     private Integer drId;
     private Director director;
     private String plNombre;
     private Integer plYear;
     
    private static Pelicula pelicula;    
    private static List<Pelicula> lista;
    private static List<Director> lista2;
    private static boolean edicion;
    
    private HtmlDataTable tabla;
     private HtmlDataTable tabla2;
    /*datos de conecciones*/
    Configuration configuration = new Configuration();
    SessionFactory sessionFactory = configuration.configure().buildSessionFactory();
    Session session = sessionFactory.openSession();
    Transaction transaction = session.beginTransaction();
     
    /**
     * Creates a new instance of PeliculaBeans2
     */
    public PeliculaBeans() {
            if (lista == null) {
            lista = new ArrayList();
        }
          if (lista2 == null) {
            lista2 = new ArrayList();
        }
    }

    public Integer getPlId() {
        return plId;
    }

    public void setPlId(Integer plId) {
        this.plId = plId;
    }

    public Integer getDrId() {
        return drId;
    }

    public void setDrId(Integer drId) {
        this.drId = drId;
    }

    
    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public String getPlNombre() {
        return plNombre;
    }

    public void setPlNombre(String plNombre) {
        this.plNombre = plNombre;
    }

    public Integer getPlYear() {
        return plYear;
    }

    public void setPlYear(Integer plYear) {
        this.plYear = plYear;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public List<Pelicula> getLista() {
        return lista;
    }

    public void setLista(List<Pelicula> lista) {
        this.lista = lista;
    }

    public HtmlDataTable getTabla() {
        return tabla;
    }

    public void setTabla(HtmlDataTable tabla) {
        this.tabla = tabla;
    }

    public HtmlDataTable getTabla2() {
        return tabla2;
    }

    public void setTabla2(HtmlDataTable tabla2) {
        this.tabla2 = tabla2;
    }

    public List<Director> getLista2() {
        return lista2;
    }

    public void setLista2(List<Director> lista2) {
        PeliculaBeans.lista2 = lista2;
    }
    
    public void VerD() {
           session.close();
           session = HibernateUtil.getSessionFactory().openSession();
           Transaction t = null;

        try {
           
            String consulta = "from Director";
            Query query = session.createQuery(consulta);
            lista2 = query.list();

        } catch (HibernateException ex) {
            if (t != null) {
                t.rollback();
            }
        } finally {
            session.flush();
            session.close();
        }
    }
    
    
    
    public void Ver() {
            
      Transaction t = null;

        try {
            t = session.beginTransaction();
            String consulta = "from Pelicula";
            Query query = session.createQuery(consulta);
            lista = query.list();

        } catch (HibernateException ex) {
            if (t != null) {
                t.rollback();
            }
        } finally {
            session.flush();
            session.close();
        } 
    }
    
      public String agregar(){
        Director elegido = (Director) this.getTabla2().getRowData();
        
        this.setDrId(elegido.getDrId());
        lista2.clear();
        lista2.add(elegido);
            
        return "pelicula.xhtml";
    }
     
    
    public String enviar() {
        Director d = new Director();
        Pelicula p = new Pelicula();
        for(Director list : lista2)
        {
            d.setDrId(list.getDrId());
        }
        
        p.setDirector(d);
        p.setPlNombre(this.getPlNombre());
        p.setPlYear(this.getPlYear());
        
        this.setPelicula(p);

        if (!edicion) {
            session.save(this.getPelicula());
            transaction.commit();
        }
        Ver();
        return "peliculaLista.xhtml";
    } 
     
    
     public String modificar() {
        
        Pelicula elegido = (Pelicula) this.getTabla().getRowData();
        Director dire = (Director) this.getTabla2().getRowData();
        
       
        this.setDirector(dire);
        this.setPlId(elegido.getPlId());
        this.setPlNombre(elegido.getPlNombre());
        this.setPlYear(elegido.getPlYear());     
        // Staticos
        pelicula.setPlId(elegido.getPlId());
        pelicula.setPlNombre(elegido.getPlNombre());
        pelicula.setPlYear(elegido.getPlYear());
        director.setDrId(dire.getDrId());
        pelicula.setDirector(director);
        
        edicion = true;
        return "pelicula.xhtml";
    }
     
     public String listita(){
        Ver();
        return "peliculaLista.xhtml";
     } 
      public String ir(){
         VerD();
        return "pelicula.xhtml";
     } 
      
      public String volver(){
        edicion = false;
        return "index.xhtml";
     } 
       public void cancelarModificar(){
        edicion = false;
        
     }
       public void limpiar() {
       
    }
       
       
}

    

