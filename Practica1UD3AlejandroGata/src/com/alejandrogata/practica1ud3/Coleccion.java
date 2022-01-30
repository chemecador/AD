package com.alejandrogata.practica1ud3;

import javax.persistence.*;
import java.util.Objects;

/***
 * Clase Colección
 */
@Entity
public class Coleccion {
    private int idcoleccion;
    private int cantidad;
    private double valor;
    private Comprador comprador;

    @Id
    @Column(name = "idcoleccion")
    public int getIdcoleccion() {
        return idcoleccion;
    }

    public void setIdcoleccion(int idcoleccion) {
        this.idcoleccion = idcoleccion;
    }

    @Basic
    @Column(name = "cantidad")
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Basic
    @Column(name = "valor")
    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coleccion coleccion = (Coleccion) o;
        return idcoleccion == coleccion.idcoleccion &&
                cantidad == coleccion.cantidad &&
                Double.compare(coleccion.valor, valor) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idcoleccion, cantidad, valor);
    }

    @OneToOne(mappedBy = "coleccion")
    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }
}
