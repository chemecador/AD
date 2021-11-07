package com.alexgg.practica1ud1;


import com.alexgg.practica1ud1.gui.ProductosControlador;
import com.alexgg.practica1ud1.gui.ProductosModelo;
import com.alexgg.practica1ud1.gui.Vista;

/**
 * Clase Principal. Contiene el main para ejecutar el programa.
 * */
public class Principal {
    public static void main(String[] args) {

        Vista vista = new Vista();
        ProductosModelo modelo= new ProductosModelo();
        ProductosControlador controlador = new ProductosControlador(vista,modelo);
    }
}
