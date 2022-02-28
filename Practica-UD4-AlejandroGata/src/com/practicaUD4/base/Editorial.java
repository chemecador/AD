package com.practicaUD4.base;

import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @author
 * Clase Pescado
 */
public class Editorial {
    //Campos
    private ObjectId id;
    private String nombre;
    private String direccion;
    private ArrayList<Puzzle> puzzles;
    private double mediaVentas;
    private LocalDate fechaCreacion;

    /**
     * Coonstructor Pescado() vacio
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
     * Get getDireccion() de tipo String
     * @return direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Set setDireccion()
     * @param direccion de tipo String
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    /**
     * Get getMediaVentas() de tipo double
     * @return MediaVentas
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

    public ArrayList<Puzzle> getPuzzles() {
        return puzzles;
    }

    public void setPuzzles(ArrayList<Puzzle> puzzles) {
        this.puzzles = puzzles;
    }

    /**
     * Método toString() de tipo String
     * @return nombre
     * @return direccion
     */
    @Override
    public String toString() {
        return  nombre + ":" + direccion;
    }
}
