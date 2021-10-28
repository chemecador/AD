package com.alexgg.vehiculosmvc.base;

import java.time.LocalDate;

public abstract class Vehiculo {
    private String matricula;
    private String marca;
    private String modelo;
    private LocalDate fechaMatriculacion;

    public Vehiculo() {
    }

    public Vehiculo(String matricula, String marca, String modelo, LocalDate fechaMatriculacion) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.fechaMatriculacion = fechaMatriculacion;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public LocalDate getFechaMatriculacion() {
        return fechaMatriculacion;
    }

    public void setFechaMatriculacion(LocalDate fechaMatriculacion) {
        this.fechaMatriculacion = fechaMatriculacion;
    }
}
