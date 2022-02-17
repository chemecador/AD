package com.gimnasio.hibernate.clases;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "gerentes", schema = "gymhibernate", catalog = "")
public class Gerentes {
    private int id;
    private String nombre;
    private String apellidos;
    private String direccion;
    private String codigoGerente;
    private List<SociosGerentes> administra;
    private List<Proveedores> proveedores;

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter de primary key de gerente
     * @return id de gerente
     */
    @Id
    @Column(name = "idgerente")
    public int getId() {
        return id;
    }

    /**
     * Getter de nombre
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
     * Getter de apellidos
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
     * Getter direccion
     * @return direccion gerente
     */
    @Basic
    @Column(name = "direccion")
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Getter codigo del gerente
     * @return el dcoigo
     */
    @Basic
    @Column(name = "codigoGerente")
    public String getCodigoGerente() {
        return codigoGerente;
    }

    public void setCodigoGerente(String codigoGerente) {
        this.codigoGerente = codigoGerente;
    }

    /**
     * Relacion una a muchas pues un gerente tiene muchos clientes
     * @return la lista que administra
     */
    @OneToMany(mappedBy = "gerente")
    public List<SociosGerentes> getAdministra() {
        return administra;
    }

    /**
     * Setter administraciones
     * @param administra
     */
    public void setAdministra(List<SociosGerentes> administra) {
        this.administra = administra;
    }

    /**
     * Relacion muchas a muchas pues muchos gerentes peuden tener muchos proveedores
     * @return
     */
    @ManyToMany
    @JoinTable(name = "proveedores_gerentes", catalog = "", schema = "gymhibernate", joinColumns = @JoinColumn(name = "id_gerente", referencedColumnName = "idgerente"), inverseJoinColumns = @JoinColumn(name = "id_proveedor", referencedColumnName = "idproveedor"))
    public List<Proveedores> getProveedores() {
        return proveedores;
    }

    /**
     * * Setter proveedores
     * @param proveedores proveedores lista
     */
    public void setProveedores(List<Proveedores> proveedores) {
        this.proveedores = proveedores;
    }

}
