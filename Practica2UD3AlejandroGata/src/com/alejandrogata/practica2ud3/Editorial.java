package com.alejandrogata.practica2ud3;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Editorial {
    private int ideditorial;
    private String nombre;
    private String telefono;
    private List<Puzzle> puzzles;

    @Override
    public String toString() {
        return "Nombre: " + nombre + " | Teléfono: " + telefono;
    }
    @Id
    @Column(name = "ideditorial")
    public int getIdeditorial() {
        return ideditorial;
    }

    public void setIdeditorial(int ideditorial) {
        this.ideditorial = ideditorial;
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
        Editorial editorial = (Editorial) o;
        return ideditorial == editorial.ideditorial &&
                Objects.equals(nombre, editorial.nombre) &&
                Objects.equals(telefono, editorial.telefono);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ideditorial, nombre, telefono);
    }

    @OneToMany(mappedBy = "editorial")
    public List<Puzzle> getPuzzles() {
        return puzzles;
    }

    public void setPuzzles(List<Puzzle> puzzles) {
        this.puzzles = puzzles;
    }
}
