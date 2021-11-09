package com.alexgg.practica1ud1.base;

import java.time.LocalDate;

/**
 * Clase Maqueta, que hereda de Producto.
 */
public class Puzzle extends Producto {
    //atributos
    private int numPiezas;

    //constructor
    public Puzzle() {
        super();
    }

    //sobrecarga del constructor
    public Puzzle(Double precio, String marca, int id, LocalDate fechaProduccion, int numPiezas) {
        super(precio, marca, id, fechaProduccion);
        this.numPiezas = numPiezas;
    }

    //getters y setters
    public int getNumPiezas() {
        return numPiezas;
    }

    public void setNumPiezas(int numPiezas) {
        this.numPiezas = numPiezas;
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
