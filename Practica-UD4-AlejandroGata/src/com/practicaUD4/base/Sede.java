package com.practicaUD4.base;

import org.bson.types.ObjectId;

/**
 * @author
 * Clase Fruta
 */
public class Sede {
    //Campos
    private ObjectId id;
    private String nombre;
    /**
     * Constructor Fruta() vacio
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


    @Override
    public String toString() {
        return nombre + " : " ;
    }
}
