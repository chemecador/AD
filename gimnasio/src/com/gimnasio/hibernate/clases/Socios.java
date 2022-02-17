package com.gimnasio.hibernate.clases;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "Socios", schema = "gymhibernate", catalog = "")
public class Socios {
    private int idsocio;
    private String nombre;
    private String apellidos;
    private String dni;
    private Date fechanacimiento;
    private String tarifa;
    private Instructores instructor;
    private List<SociosGerentes> administraciones;

    public void setIdSocio(int id) {
        this.idsocio = id;
    }

    @Id
    @Column(name = "idsocio")
    public int getIdSocio() {
        return idsocio;
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
     * @param nombre
     */
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

    /**
     * Setter apellidos
     * @param apellidos
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Getter dni
     * @return dni
     */
    @Basic
    @Column(name = "dni")
    public String getDni() {
        return dni;
    }

    /**
     * Setter dni
     * @param dni
     */
    public void setDni(String dni) {
        this.dni = dni;
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
     * Getter tarifa
     * @return tarifa
     */
    @Basic
    @Column(name = "tarifa")
    public String getTarifa() {
        return tarifa;
    }

    public void setTarifa(String tarifa) {
        this.tarifa = tarifa;
    }

    /**
     * Relacion muchas a una pues muchos socios dependen de un instructor
     * @return instructor
     */
    @ManyToOne
    @JoinColumn(name = "idInstructor", referencedColumnName = "idinstructor")
    public Instructores getInstructor() {
        return instructor;
    }

    /**
     * Setter instructor
     * @param instructor
     */
    public void setInstructor(Instructores instructor) {
        this.instructor = instructor;
    }

    /**
     * Relacion uno a varios pues un socio podrá ser gestionado por varios gerentes
     * @return lista de gerentes
     */
    @OneToMany(mappedBy = "socio")
    public List<SociosGerentes> getAdministraciones() {
        return administraciones;
    }

    /**
     * Setter Administración
     * @param administraciones
     */
    public void setAdministraciones(List<SociosGerentes> administraciones) {
        this.administraciones = administraciones;
    }

    @Override
    public String toString() {
        return nombre + " - " + apellidos + " - " + dni + " - " + fechanacimiento + " - " + tarifa + " - " +instructor.getCodigoInstructor();
    }
}
