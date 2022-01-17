package main;

import gui.Controlador;
import gui.Modelo;
import gui.Vista;

/**
 * Clase principal
 */
public class Principal {
    /***
     * Método principal
     * @param args
     */
    public static void main(String[] args) {
        Vista vista = new Vista();
        Modelo modelo = new Modelo();
        Controlador controlador = new Controlador(vista, modelo);
    }
}
