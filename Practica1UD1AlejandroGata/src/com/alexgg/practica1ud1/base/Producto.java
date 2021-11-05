package com.alexgg.practica1ud1.base;


import java.time.LocalDate;

public abstract class Producto {
    private Double precio;
    private String marca;
    private int id;
    private LocalDate fechaProduccion;

    public Producto() {
    }

    public Producto(Double precio, String marca, int id, LocalDate fechaProduccion) {
        this.precio = precio;
        this.marca = marca;
        this.id = id;
        this.fechaProduccion = fechaProduccion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFechaProduccion() {
        return fechaProduccion;
    }

    public void setFechaProduccion(LocalDate fechaProduccion) {
        this.fechaProduccion = fechaProduccion;
    }
}
