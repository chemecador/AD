package com.practicaUD4;

import com.practicaUD4.gui.Modelo;
import com.practicaUD4.gui.Controlador;
import com.practicaUD4.gui.Vista;

/**
 * Clase Principal
 * @author
 */
public class Principal {
    /**
     * Método main(), es estatico e inicia a la aplicacion
     * @param args de tipo String[]
     */
    public static void main(String[] args) {
        Vista vista = new Vista();
        Modelo modelo = new Modelo();
        Controlador controlador = new Controlador(vista, modelo);
    }
}
