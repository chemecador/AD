package com.gimnasio.hibernate.vista;

import com.gimnasio.hibernate.clases.Actividades;

public class Ejecucion {
    /**
     * Trabajo de hibernate con previo mapeo de 5 clases (sin incluir gerente por falta de tiempo) y con tablas intermedias.
     * @see Actividades , DetalleProveedor, Gerentes, Instructores, Material, Proveedores, Socios, SociosGerentes
     * @see Controlador
     * @see Modelo
     * @see Vista
     */
    public static void main(String[] args) {
        Vista vista = new Vista();
        Modelo modelo = new Modelo();
        Controlador controlador = new Controlador(vista,modelo);
    }
}
