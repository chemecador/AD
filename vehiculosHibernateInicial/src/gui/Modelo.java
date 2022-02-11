package gui;

import com.elenajif.vehiculosHibernateElena.Coche;
import com.elenajif.vehiculosHibernateElena.Propietario;
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

        if(sessionFactory != null && sessionFactory.isOpen()){
            sessionFactory.close();
        }
    }

    public void conectar() {

        Configuration conf = new Configuration();
        //cargar fichero conf
        conf.configure("hibernate.cfg.xml");
        //indicar clases mapeadas
        conf.addAnnotatedClass(Coche.class);
        conf.addAnnotatedClass(Propietario.class);
        //crear objeto ServiceRegistry a partir de los parámetros de conf
        //gestiona y provee acceso a servicios
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties()).build();
        //finalmente se crea el objeto a partir de la configuración
        sessionFactory = conf.buildSessionFactory();
    }

    public void altaCoche() {

    }

    public ArrayList<Coche> getCoches() {

    }

    public void modificar(Coche cocheSeleccion) {

    }

    public void borrar(Coche cocheBorrado) {

    }

    public ArrayList<Propietario> getPropietarios(){

    }

    public ArrayList<Coche> getCochesPropietario(){

    }



}
