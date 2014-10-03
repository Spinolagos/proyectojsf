/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Beans;

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
public class CiudadBeans {
    
    Configuration configuration = new Configuration();
    SessionFactory sessionFactory = configuration.configure().buildSessionFactory();
    Session session = sessionFactory.openSession();
    Transaction transaction = session.beginTransaction();
    private static boolean edicion;
    
    
    private Integer cdId;
    private String cdNombre;
    private Integer cdRegion;
    private static Ciudad ciudad;
    
    private static List<Ciudad> Lista;
    private HtmlDataTable tabla;
    
    
    /**
     * Creates a new instance of CiudadBeans
     */
    public CiudadBeans() {
          if (Lista == null) {
            Lista = new ArrayList();
        }
    }

    public Integer getCdId() {
        return cdId;
    }

    public void setCdId(Integer cdId) {
        this.cdId = cdId;
    }

    public String getCdNombre() {
        return cdNombre;
    }

    public void setCdNombre(String cdNombre) {
        this.cdNombre = cdNombre;
    }

    public Integer getCdRegion() {
        return cdRegion;
    }

    public void setCdRegion(Integer cdRegion) {
        this.cdRegion = cdRegion;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public List<Ciudad> getLista() {
        return Lista;
    }

    public void setLista(List<Ciudad> Lista) {
        CiudadBeans.Lista = Lista;
    }

    public HtmlDataTable getTabla() {
        return tabla;
    }

    public void setTabla(HtmlDataTable tabla) {
        this.tabla = tabla;
    }
    
      public void Ver() {
        Transaction t = null;

        try {
            t = session.beginTransaction();
            String consulta = "from Ciudad";
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

        Ciudad city = new Ciudad();
        if(edicion)
        {
           city = ciudad;
        }
        
        city.setCdNombre(this.getCdNombre());
        city.setCdRegion(this.getCdRegion());

        this.setCiudad(city);

        if (!edicion) {
            session.save(this.getCiudad());
            transaction.commit();
        } else {
            Transaction t = null;
             session = HibernateUtil.getSessionFactory().openSession();
            t = session.beginTransaction();
            session.update(ciudad);
            t.commit();
            edicion = false;
        }
        Ver();
        return "ciudadLista.xhtml";
    }
   
    
    
    public String modificar() {
        Ciudad elegido = (Ciudad) this.getTabla().getRowData();
        
        this.setCdId(elegido.getCdId());
        this.setCdNombre(elegido.getCdNombre());
        this.setCdRegion(elegido.getCdRegion());
        
        // Staticos
        ciudad.setCdId(elegido.getCdId());
        ciudad.setCdNombre(elegido.getCdNombre());
        ciudad.setCdRegion(elegido.getCdRegion());
        
        edicion = true;
        return "ciudad.xhtml";
    }
    
    public String ir(){
          return "ciudad.xhtml";
    }
     public String volver(){
          edicion = false;
          return "index.xhtml";
    }
     public String listita(){
          Ver();
        return "ciudadLista.xhtml";
     }
     public void cancelarModificar(){
        edicion = false;
        this.setCdNombre("");
        this.setCdRegion(0);
     }
    public void limpiar() {
        this.setCdNombre("");
        this.setCdRegion(0);
    }
}
