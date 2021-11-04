package com.alexgg.practica1ud1.base;

import java.time.LocalDate;

public class Puzzle extends Producto {
    private int numPiezas;

    public Puzzle() {
        super();
    }

    public Puzzle(Double precio, String marca, int id, LocalDate fechaProduccion, int numPiezas) {
        super(precio, marca, id, fechaProduccion);
        this.numPiezas = numPiezas;
    }

    @Override
    public String toString() {
        return "Puzzle{" +
                "precio=" + precio +
                ", marca='" + marca + '\'' +
                ", id=" + id +
                ", fechaProduccion=" + fechaProduccion +
                '}';
    }
}
