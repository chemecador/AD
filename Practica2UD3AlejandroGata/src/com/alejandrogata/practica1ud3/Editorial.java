package com.alejandrogata.practica1ud3;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/***
 * Clase Editorial
 */
@Entity
public class Editorial {
    private int ideditorial;
    private String editorial;
    private String email;
    private String telefono;
    private Integer antiguedad;
    private String reputacion;
    private String web;
    private List<Puzzle> puzzles;

    @Id
    @Column(name = "ideditorial")
    public int getIdeditorial() {
        return ideditorial;
    }

    public void setIdeditorial(int ideditorial) {
        this.ideditorial = ideditorial;
    }

    @Basic
    @Column(name = "editorial")
    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "telefono")
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Basic
    @Column(name = "antiguedad")
    public Integer getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(Integer antiguedad) {
        this.antiguedad = antiguedad;
    }

    @Basic
    @Column(name = "reputacion")
    public String getReputacion() {
        return reputacion;
    }

    public void setReputacion(String reputacion) {
        this.reputacion = reputacion;
    }

    @Basic
    @Column(name = "web")
    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Editorial editorial1 = (Editorial) o;
        return ideditorial == editorial1.ideditorial &&
                Objects.equals(editorial, editorial1.editorial) &&
                Objects.equals(email, editorial1.email) &&
                Objects.equals(telefono, editorial1.telefono) &&
                Objects.equals(antiguedad, editorial1.antiguedad) &&
                Objects.equals(reputacion, editorial1.reputacion) &&
                Objects.equals(web, editorial1.web);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ideditorial, editorial, email, telefono, antiguedad, reputacion, web);
    }

    @OneToMany(mappedBy = "editorial")
    public List<Puzzle> getPuzzles() {
        return puzzles;
    }

    public void setPuzzles(List<Puzzle> puzzles) {
        this.puzzles = puzzles;
    }
}
