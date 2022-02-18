package gui;

import com.alejandrogata.practica2ud3.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.ArrayList;

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
        configuracion.addAnnotatedClass(Tienda.class);
        configuracion.addAnnotatedClass(Puzzle.class);
        configuracion.addAnnotatedClass(Comprador.class);
        configuracion.addAnnotatedClass(Editorial.class);
        configuracion.addAnnotatedClass(CompradorPuzzle.class);
        configuracion.addAnnotatedClass(CompradorTienda.class);
        configuracion.addAnnotatedClass(VentaPuzzle.class);

        //Creamos un objeto ServiceRegistry a partir de los parámetros de configuración
        //Esta clase se usa para gestionar y proveer de acceso a servicios
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().applySettings(
                configuracion.getProperties()).build();

        //finalmente creamos un objeto sessionfactory a partir de la configuracion y del registro de servicios
        sessionFactory = configuracion.buildSessionFactory(ssr);

    }

    public ArrayList<Puzzle> getPuzzles() {
        Session sesion = sessionFactory.openSession();
        Query query = sesion.createQuery("FROM Puzzle");
        ArrayList<Puzzle> lista = (ArrayList<Puzzle>)query.getResultList();
        sesion.close();
        return lista;
    }

    public void modificarPuzzle(Puzzle puzzleSeleccion) {
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();
        sesion.saveOrUpdate(puzzleSeleccion);
        sesion.getTransaction().commit();
        sesion.close();
    }

    public void borrarPuzzle(Puzzle puzzleBorrado) {
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();
        sesion.delete(puzzleBorrado);
        sesion.getTransaction().commit();
        sesion.close();
    }

    public ArrayList<Comprador> getCompradores() {
        Session sesion = sessionFactory.openSession();
        Query query = sesion.createQuery("FROM Comprador");
        ArrayList<Comprador> listaCompradores = (ArrayList<Comprador>)query.getResultList();
        sesion.close();
        return listaCompradores;
    }
    public ArrayList<Tienda> getTiendas(){
        Session sesion = sessionFactory.openSession();
        Query query = sesion.createQuery("FROM Tienda");
        ArrayList<Tienda> listaTiendas = (ArrayList<Tienda>)query.getResultList();
        sesion.close();
        return listaTiendas;
    }

    public ArrayList<Editorial> getEditoriales(){
        Session sesion = sessionFactory.openSession();
        Query query = sesion.createQuery("FROM Editorial");
        ArrayList<Editorial> listaEditoriales = (ArrayList<Editorial>)query.getResultList();
        sesion.close();
        return listaEditoriales;
    }
    public ArrayList<VentaPuzzle> getVentas(){
        Session sesion = sessionFactory.openSession();
        Query query = sesion.createQuery("FROM VentaPuzzle");
        ArrayList<VentaPuzzle> ventas = (ArrayList<VentaPuzzle>)query.getResultList();
        sesion.close();
        return ventas;
    }
    public ArrayList<CompradorPuzzle> getCompras(){
        Session sesion = sessionFactory.openSession();
        Query query = sesion.createQuery("FROM CompradorPuzzle");
        ArrayList<CompradorPuzzle> cp = (ArrayList<CompradorPuzzle>)query.getResultList();
        sesion.close();
        return cp;
    }
    public void altaComprador(Comprador c) {
        //Obtengo una session a partir de la factoria de sesiones
        Session sesion = sessionFactory.openSession();

        sesion.beginTransaction();
        sesion.save(c);
        sesion.getTransaction().commit();

        sesion.close();
    }

    public void modificarComprador(Comprador c) {

    }
    public void insertar(Object o) {
        //Obtengo una session a partir de la factoria de sesiones
        Session sesion = sessionFactory.openSession();

        sesion.beginTransaction();
        sesion.save(o);
        sesion.getTransaction().commit();

        sesion.close();
    }


    public void modificar(Object o) {
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();
        sesion.saveOrUpdate(o);
        sesion.getTransaction().commit();
        sesion.close();
    }
    public void eliminar(Object o) {
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();
        sesion.delete(o);
        sesion.getTransaction().commit();
        sesion.close();
    }
}
