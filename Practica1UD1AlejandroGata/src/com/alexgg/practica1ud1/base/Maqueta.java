package com.alexgg.practica1ud1.base;

import java.time.LocalDate;

/**
 * Clase Maqueta, que hereda de Producto.
 */
public class Maqueta extends Producto {
    private int numFiguras;

    //constructor
    public Maqueta() {
        super();
    }
    //sobrecarga del constructor
    public Maqueta(Double precio, String marca, int id, LocalDate fechaProduccion, int numFiguras) {
        super(precio, marca, id, fechaProduccion);
        this.numFiguras = numFiguras;
    }
    //getters y setters
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
