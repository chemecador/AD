package examenUD1.base;

import java.time.LocalDate;

public class Ordenador {
    private String nombre;
    private String cantidad;
    private String precio;
    private LocalDate fechaOferta;

    public Ordenador(){

    }

    public Ordenador(String nombre, String cantidad, String precio, LocalDate fechaOferta) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.fechaOferta = fechaOferta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public LocalDate getFechaOferta() {
        return fechaOferta;
    }

    public void setFechaOferta(LocalDate fechaOferta) {
        this.fechaOferta = fechaOferta;
    }
}
