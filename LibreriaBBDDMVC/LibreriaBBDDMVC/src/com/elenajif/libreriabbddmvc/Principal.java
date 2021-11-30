package com.elenajif.libreriabbddmvc;

/**
 * Created by PROFESOR on 23/11/2018.
 */
public class Principal {
    public static void main(String[] args) {
        Vista vista = new Vista();
        Modelo modelo = new Modelo();
        Controlador controlador = new Controlador(vista, modelo);
    }
}
