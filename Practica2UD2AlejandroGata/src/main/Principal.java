package main;

import gui.Controlador;
import gui.Modelo;
import gui.Vista;

/**
 * Created by DAM on 13/12/2021.
 */
public class Principal {
    public static void main(String[] args) {
        Vista vista = new Vista();
        Modelo modelo = new Modelo();
        Controlador controlador = new Controlador(vista, modelo);
    }
}
