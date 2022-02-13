package gui;

import com.alejandrogata.practica1ud3.Comprador;
import com.alejandrogata.practica1ud3.Editorial;
import com.alejandrogata.practica1ud3.Puzzle;
import com.alejandrogata.practica1ud3.Tienda;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class Modelo {
    SessionFactory sessionFactory;

    public void desconectar() {
        //Cierro la factoria de sessiones
        if(sessionFactory != null && sessionFactory.isOpen())
            sessionFactory.close();
    }

    public void conectar() {
        Configuration configuracion = new Configuration();
        //Cargo el fichero Hibernate.cfg.xml
        configuracion.configure("hibernate.cfg.xml");

        //Indico la clase mapeada con anotaciones
        configuracion.addAnnotatedClass(Puzzle.class);
        configuracion.addAnnotatedClass(Comprador.class);
        configuracion.addAnnotatedClass(Editorial.class);
        configuracion.addAnnotatedClass(Tienda.class);

        //Creamos un objeto ServiceRegistry a partir de los parámetros de configuración
        //Esta clase se usa para gestionar y proveer de acceso a servicios
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().applySettings(
                configuracion.getProperties()).build();

        //finalmente creamos un objeto sessionfactory a partir de la configuracion y del registro de servicios
        sessionFactory = configuracion.buildSessionFactory(ssr);

    }

    public void altaPuzzle(Puzzle nuevoPuzzle) {
        //Obtengo una session a partir de la factoria de sesiones
        Session sesion = sessionFactory.openSession();

        sesion.beginTransaction();
        sesion.save(nuevoPuzzle);
        sesion.getTransaction().commit();

        sesion.close();
    }

    public ArrayList<Puzzle> getPuzzles() {
        Session sesion = sessionFactory.openSession();
        Query query = sesion.createQuery("FROM Puzzle");
        ArrayList<Puzzle> lista = (ArrayList<Puzzle>)query.getResultList();
        sesion.close();
        return lista;
    }

    public void modificar(Puzzle puzzleSeleccion) {
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();
        sesion.saveOrUpdate(puzzleSeleccion);
        sesion.getTransaction().commit();
        sesion.close();
    }

    public void borrar(Puzzle puzzleBorrado) {
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();
        sesion.delete(puzzleBorrado);
        sesion.getTransaction().commit();
        sesion.close();
    }

    public ArrayList<Comprador> getPropietarios() {
        Session sesion = sessionFactory.openSession();
        Query query = sesion.createQuery("FROM Propietario");
        ArrayList<Comprador> listaCompradores = (ArrayList<Comprador>)query.getResultList();
        sesion.close();
        return listaCompradores;
    }

    public ArrayList<Puzzle> getPuzzlesComprador(Comprador compradorSeleccionado) {
        Session sesion = sessionFactory.openSession();
        Query query = sesion.createQuery("FROM puzzle WHERE comprador = :prop");
        query.setParameter("prop", compradorSeleccionado);
        ArrayList<Puzzle> lista = (ArrayList<Puzzle>) query.getResultList();
        sesion.close();
        return lista;
    }
}
