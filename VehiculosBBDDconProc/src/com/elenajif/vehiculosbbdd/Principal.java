package com.elenajif.vehiculosbbdd;

/**
 * Created by DAM on 02/12/2021.
 */
public class Principal {
    public static void main(String[] args) {
        Vista vista = new Vista();
        Modelo modelo = new Modelo();
        Controlador controlador = new Controlador(vista, modelo);
    }
}
