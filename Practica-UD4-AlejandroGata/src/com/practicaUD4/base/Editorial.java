package com.practicaUD4.base;

import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @author
 * Clase Editorial
 */
public class Editorial {
    //Campos
    private ObjectId id;
    private String nombre;
    private Sede sede;
    private double mediaVentas;
    private LocalDate fechaFundacion;

    /**
     * Constructor Editorial() vacio
     */
    public Editorial() {
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
     * Set getSede()
     * @return sede de tipo Sede
     */
    public Sede getSede() {
        return sede;
    }
    /**
     * Set setSede()
     * @param sede de tipo Sede
     */
    public void setSede(Sede sede) {
        this.sede = sede;
    }

    /**
     * Set getMediaVentas()
     * @return mediaVentas de tipo double
     */
    public double getMediaVentas() {
        return mediaVentas;
    }

    /**
     * Set setMediaVentas()
     * @param mediaVentas de tipo double
     */
    public void setMediaVentas(double mediaVentas) {
        this.mediaVentas = mediaVentas;
    }

    public LocalDate getFechaFundacion() {
        return fechaFundacion;
    }

    public void setFechaFundacion(LocalDate fechaFundacion) {
        this.fechaFundacion = fechaFundacion;
    }

    /**
     * Método toString() de tipo String
     * @return nombre
     * @return direccion
     */


    @Override
    public String toString() {
        return "Nombre:" + nombre + " -  Sede : "  + sede + " - Media de ventas al mes:" + mediaVentas + " - Fecha de fundación: " + fechaFundacion ;
    }
}
