package com.alexgg.practica1ud1.gui;

import com.alexgg.practica1ud1.base.JuegoDeMesa;
import com.alexgg.practica1ud1.base.Maqueta;
import com.alexgg.practica1ud1.base.Producto;
import com.alexgg.practica1ud1.base.Puzzle;

import java.time.LocalDate;
import java.util.ArrayList;

public class VehiculosModelo {
    private ArrayList<Producto> listaProductos;

    public VehiculosModelo() {
        listaProductos = new ArrayList<>();
    }

    public void altaPuzzle(Double precio, String marca, int id, LocalDate fechaProduccion, int numPiezas) {
        Puzzle nuevoPuzzle = new Puzzle(precio, marca, id, fechaProduccion, numPiezas);
        listaProductos.add(nuevoPuzzle);
    }

    public void altaMaqueta(Double precio, String marca, int id, LocalDate fechaProduccion, int numFiguras) {
        Maqueta nuevaMaqueta = new Maqueta(precio, marca, id, fechaProduccion, numFiguras);
        listaProductos.add(nuevaMaqueta);
    }

    public void altaJuegoDeMesa(Double precio, String marca, int id, LocalDate fechaProduccion, int numJugadores) {
        JuegoDeMesa nuevoJuego = new JuegoDeMesa(precio, marca, id, fechaProduccion, numJugadores);
        listaProductos.add(nuevoJuego);
    }
    public boolean existeId(int id) {
        for (Producto unProducto : listaProductos) {
            if (unProducto.getId() == id)
                return true;
        }
        return false;
    }
}
