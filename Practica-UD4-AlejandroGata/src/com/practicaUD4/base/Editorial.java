package com.practicaUD4.base;

import org.bson.types.ObjectId;

import java.time.LocalDate;
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
    private LocalDate fechaCreacion;

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
    /**
     * Get getFechaCreacion() de LocalDate
     * @return fechaCreacion
     */
    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * Set setfechaCreacion()
     * @param fechaCreacion de tipo LocalDate
     */
    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Método toString() de tipo String
     * @return nombre
     * @return direccion
     */
    @Override
    public String toString() {
        return  nombre + " : "  + sede + " : " + mediaVentas + " : " + fechaCreacion;
    }
}
