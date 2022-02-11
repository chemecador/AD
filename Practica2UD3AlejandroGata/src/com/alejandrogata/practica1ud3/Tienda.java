package com.alejandrogata.practica1ud3;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/***
 * Clase Tienda
 */
@Entity
public class Tienda {
    private int idtienda;
    private String nombre;
    private String direccion;
    private String telefono;
    private List<Comprador> compradores;
    private List<Puzzle> puzzles;

    @Id
    @Column(name = "idtienda")
    public int getIdtienda() {
        return idtienda;
    }

    public void setIdtienda(int idtienda) {
        this.idtienda = idtienda;
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
    @Column(name = "direccion")
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Basic
    @Column(name = "telefono")
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tienda tienda = (Tienda) o;
        return idtienda == tienda.idtienda &&
                Objects.equals(nombre, tienda.nombre) &&
                Objects.equals(direccion, tienda.direccion) &&
                Objects.equals(telefono, tienda.telefono);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idtienda, nombre, direccion, telefono);
    }

    @ManyToMany(mappedBy = "tiendas")
    public List<Comprador> getCompradores() {
        return compradores;
    }

    public void setCompradores(List<Comprador> compradores) {
        this.compradores = compradores;
    }

    @ManyToMany
    @JoinTable(name = "puzzle_tienda", catalog = "", schema = "tiendapuzzles", joinColumns = @JoinColumn(name = "idtienda", referencedColumnName = "idtienda"), inverseJoinColumns = @JoinColumn(name = "idtienda", referencedColumnName = "idtienda"))
    public List<Puzzle> getPuzzles() {
        return puzzles;
    }

    public void setPuzzles(List<Puzzle> puzzles) {
        this.puzzles = puzzles;
    }
}
