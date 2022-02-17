package com.gimnasio.hibernate.clases;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "instructores", schema = "gymhibernate", catalog = "")
public class Instructores {
    private int idinstructor;
    private String nombre;
    private String apellidos;
    private Date fechanacimiento;
    private String codigoInstructor;
    private List<Socios> socios;
    private List<Actividades> actividades;
    // No sé...
    // int idActividad;

    /**
     * Getter instructor
     * @return instructor
     */
    @Id
    @Column(name = "idinstructor")
    public int getIdinstructor() {
        return idinstructor;
    }

    public void setIdinstructor(int idinstructor) {
        this.idinstructor = idinstructor;
    }

/*
    @Basic
    @Column(name = "idActividad")
    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }
 */
    /**
     * Getter nombre
     * @return nombre
     */
    @Basic
    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Getter apellidos
     * @return apellidos
     */
    @Basic
    @Column(name = "apellidos")
    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Getter fecha nacimiento
     * @return date fecha nacimiento
     */
    @Basic
    @Column(name = "fechanacimiento")
    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    /**
     * Getter codigoInstructor
     * @return codigoInstructor
     */
    @Basic
    @Column(name = "codigoInstructor")
    public String getCodigoInstructor() {
        return codigoInstructor;
    }

    public void setCodigoInstructor(String codigoInstructor) {
        this.codigoInstructor = codigoInstructor;
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
        Instructores that = (Instructores) o;
        return idinstructor == that.idinstructor &&
                Objects.equals(nombre, that.nombre) &&
                Objects.equals(apellidos, that.apellidos) &&
                Objects.equals(fechanacimiento, that.fechanacimiento) &&
                Objects.equals(codigoInstructor, that.codigoInstructor);
    }

    /**
     * Hashea el objeto
     * @return hasheo
     */
    @Override
    public int hashCode() {
        return Objects.hash(idinstructor, nombre, apellidos, fechanacimiento, codigoInstructor);
    }

    /**
     * Relacion uno a muchos, pues un instructor tiene muchos socios
     * @return lista de socios
     */
    @OneToMany(mappedBy = "instructor")
    public List<Socios> getSocios() {
        return socios;
    }

    public void setSocios(List<Socios> socios) {
        this.socios = socios;
    }

    /**
     * Relacion muchos a muchos pues muchas actividades dependen de muchos instructores
     * @return lista de actividades
     */
    @ManyToMany
    @JoinTable(name = "instructores_actividades", catalog = "", schema = "gymhibernate", joinColumns = @JoinColumn(name = "id_instructor", referencedColumnName = "idinstructor"), inverseJoinColumns = @JoinColumn(name = "id_actividad", referencedColumnName = "idactividad"))
    public List<Actividades> getActividades() {
        return actividades;
    }

    public void setActividades(List<Actividades> actividades) {
        this.actividades = actividades;
    }

    @Override
    public String toString() {
        return nombre + " - " + apellidos + " - " + codigoInstructor;
    }
}
