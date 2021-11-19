package main;

import examenUD1.gui.Controlador;
import examenUD1.gui.Modelo;
import examenUD1.gui.Ventana.Ventana;

public class Principal {
    public static void main(String[] args) {
        Ventana vista = new Ventana();
        Modelo modelo = new Modelo();
        Controlador controlador = new Controlador(vista, modelo);
    }
}
