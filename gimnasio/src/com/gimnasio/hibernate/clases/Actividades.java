package com.gimnasio.hibernate.clases;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "actividades", schema = "gymhibernate", catalog = "")
public class Actividades {
    private int idactividad;
    private String titulo;
    private String instalacion;
    private int horasSemanales;
    private double precio;
    private List<DetalleActividad> materiales;
    private List<Instructores> instructores;

    /**
     * Getter id actividad
     * @return id actividad
     */
    @Id
    @Column(name = "idactividad")
    public int getIdactividad() {
        return idactividad;
    }

    public void setIdactividad(int idactividad) {
        this.idactividad = idactividad;
    }

    /**
     * Getter titulo
     * @return titulo
     */
    @Basic
    @Column(name = "titulo")
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Getter instalacion de la actividad
     * @return la instalacion
     */
    @Basic
    @Column(name = "instalacion")
    public String getInstalacion() {
        return instalacion;
    }

    public void setInstalacion(String instalacion) {
        this.instalacion = instalacion;
    }

    /**
     * Getter h. semanales
     * @return h. semanales
     */
    @Basic
    @Column(name = "horasSemanales")
    public int getHorasSemanales() {
        return horasSemanales;
    }

    public void setHorasSemanales(int horasSemanales) {
        this.horasSemanales = horasSemanales;
    }

    @Basic
    @Column(name = "precio")
    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
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
        Actividades that = (Actividades) o;
        return idactividad == that.idactividad &&
                horasSemanales == that.horasSemanales &&
                Double.compare(that.precio, precio) == 0 &&
                Objects.equals(titulo, that.titulo) &&
                Objects.equals(instalacion, that.instalacion);
    }

    /**
     * Deuvelve el hash  del objeto
     * @return hash del objeto
     */
    @Override
    public int hashCode() {
        return Objects.hash(idactividad, titulo, instalacion, horasSemanales, precio);
    }

    /**
     * Relacion Una a varias, pues una actividad tiene varios materiales
     * Devuelve la lista de materiales
     * @return lista de materiales
     */
    @OneToMany(mappedBy = "actividad")
    public List<DetalleActividad> getMateriales() {
        return materiales;
    }

    /**
     * Setter de materiales
     * @param materiales
     */
    public void setMateriales(List<DetalleActividad> materiales) {
        this.materiales = materiales;
    }

    /**
     * Relacion varias a varias, pues muchas actividades pueden tener muchos instructores
     * @return lista de instructores
     */
    @ManyToMany(mappedBy = "actividades")
    public List<Instructores> getInstructores() {
        return instructores;
    }

    /**
     * Setter de instructores
     * @param instructores lista de instructores
     */
    public void setInstructores(List<Instructores> instructores) {
        this.instructores = instructores;
    }

    @Override
    public String toString() {
        return titulo + " - " + instalacion + " - " + horasSemanales + "h - " + precio+"€";
    }
}
