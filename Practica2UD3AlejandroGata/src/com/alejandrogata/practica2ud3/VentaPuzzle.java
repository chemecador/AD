package com.alejandrogata.practica2ud3;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "puzzle_tienda", schema = "tiendapuzzles", catalog = "")
public class VentaPuzzle {
    private int idpuzzletienda;
    private Integer precio;
    private Puzzle puzzle;
    private Tienda tienda;

    @Override
    public String toString() {
        return "Puzzle: " + puzzle.getTitulo() + " | Tienda: " + tienda.getNombre() + " | Precio: " + precio;
    }

    @Id
    @Column(name = "idpuzzletienda")
    public int getIdpuzzletienda() {
        return idpuzzletienda;
    }

    public void setIdpuzzletienda(int idpuzzletienda) {
        this.idpuzzletienda = idpuzzletienda;
    }

    @Basic
    @Column(name = "precio")
    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VentaPuzzle that = (VentaPuzzle) o;
        return idpuzzletienda == that.idpuzzletienda &&
                Objects.equals(precio, that.precio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idpuzzletienda, precio);
    }

    @ManyToOne
    @JoinColumn(name = "idpuzzle", referencedColumnName = "idpuzzle")
    public Puzzle getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(Puzzle puzzle) {
        this.puzzle = puzzle;
    }

    @ManyToOne
    @JoinColumn(name = "idtienda", referencedColumnName = "idtienda")
    public Tienda getTienda() {
        return tienda;
    }

    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }
}
