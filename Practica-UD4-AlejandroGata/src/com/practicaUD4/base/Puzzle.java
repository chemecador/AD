package com.practicaUD4.base;

import org.bson.types.ObjectId;

import java.time.LocalDate;
/**
 * @author
 * Clase Puzzle
 */
public class Puzzle {
    //Campos
    private ObjectId id;
    private String nombre;
    private String marca;
    private double precio;
    private LocalDate fechaFabricacion;
    /**
     * Constructor Puzzle() vacio
     */
    public Puzzle() {

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
    public double getPrecio() {
        return precio;
    }
    /**
     * Set setPesoNeto()
     * @param pesoNeto de tipo double
     */
    public void setPrecio(double pesoNeto) {
        this.precio = pesoNeto;
    }
    /**
     * Get getFechaFabricacion() de LocalDate
     * @return fechaFabricacion
     */
    public LocalDate getFechaFabricacion() {
        return fechaFabricacion;
    }
    /**
     * Set setFechaFabricacion()
     * @param fechaFabricacion de tipo LocalDate
     */
    public void setFechaFabricacion(LocalDate fechaFabricacion) {
        this.fechaFabricacion = fechaFabricacion;
    }
    /**
     * Get getMarca() de tipo String
     * @return marca
     */
    public String getMarca() {
        return marca;
    }
    /**
     * Set setMarca()
     * @param marca de tipo String
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }
    /**
     * Método toString() de tipo String
     * @return nombre
     * @return marca
     */

    @Override
    public String toString() {
        return "Nombre: " + nombre + " - Marca: " + marca  + " - Precio: " + precio + " - Fecha de fabricación: " + fechaFabricacion;
    }
}
