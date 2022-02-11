package com.elenajif.vehiculosHibernate1Elena;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "coches")
public class Coche implements Serializable{

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "matricula")
    private String matricula;

    @Column(name = "marca")
    private String marca;

    @Column(name = "modelo")
    private String modelo;

    @Column(name = "fecha_matriculacion")
    private Timestamp fechaMatriculacion;

    @ManyToOne
    @JoinColumn(name = "id_propietario")
    private Propietario propietario;

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Timestamp getFechaMatriculacion() {
        return fechaMatriculacion;
    }

    public void setFechaMatriculacion(Timestamp fechaMatriculacion) {
        this.fechaMatriculacion = fechaMatriculacion;
    }


    public Coche() {
    }

    public Coche(String matricula, String marca, String modelo, Timestamp fechaMatriculacion) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.fechaMatriculacion = fechaMatriculacion;
    }

    @Override
    public String toString() {
        return matricula + "-" + marca;
    }
}