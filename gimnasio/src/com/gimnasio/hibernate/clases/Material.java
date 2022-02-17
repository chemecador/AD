package com.gimnasio.hibernate.clases;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "material", schema = "gymhibernate", catalog = "")
public class Material {
    private int idmaterial;
    private String nombre;
    private byte disponible;
    private List<DetalleProveedor> proveedores;
    private List<DetalleActividad> actividades;

    /**
     * Getter idMaterial
     * @return idmterial
     */
    @Id
    @Column(name = "idmaterial")
    public int getIdmaterial() {
        return idmaterial;
    }

    /**
     * Setter idmaterial
     * @param idmaterial int id
     */
    public void setIdmaterial(int idmaterial) {
        this.idmaterial = idmaterial;
    }

    /**
     * Getter nombre
     * @return nombre
     */
    @Basic
    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    /**
     * Setter nombre
     * @param nombre nombre string
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Getter disponible
     * @return true/false
     */
    @Basic
    @Column(name = "disponible")
    public byte getDisponible() {
        return disponible;
    }

    public void setDisponible(byte disponible) {
        this.disponible = disponible;
    }

    /**
     * Permite ver si concuerda el objeto Actividades con otro por parametro
     * @param o objeto a igualar
     * @return true si existe
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Material material = (Material) o;
        return idmaterial == material.idmaterial &&
                disponible == material.disponible &&
                Objects.equals(nombre, material.nombre);
    }

    /**
     * Hashea el objeto
     * @return el hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(idmaterial, nombre, disponible);
    }

    /**
     * Mapeo uno a varios pues un material puede ser distribuido por varios proveedores
     * @return lista de proveedores
     */
    @OneToMany(mappedBy = "material")
    public List<DetalleProveedor> getProveedores() {
        return proveedores;
    }

    public void setProveedores(List<DetalleProveedor> proveedores) {
        this.proveedores = proveedores;
    }

    /**
     * Mapeo uno a varios pues los materiales serán utilizados en varias actividades.
     * @return lista de actividades
     */
    @OneToMany(mappedBy = "material")
    public List<DetalleActividad> getActividades() {
        return actividades;
    }

    /**
     * Setter actividades
     * @param actividades actividades
     */
    public void setActividades(List<DetalleActividad> actividades) {
        this.actividades = actividades;
    }

    @Override
    public String toString() {
        if (disponible == 1){
            return nombre + " - " + " disponible(SI)";
        }else{
            return nombre + " - " + " disponible(NO)";
        }
    }
}
