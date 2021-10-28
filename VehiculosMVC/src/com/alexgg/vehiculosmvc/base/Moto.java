package com.alexgg.vehiculosmvc.base;

import java.time.LocalDate;

public class Moto extends Vehiculo {
    private double kms;

    public Moto() {

    }

    public Moto(String matricula, String marca, String modelo, LocalDate fechaMatriculacion, double kms) {
        super(matricula, marca, modelo, fechaMatriculacion);
        this.kms = kms;
    }

    @Override
    public String toString() {
        return "Coche{" +
                "matricula=" + getMatricula() +
                "marca=" + getMarca() +
                "modelo= " + getModelo() +
                '}';
    }
}
