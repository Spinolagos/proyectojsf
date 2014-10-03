/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Clases.Director;
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
public class DirectorBeans {

    private Integer drId;
    private String drNombre;
    private Integer drEdad;

    private static Director director;
    private static List<Director> lista;
    private static boolean edicion;
    private HtmlDataTable tabla;
    /*datos de conecciones*/
    Configuration configuration = new Configuration();
    SessionFactory sessionFactory = configuration.configure().buildSessionFactory();
    Session session = sessionFactory.openSession();
    Transaction transaction = session.beginTransaction();

    /**
     * Creates a new instance of DirectorBeans
     */
    public DirectorBeans() {
        if (lista == null) {
            lista = new ArrayList();
        }
    }

    public Integer getDrId() {
        return drId;
    }

    public void setDrId(Integer drId) {
        this.drId = drId;
    }

    public String getDrNombre() {
        return drNombre;
    }

    public void setDrNombre(String drNombre) {
        this.drNombre = drNombre;
    }

    public Integer getDrEdad() {
        return drEdad;
    }

    public void setDrEdad(Integer drEdad) {
        this.drEdad = drEdad;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public List<Director> getLista() {
        return lista;
    }

    public void setLista(List<Director> lista) {
        this.lista = lista;
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
            String consulta = "from Director";
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

    public String ir() {
        return "director.xhtml";
    }

    public String volver() {
        edicion = false;
        return "index.xhtml";
    }

    public String listita() {
        Ver();
        return "directorLista.xhtml";
    }

    public String enviar() {

        Director dir = new Director();
        if (edicion) {
            dir = director;
        }

        dir.setDrNombre(this.getDrNombre());
        dir.setDrEdad(this.getDrEdad());
        this.setDirector(dir);

        if (!edicion) {
            session.save(this.getDirector());
            transaction.commit();
        } else {
            Transaction t = null;
            session = HibernateUtil.getSessionFactory().openSession();
            t = session.beginTransaction();
            session.update(director);
            t.commit();
            edicion = false;
        }
        Ver();
        return "directorLista.xhtml";
    }

    public String modificar() {

        Director elegido = (Director) this.getTabla().getRowData();

        this.setDrNombre(elegido.getDrNombre());
        this.setDrEdad(elegido.getDrEdad());
        this.setDrId(elegido.getDrId());

        // Staticos
        director.setDrNombre(elegido.getDrNombre());
        director.setDrEdad(elegido.getDrEdad());
        director.setDrId(elegido.getDrId());

        edicion = true;
        return "director.xhtml";
    }

    public void cancelarModificar() {
        edicion = false;
        this.setDrNombre("");
        this.setDrEdad(0);
    }

    public void limpiar() {
        this.setDrNombre("");
        this.setDrEdad(0);
    }

}
