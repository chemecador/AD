package com.alejandrogata.practica2ud3;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Comprador {
    private int idcomprador;
    private String nombre;
    private String apellidos;
    private String dni;
    private List<Tienda> tiendas;
    private List<Puzzle> puzzles;

    @Id
    @Column(name = "idcomprador")
    public int getIdcomprador() {
        return idcomprador;
    }

    public void setIdcomprador(int idcomprador) {
        this.idcomprador = idcomprador;
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

    @Basic
    @Column(name = "dni")
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comprador comprador = (Comprador) o;
        return idcomprador == comprador.idcomprador &&
                Objects.equals(nombre, comprador.nombre) &&
                Objects.equals(apellidos, comprador.apellidos) &&
                Objects.equals(dni, comprador.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idcomprador, nombre, apellidos, dni);
    }

    @ManyToMany
    @JoinTable(name = "comprador_tienda", catalog = "", schema = "tiendapuzzles", joinColumns = @JoinColumn(name = "idcomprador", referencedColumnName = "idcomprador"), inverseJoinColumns = @JoinColumn(name = "idtienda", referencedColumnName = "idtienda"))
    public List<Tienda> getTiendas() {
        return tiendas;
    }

    public void setTiendas(List<Tienda> tiendas) {
        this.tiendas = tiendas;
    }

    @ManyToMany
    @JoinTable(name = "comprador_puzzle", catalog = "", schema = "tiendapuzzles", joinColumns = @JoinColumn(name = "idcomprador", referencedColumnName = "idcomprador"), inverseJoinColumns = @JoinColumn(name = "idpuzzle", referencedColumnName = "idpuzzle"))
    public List<Puzzle> getPuzzles() {
        return puzzles;
    }

    public void setPuzzles(List<Puzzle> puzzles) {
        this.puzzles = puzzles;
    }
}
