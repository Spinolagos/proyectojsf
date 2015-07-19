/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Beans;

import Clases.Cine;
import Clases.Ciudad;
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
public class CineBeans {
     private Integer cnId;
     private Integer cdId;
     private Ciudad ciudad;
     private String cnNombre;
     
    private static Cine cine;
    private static List<Cine> lista;
    private static List<Ciudad> lista2;    
    private static boolean edicion;
    
    private HtmlDataTable tabla;
    private HtmlDataTable tabla2;
    /*datos de conecciones*/
    Configuration configuration = new Configuration();
    SessionFactory sessionFactory = configuration.configure().buildSessionFactory();
    Session session = sessionFactory.openSession();
    Transaction transaction = session.beginTransaction();
    /**
     * Creates a new instance of CineBeans
     */
    public CineBeans() {
          if (lista == null) {
            lista = new ArrayList();
        }
          if (lista2 == null) {
            lista2 = new ArrayList();
        }
    }

    public Integer getCnId() {
        return cnId;
    }

    public void setCnId(Integer cnId) {
        this.cnId = cnId;
    }

    public Integer getCdId() {
        return cdId;
    }

    public void setCdId(Integer cdId) {
        this.cdId = cdId;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public String getCnNombre() {
        return cnNombre;
    }

    public void setCnNombre(String cnNombre) {
        this.cnNombre = cnNombre;
    }

    public Cine getCine() {
        return cine;
    }

    public void setCine(Cine cine) {
        this.cine = cine;
    }

    public List<Cine> getLista() {
        return lista;
    }

    public void setLista(List<Cine> lista) {
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

    public List<Ciudad> getLista2() {
        return lista2;
    }

    public void setLista2(List<Ciudad> lista2) {
        this.lista2 = lista2;
    }
    

    public void VerC() {
           session.close();
           session = HibernateUtil.getSessionFactory().openSession();
           Transaction t = null;

        try {
           
            String consulta = "from Ciudad";
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
            
            String consulta = "from Cine";
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
        Ciudad elegido = (Ciudad) this.getTabla2().getRowData();
        
        this.setCdId(elegido.getCdId());
        lista2.clear();
        lista2.add(elegido);
            
        return "cine.xhtml";
    }
    
    public String enviar() {
        Ciudad c = new Ciudad();
        Cine cn = new Cine();
        for(Ciudad list : lista2)
        {
            c.setCdId(list.getCdId());
        }
        cn.setCiudad(c);
        cn.setCnNombre(this.getCnNombre());

        this.setCine(cn);

        if (!edicion) {
            session.save(this.getCine());
            transaction.commit();
        }
        Ver();
        return "cineLista.xhtml";
    } 
     
    
     public String listita(){
        Ver();
        return "cineLista.xhtml";
     } 
      public String ir(){
        VerC();  
        return "cine.xhtml";
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

    

    

