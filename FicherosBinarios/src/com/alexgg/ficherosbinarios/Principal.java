package com.alexgg.ficherosbinarios;

import java.io.*;
import java.util.ArrayList;

public class Principal {
    public static void main(String[] args) {

        FileOutputStream fichero = null;
        ObjectOutputStream serializador = null;
        try {

            ArrayList<Producto> listaProductos = null;

            Producto producto1 = new Producto("Producto1", 1);
            Producto producto2 = new Producto("Producto2", 2);
            Producto producto3 = new Producto("Producto3", 3);

            listaProductos = new ArrayList<Producto>();
            listaProductos.add(producto1);
            listaProductos.add(producto2);
            listaProductos.add(producto3);

            Serializar se = new Serializar();
            se.escribirObjeto(listaProductos);
            listaProductos = (ArrayList<Producto>) se.leerObjeto();


            fichero = new FileOutputStream("archivo.dat");
            serializador = new ObjectOutputStream(fichero);
            serializador.writeObject(listaProductos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
