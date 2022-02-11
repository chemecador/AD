package com.elenajif.vehiculosHibernate1Elena.gui;

import com.elenajif.vehiculosHibernate1Elena.Coche;
import com.elenajif.vehiculosHibernate1Elena.Propietario;
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
        configuracion.addAnnotatedClass(Coche.class);
        configuracion.addAnnotatedClass(Propietario.class);

        //Creamos un objeto ServiceRegistry a partir de los parámetros de configuración
        //Esta clase se usa para gestionar y proveer de acceso a servicios
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().applySettings(
                configuracion.getProperties()).build();

        //finalmente creamos un objeto sessionfactory a partir de la configuracion y del registro de servicios
        sessionFactory = configuracion.buildSessionFactory(ssr);

    }

    public void altaCoche(Coche nuevoCoche) {
        //Obtengo una session a partir de la factoria de sesiones
        Session sesion = sessionFactory.openSession();

        sesion.beginTransaction();
        sesion.save(nuevoCoche);
        sesion.getTransaction().commit();

        sesion.close();
    }

    public ArrayList<Coche> getCoches() {
        Session sesion = sessionFactory.openSession();
        Query query = sesion.createQuery("FROM Coche");
        ArrayList<Coche> lista = (ArrayList<Coche>)query.getResultList();
        sesion.close();
        return lista;
    }

    public void modificar(Coche cocheSeleccion) {
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();
        sesion.saveOrUpdate(cocheSeleccion);
        sesion.getTransaction().commit();
        sesion.close();
    }

    public void borrar(Coche cocheBorrado) {
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();
        sesion.delete(cocheBorrado);
        sesion.getTransaction().commit();
        sesion.close();
    }

    public ArrayList<Propietario> getPropietarios() {
        Session sesion = sessionFactory.openSession();
        Query query = sesion.createQuery("FROM Propietario");
        ArrayList<Propietario> listaPropietarios = (ArrayList<Propietario>)query.getResultList();
        sesion.close();
        return listaPropietarios;
    }

    public ArrayList<Coche> getCochesPropietario(Propietario propietarioSeleccionado) {
        Session sesion = sessionFactory.openSession();
        Query query = sesion.createQuery("FROM Coche WHERE propietario = :prop");
        query.setParameter("prop", propietarioSeleccionado);
        ArrayList<Coche> lista = (ArrayList<Coche>) query.getResultList();
        sesion.close();
        return lista;
    }
}
