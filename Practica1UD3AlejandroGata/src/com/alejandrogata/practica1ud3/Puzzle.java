package com.alejandrogata.practica1ud3;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

/***
 * Clase Puzzle
 */
@Entity
public class Puzzle {
    private int idpuzzle;
    private String titulo;
    private String isbn;
    private String genero;
    private double precio;
    private Date fechaedicion;
    private List<Comprador> compradores;
    private List<Tienda> tiendas;
    private Editorial editorial;

    @Id
    @Column(name = "idpuzzle")
    public int getIdpuzzle() {
        return idpuzzle;
    }

    public void setIdpuzzle(int idpuzzle) {
        this.idpuzzle = idpuzzle;
    }

    @Basic
    @Column(name = "titulo")
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Basic
    @Column(name = "isbn")
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Basic
    @Column(name = "genero")
    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Basic
    @Column(name = "precio")
    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Basic
    @Column(name = "fechaedicion")
    public Date getFechaedicion() {
        return fechaedicion;
    }

    public void setFechaedicion(Date fechaedicion) {
        this.fechaedicion = fechaedicion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Puzzle puzzle = (Puzzle) o;
        return idpuzzle == puzzle.idpuzzle &&
                Double.compare(puzzle.precio, precio) == 0 &&
                Objects.equals(titulo, puzzle.titulo) &&
                Objects.equals(isbn, puzzle.isbn) &&
                Objects.equals(genero, puzzle.genero) &&
                Objects.equals(fechaedicion, puzzle.fechaedicion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idpuzzle, titulo, isbn, genero, precio, fechaedicion);
    }

    @ManyToMany
    @JoinTable(name = "comprador_puzzle", catalog = "", schema = "tiendapuzzles", joinColumns = @JoinColumn(name = "idcomprador", referencedColumnName = "idcomprador", nullable = false), inverseJoinColumns = @JoinColumn(name = "idcomprador", referencedColumnName = "idcomprador", nullable = false))
    public List<Comprador> getCompradores() {
        return compradores;
    }

    public void setCompradores(List<Comprador> compradores) {
        this.compradores = compradores;
    }

    @ManyToMany(mappedBy = "puzzles")
    public List<Tienda> getTiendas() {
        return tiendas;
    }

    public void setTiendas(List<Tienda> tiendas) {
        this.tiendas = tiendas;
    }

    @ManyToOne
    @JoinColumn(name = "ideditorial", referencedColumnName = "ideditorial")
    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }
}
