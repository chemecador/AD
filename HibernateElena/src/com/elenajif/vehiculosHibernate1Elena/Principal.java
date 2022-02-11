package com.elenajif.vehiculosHibernate1Elena;

import com.elenajif.vehiculosHibernate1Elena.gui.Controlador;
import com.elenajif.vehiculosHibernate1Elena.gui.Modelo;
import com.elenajif.vehiculosHibernate1Elena.gui.Vista;


public class Principal {
    public static void main(String[] args) {
        Vista vista = new Vista();
        Modelo modelo = new Modelo();
        Controlador controlador = new Controlador(vista,modelo);
    }
}