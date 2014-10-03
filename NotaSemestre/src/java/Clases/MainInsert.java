/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Clases;

import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Nanami
 */
public class MainInsert {
    public static void main(String[] args) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sesion.beginTransaction();
        
       
    
//        p.setPNombre("Huepil");
//        sesion.save(p);
//        t.commit();
//        
//        System.out.println("Pais ingresado");
        Director d = new Director();
        d.setDrId(1);
        Pelicula p = new Pelicula();
        p.setPlNombre("movie");
        p.setPlYear(23);      
        p.setDirector(d);
        sesion.save(p);
        
        t.commit();
        
        System.out.println("pelicula ha sido registrada");
        
        
    }
}
