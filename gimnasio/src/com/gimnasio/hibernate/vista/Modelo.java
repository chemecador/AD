package com.gimnasio.hibernate.vista;

import com.gimnasio.hibernate.clases.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.ArrayList;

/**
 * Clase Modelo, que será la encargada de conectar la aplicación con hibernate, que a su vez conectará con la BBDD
 */
public class Modelo {
    SessionFactory sessionFactory;

    /**
     * Desconecta de hibernate
     */
    public void desconectar() {
        //Cierro la factoria de sessiones
        if(sessionFactory != null && sessionFactory.isOpen())
            sessionFactory.close();
    }

    /**
     * Conecta hibernate con las configuraciones oportuna (addAnnotatedClass)
     */
    public void conectar() {
        Configuration configuracion = new Configuration();
        //Cargo el fichero Hibernate.cfg.xml
        configuracion.configure("hibernate.cfg.xml");

        //Indico la clase mapeada con anotaciones
        configuracion.addAnnotatedClass(Actividades.class);
        configuracion.addAnnotatedClass(DetalleActividad.class);
        configuracion.addAnnotatedClass(DetalleProveedor.class);
        configuracion.addAnnotatedClass(Gerentes.class);
        configuracion.addAnnotatedClass(Instructores.class);
        configuracion.addAnnotatedClass(Material.class);
        configuracion.addAnnotatedClass(Proveedores.class);
        configuracion.addAnnotatedClass(Socios.class);
        configuracion.addAnnotatedClass(SociosGerentes.class);

        //Creamos un objeto ServiceRegistry a partir de los parámetros de configuración
        //Esta clase se usa para gestionar y proveer de acceso a servicios
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().applySettings(
                configuracion.getProperties()).build();

        //finalmente creamos un objeto sessionfactory a partir de la configuracion y del registro de servicios
        sessionFactory = configuracion.buildSessionFactory(ssr);

    }

    /**
     * Da de alta un socio
     * @param nuevoSocio nuevo Socio
     */
    public void altaSocio(Socios nuevoSocio) {
        //Obtengo una session a partir de la factoria de sesiones
        Session sesion = sessionFactory.openSession();

        sesion.beginTransaction();
        sesion.save(nuevoSocio);
        sesion.getTransaction().commit();

        sesion.close();
    }

    /**
     * Obtiene todos los socios
     * @return list de socios
     */
    public ArrayList<Socios> getSocios() {
        Session sesion = sessionFactory.openSession();
        Query query = sesion.createQuery("FROM Socios");
        ArrayList<Socios> lista = (ArrayList<Socios>)query.getResultList();
        sesion.close();
        return lista;
    }

    /**
     * Modifica un socio
     * @param socioSeleccion socio a cambiar
     */
    public void modificarSocio(Socios socioSeleccion) {
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();
        sesion.saveOrUpdate(socioSeleccion);
        sesion.getTransaction().commit();
        sesion.close();
    }

    /**
     * Elimina un socio
     * @param socioBorrado socio eliminado
     */
    public void borrarSocio(Socios socioBorrado) {
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();
        sesion.delete(socioBorrado);
        sesion.getTransaction().commit();
        sesion.close();
    }

    // INSTRUCTORES

    /**
     * Da de alta un instructor
     * @param nuevoInstructor da de alta un instructor
     */
    public void altaInstructor(Instructores nuevoInstructor) {
        //Obtengo una session a partir de la factoria de sesiones
        Session sesion = sessionFactory.openSession();

        sesion.beginTransaction();
        sesion.save(nuevoInstructor);
        sesion.getTransaction().commit();

        sesion.close();
    }

    /**
     * Obtiene todos los instructores de la bbdd
     * @return la lista de instructores
     */
    public ArrayList<Instructores> getInstructor() {
        Session sesion = sessionFactory.openSession();
        Query query = sesion.createQuery("FROM Instructores");
        ArrayList<Instructores> lista = (ArrayList<Instructores>)query.getResultList();
        sesion.close();
        return lista;
    }

    /**
     * Modifica un instructor
     * @param instructorSeleccion nuevo instructor a modificar
     */
    public void modificarInstructor(Instructores instructorSeleccion) {
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();
        sesion.saveOrUpdate(instructorSeleccion);
        sesion.getTransaction().commit();
        sesion.close();
    }

    /**
     * Borra un instructor elegido
     * @param instructorBorrado el instructor a eliminar
     */
    public void borrarInstructor(Instructores instructorBorrado) {
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();
        sesion.delete(instructorBorrado);
        sesion.getTransaction().commit();
        sesion.close();
    }

    /**
     * Permite recolecta los socios que tiene X instructor
     * @param sociosInstructor el instructor a ver
     * @return Lista de Socios de X instructor
     */
    public ArrayList<Socios> getSociosInstructor(Instructores sociosInstructor) {
        Session sesion = sessionFactory.openSession();
        Query query = sesion.createQuery("FROM Socios WHERE idInstructor = :prop");
        query.setParameter("prop", sociosInstructor);
        ArrayList<Socios> lista = (ArrayList<Socios>) query.getResultList();
        sesion.close();
        return lista;
    }


    // Actividades

    /**
     * Nos permite dar de alta una actividad
     * @param nuevaActividad nueva actividad
     */
    public void altaActividad(Actividades nuevaActividad) {
        //Obtengo una session a partir de la factoria de sesiones
        Session sesion = sessionFactory.openSession();

        System.out.println(nuevaActividad);
        sesion.beginTransaction();
        sesion.save(nuevaActividad);
        sesion.getTransaction().commit();

        sesion.close();
    }

    /**
     * Nos permite sacar todas las activides
     * @return lista de actividades
     */
    public ArrayList<Actividades> getActividad() {
        Session sesion = sessionFactory.openSession();
        Query query = sesion.createQuery("FROM Actividades");
        ArrayList<Actividades> lista = (ArrayList<Actividades>)query.getResultList();
        sesion.close();
        return lista;
    }

    /**
     * nos permite modificar una actividad
     * @param actividadSeleccion una actividad
     */
    public void modificarActividad(Actividades actividadSeleccion) {
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();
        sesion.saveOrUpdate(actividadSeleccion);
        sesion.getTransaction().commit();
        sesion.close();
    }

    /**
     * Nos permite borrar una actividad
     * @param actividadBorrada actividad borrada
     */
    public void borrarActividad (Actividades actividadBorrada) {
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();
        sesion.delete(actividadBorrada);
        sesion.getTransaction().commit();
        sesion.close();
    }




    // Material

    /**
     * Nos permite dar de alta un material
     * @param nuevoMaterial material nuevo
     */
    public void altaMaterial(Material nuevoMaterial) {
        //Obtengo una session a partir de la factoria de sesiones
        Session sesion = sessionFactory.openSession();

        sesion.beginTransaction();
        sesion.save(nuevoMaterial);
        sesion.getTransaction().commit();

        sesion.close();
    }

    /**
     * Nos permite obtener todos los materiales
     * @return lista de materiales
     */
    public ArrayList<Material> getMaterial() {
        Session sesion = sessionFactory.openSession();
        Query query = sesion.createQuery("FROM Material");
        ArrayList<Material> lista = (ArrayList<Material>)query.getResultList();
        sesion.close();
        return lista;
    }

    /**
     * Nos permite modificar un material
     * @param materialSeleccion material modificado
     */
    public void modificarMaterial(Material materialSeleccion) {
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();
        sesion.saveOrUpdate(materialSeleccion);
        sesion.getTransaction().commit();
        sesion.close();
    }

    /**
     * Nos permtie borrar un material
     * @param materialBorrado material eliminado
     */
    public void borrarMaterial (Material materialBorrado) {
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();
        sesion.delete(materialBorrado);
        sesion.getTransaction().commit();
        sesion.close();
    }



    // Proveedor

    /**
     * Nos permite dar de alta un proveedor
     * @param nuevoProveedor nuevo proveedor
     */
    public void altaProveedor(Proveedores nuevoProveedor) {
        //Obtengo una session a partir de la factoria de sesiones
        Session sesion = sessionFactory.openSession();

        sesion.beginTransaction();
        sesion.save(nuevoProveedor);
        sesion.getTransaction().commit();

        sesion.close();
    }

    /**
     * Nos permite obtener todos los proveedores
     * @return lista de proveedores
     */
    public ArrayList<Proveedores> getProveedor() {
        Session sesion = sessionFactory.openSession();
        Query query = sesion.createQuery("FROM Proveedores");
        ArrayList<Proveedores> lista = (ArrayList<Proveedores>)query.getResultList();
        sesion.close();
        return lista;
    }

    /**
     * Nos permite modificar un proveedor
     * @param proveedorSeleccion proveedor modificado
     */
    public void modificarProveedor(Proveedores proveedorSeleccion) {
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();
        sesion.saveOrUpdate(proveedorSeleccion);
        sesion.getTransaction().commit();
        sesion.close();
    }

    /**
     * Nos permite eliminar un proveedor
     * @param proveedorBorrado proveedor eliminado
     */
    public void borrarProveedor(Proveedores proveedorBorrado) {
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();
        sesion.delete(proveedorBorrado);
        sesion.getTransaction().commit();
        sesion.close();
    }

    /**
     * Nos permite obtener todos los pedidos de X proveedor
     * @param detalleProveedor el proveedor a observar
     * @return la lista de Pedidos de dicho proveedor
     */
    public ArrayList<DetalleProveedor> getPedidosProveedores(Proveedores detalleProveedor) {
        Session sesion = sessionFactory.openSession();
        Query query = sesion.createQuery("FROM DetalleProveedor WHERE proveedor = :prop");
        System.out.println("HE HECHO LA CONSULTA YA");
        System.out.println("PEDIDO -> " + detalleProveedor);
        query.setParameter("prop", detalleProveedor);
        ArrayList<DetalleProveedor> lista = (ArrayList<DetalleProveedor>) query.getResultList();
        System.out.println(lista);
        sesion.close();
        return lista;
    }


    /**
     * Da de alta un pedido
     * @param nuevoPedido el pedido nuevo
     */
    public void altaPedido(DetalleProveedor nuevoPedido) {
        //Obtengo una session a partir de la factoria de sesiones
        Session sesion = sessionFactory.openSession();

        sesion.beginTransaction();
        sesion.save(nuevoPedido);
        sesion.getTransaction().commit();

        sesion.close();
    }

    /**
     * Nos permite obtener los pedidos
     * @return la lista de pedidos
     */
    public ArrayList<DetalleProveedor> getPedido() {
        Session sesion = sessionFactory.openSession();
        Query query = sesion.createQuery("FROM DetalleProveedor");
        ArrayList<DetalleProveedor> lista = (ArrayList<DetalleProveedor>)query.getResultList();
        sesion.close();
        return lista;
    }

    /**
     * Modifica un pedido
     * @param pedidoSeleccion pedido a modificar
     */
    public void modificarPedido(DetalleProveedor pedidoSeleccion) {
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();
        sesion.saveOrUpdate(pedidoSeleccion);
        sesion.getTransaction().commit();
        sesion.close();
    }

    /**
     * Método que permite eliminar un pedido
     * @param pedidoBorrado EL PEDIDO A BORRAR
     */
    public void borrarPedido(DetalleProveedor pedidoBorrado) {
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();
        sesion.delete(pedidoBorrado);
        sesion.getTransaction().commit();
        sesion.close();
    }
}
