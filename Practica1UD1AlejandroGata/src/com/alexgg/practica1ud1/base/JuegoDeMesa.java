package com.alexgg.practica1ud1.base;

import java.time.LocalDate;

public class JuegoDeMesa extends Producto {
    private int numJugadores;

    public JuegoDeMesa() {
        super();
    }

    public JuegoDeMesa(Double precio, String marca, int id, LocalDate fechaProduccion, int numJugadores) {
        super(precio, marca, id, fechaProduccion);
        this.numJugadores = numJugadores;
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
