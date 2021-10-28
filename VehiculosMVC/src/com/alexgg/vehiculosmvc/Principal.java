package com.alexgg.vehiculosmvc;


import com.alexgg.vehiculosmvc.gui.VehiculosControlador;
import com.alexgg.vehiculosmvc.gui.VehiculosModelo;
import com.alexgg.vehiculosmvc.gui.Ventana;

public class Principal {
    public static void main(String[] args) {
        Ventana vista = new Ventana();
        VehiculosModelo modelo = new VehiculosModelo();
        VehiculosControlador controlador = new VehiculosControlador(modelo,vista);
    }
}
