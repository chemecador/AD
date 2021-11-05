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

    public int getNumFiguras() {
        return numFiguras;
    }

    public void setNumFiguras(int numFiguras) {
        this.numFiguras = numFiguras;
    }

    @Override
    public String toString() {
        return "Puzzle{" +
                "precio=" + getPrecio() +
                ", marca='" + getMarca() + '\'' +
                ", id=" + getId() +
                ", fechaProduccion=" + getFechaProduccion() +
                '}';
    }
}
