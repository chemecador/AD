package com.alejandrogata.practica1ud3;

import javax.persistence.*;
import java.util.Objects;

/***
 * Clase Dependiente
 */
@Entity
public class Dependiente {
    private int iddependiente;
    private String nombre;
    private String apellidos;
    private Tienda tienda;

    @Id
    @Column(name = "iddependiente")
    public int getIddependiente() {
        return iddependiente;
    }

    public void setIddependiente(int iddependiente) {
        this.iddependiente = iddependiente;
    }

    @Basic
    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "apellidos")
    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dependiente that = (Dependiente) o;
        return iddependiente == that.iddependiente &&
                Objects.equals(nombre, that.nombre) &&
                Objects.equals(apellidos, that.apellidos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iddependiente, nombre, apellidos);
    }

    @ManyToOne
    @JoinColumn(name = "iddependiente", referencedColumnName = "iddependiente", nullable = false)
    public Tienda getTienda() {
        return tienda;
    }

    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }
}
