package com.practicaUD4.base;

import org.bson.types.ObjectId;

import java.time.LocalDate;

/**
 * @author
 * Clase Fruta
 */
public class Sede {
    //Campos
    private ObjectId id;
    private String nombre;
    private double mediaClientes;
    private LocalDate fechaCreacion;

    /**
     * Constructor Sede() vacio
     */
    public Sede() {
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

    public double getMediaClientes() {
        return mediaClientes;
    }

    public void setMediaClientes(double mediaClientes) {
        this.mediaClientes = mediaClientes;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + " - Media de clientes al día: " + mediaClientes + " - Fecha de creación: " + fechaCreacion;
    }
}
