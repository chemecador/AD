package com.alexgg.practica1ud1.base;

import java.time.LocalDate;

public class Maqueta extends Producto {
    private int numFiguras;

    public Maqueta() {
        super();
    }

    public Maqueta(Double precio, String marca, int id, LocalDate fechaProduccion, int numFiguras) {
        super(precio, marca, id, fechaProduccion);
        this.numFiguras = numFiguras;
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
