package com.gimnasio.hibernate.clases;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "actividades_materiales", schema = "gymhibernate", catalog = "")
public class DetalleActividad {
    private int id;
    private String cantidad;
    private Material material;
    private Actividades actividad;

    /**
     * getter id
     * @return
     */
    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    /**
     * Setter id
     * @param id id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * getter cantiad
     * @return cantidad
     */
    @Basic
    @Column(name = "cantidad")
    public String getCantidad() {
        return cantidad;
    }


    /**
     * Setter cantidad
     * @param cantidad cantidad
     */
    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Permite ver si un objeto por parametro es igual a detalle actividad
     * @param o el objeto a ver
     * @return true si es igual
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetalleActividad that = (DetalleActividad) o;
        return id == that.id &&
                Objects.equals(cantidad, that.cantidad);
    }

    /**
     * HASHEA el objeto detalleactividad
     * @return el hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, cantidad);
    }

    /**
     * Relacion mucha a una, pues un mismo material no puede estar en varias actividades
     * @return  materiales
     */
    @ManyToOne
    @JoinColumn(name = "id_material", referencedColumnName = "idmaterial")
    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    /**
     * Relacion muchas a una, pues un mismo material no puede estar en varias actividades
     * @return actividades
     */
    @ManyToOne
    @JoinColumn(name = "id_actividad", referencedColumnName = "idactividad", nullable = false)
    public Actividades getActividad() {
        return actividad;
    }


    /**
     * Seter de actividades
     * @param actividad
     */
    public void setActividad(Actividades actividad) {
        this.actividad = actividad;
    }


}
