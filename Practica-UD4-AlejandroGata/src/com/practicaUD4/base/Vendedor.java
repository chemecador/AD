package com.practicaUD4.base;

import org.bson.types.ObjectId;

import java.time.LocalDate;
/**
 * @author
 * Clase Fruta
 */
public class Vendedor {
    //Campos
    private ObjectId id;
    private String nombre;
    private String apellidos;
    private double salario;
    private LocalDate fechaNacimiento;
    /**
     * Constructor Fruta() vacio
     */
    public Vendedor() {

    }
    /**
     * Get getId de ObjectId
     * @return id
     */
    public ObjectId getId() {
        return id;
    }
    /**
     * Set setId()
     * @param id de ObjectId
     */
    public void setId(ObjectId id) {
        this.id = id;
    }
    /**
     * Get getNombre() de tipo String
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Set setNombre()
     * @param nombre de tipo String
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Get getPesoNeto() de tipo double
     * @return pesoNeto
     */
    public double getSalario() {
        return salario;
    }
    /**
     * Set setPesoNeto()
     * @param pesoNeto de tipo double
     */
    public void setSalario(double pesoNeto) {
        this.salario = pesoNeto;
    }
    /**
     * Get getfechaNacimiento() de LocalDate
     * @return fechaNacimiento
     */
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }
    /**
     * Set setfechaNacimiento()
     * @param fechaNacimiento de tipo LocalDate
     */
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    /**
     * Get getapellidos() de tipo String
     * @return apellidos
     */
    public String getApellidos() {
        return apellidos;
    }
    /**
     * Set setapellidos()
     * @param apellidos de tipo String
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Método toString() de tipo String
     * @return nombre
     * @return apellidos
     */
    @Override
    public String toString() {
        return nombre + ": " + apellidos ;
    }
}
