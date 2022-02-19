package gui;

import com.alejandrogata.practica2ud3.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.ArrayList;

/***
 * Clase Modelo
 */
public class Modelo {

    SessionFactory sessionFactory;

    /***
     * Método desconectar
     */
    public void desconectar() {
        //Cierro la factoria de sessiones
        if(sessionFactory != null && sessionFactory.isOpen())
            sessionFactory.close();
    }

    /***
     * Método conectar
     */
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

    /***
     * Devuelve los puzzles que tiene una editorial
     * @param editorial editorial
     * @return lista de puzzles
     */
    ArrayList<Puzzle> getEditorialPuzzles(Editorial editorial) {
        Session sesion = sessionFactory.openSession();
        Query query = sesion.createQuery("FROM Puzzle WHERE editorial =: edit");
        query.setParameter("edit", editorial);
        ArrayList<Puzzle> lista = (ArrayList<Puzzle>)query.getResultList();
        sesion.close();
        return lista;
    }

    /***
     * Devuelve los puzzles disponibles
     * @return lista de puzzles
     */
    ArrayList<Puzzle> getPuzzles() {
        Session sesion = sessionFactory.openSession();
        Query query = sesion.createQuery("FROM Puzzle");
        ArrayList<Puzzle> lista = (ArrayList<Puzzle>)query.getResultList();
        sesion.close();
        return lista;
    }


    /***
     * Devuelve los compradores disponibles
     * @return lista de compradores
     */
    ArrayList<Comprador> getCompradores() {
        Session sesion = sessionFactory.openSession();
        Query query = sesion.createQuery("FROM Comprador");
        ArrayList<Comprador> listaCompradores = (ArrayList<Comprador>)query.getResultList();
        sesion.close();
        return listaCompradores;
    }

    /***
     * Devuelve las tiendas disponibles
     * @return lista de tiendas
     */
    ArrayList<Tienda> getTiendas(){
        Session sesion = sessionFactory.openSession();
        Query query = sesion.createQuery("FROM Tienda");
        ArrayList<Tienda> listaTiendas = (ArrayList<Tienda>)query.getResultList();
        sesion.close();
        return listaTiendas;
    }

    /***
     * Devuelve las editoriales disponibles
     * @return lista de editoriales
     */
    ArrayList<Editorial> getEditoriales(){
        Session sesion = sessionFactory.openSession();
        Query query = sesion.createQuery("FROM Editorial");
        ArrayList<Editorial> listaEditoriales = (ArrayList<Editorial>)query.getResultList();
        sesion.close();
        return listaEditoriales;
    }

    /***
     * Devuelve las ventas disponibles
     * @return lista de ventas
     */
    ArrayList<VentaPuzzle> getVentas(){
        Session sesion = sessionFactory.openSession();
        Query query = sesion.createQuery("FROM VentaPuzzle");
        ArrayList<VentaPuzzle> ventas = (ArrayList<VentaPuzzle>)query.getResultList();
        sesion.close();
        return ventas;
    }

    /***
     * Insertar un objeto en la BBDD
     * @param o objeto a insertar en la BBDD
     */
    void insertar(Object o) {
        //Obtengo una session a partir de la factoria de sesiones
        Session sesion = sessionFactory.openSession();

        sesion.beginTransaction();
        sesion.save(o);
        sesion.getTransaction().commit();

        sesion.close();
    }

    /***
     * Modificar un objeto de la BBDD
     * @param o objeto a modificar en la BBDD
     */
    void modificar(Object o) {
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();
        sesion.saveOrUpdate(o);
        sesion.getTransaction().commit();
        sesion.close();
    }

    /***
     * Eliminar un objeto de la BBDD
     * @param o objeto a eliminar en la BBDD
     */
    void eliminar(Object o) {
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();
        sesion.delete(o);
        sesion.getTransaction().commit();
        sesion.close();
    }
}
