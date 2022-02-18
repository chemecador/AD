package com.alejandrogata.practica2ud3;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Puzzle {
    private int idpuzzle;
    private String titulo;
    private String isbn;
    private List<Comprador> compradores;
    private List<VentaPuzzle> ventas;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Puzzle puzzle = (Puzzle) o;
        return idpuzzle == puzzle.idpuzzle &&
                Objects.equals(titulo, puzzle.titulo) &&
                Objects.equals(isbn, puzzle.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idpuzzle, titulo, isbn);
    }

    @ManyToMany(mappedBy = "puzzles")
    public List<Comprador> getCompradores() {
        return compradores;
    }

    public void setCompradores(List<Comprador> compradores) {
        this.compradores = compradores;
    }

    @OneToMany(mappedBy = "puzzle")
    public List<VentaPuzzle> getVentas() {
        return ventas;
    }

    public void setVentas(List<VentaPuzzle> ventas) {
        this.ventas = ventas;
    }

    @ManyToOne
    @JoinColumn(name = "ideditorial", referencedColumnName = "ideditorial")
    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    @Override
    public String toString() {
        return "Título: " + titulo + " | ISBN: " + isbn;
    }
}
