package com.gimnasio.hibernate.clases;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "proveedores", schema = "gymhibernate", catalog = "")
public class Proveedores {
    private int idproveedor;
    private String tipo;
    private String direccion;
    private int telefono;
    private String fax;
    private List<Gerentes> gerentes;
    private List<DetalleProveedor> detalles;

    /**
     * Getter idproveedor
     * @return idproveedor
     */
    @Id
    @Column(name = "idproveedor")
    public int getIdproveedor() {
        return idproveedor;
    }

    /**
     * Setter idproveedor
     * @param idproveedor
     */
    public void setIdproveedor(int idproveedor) {
        this.idproveedor = idproveedor;
    }

    /**
     * Getter tipo
     * @return tipo
     */
    @Basic
    @Column(name = "tipo")
    public String getTipo() {
        return tipo;
    }

    /**
     * Setter tipo
     * @param tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Getter direccion
     * @return direccion
     */
    @Basic
    @Column(name = "direccion")
    public String getDireccion() {
        return direccion;
    }

    /**
     * Setter direccion
     * @param direccion
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Getter telefono
     * @return telefono
     */
    @Basic
    @Column(name = "telefono")
    public int getTelefono() {
        return telefono;
    }


    /**
     * Setter telefono
     * @param telefono
     */
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    /**
     * Getter fax
     * @return fax
     */
    @Basic
    @Column(name = "fax")
    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
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
        Proveedores that = (Proveedores) o;
        return idproveedor == that.idproveedor &&
                telefono == that.telefono &&
                Objects.equals(tipo, that.tipo) &&
                Objects.equals(direccion, that.direccion) &&
                Objects.equals(fax, that.fax);
    }

    /**
     * Hashea el objeto proveedores
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(idproveedor, tipo, direccion, telefono, fax);
    }

    /**
     * Relacion de mapeo muchos a muchos pues muchos proveedores pueden ser contratados por muchos gerentes
     * @return lista de gerentes
     */
    @ManyToMany(mappedBy = "proveedores")
    public List<Gerentes> getGerentes() {
        return gerentes;
    }

    public void setGerentes(List<Gerentes> gerentes) {
        this.gerentes = gerentes;
    }

    /**
     * Relación una a varios pues un solo proveedor podrá dar varios materiales
     * @return lista de detalles/materiales
     */
    @OneToMany(mappedBy = "proveedor")
    public List<DetalleProveedor> getDetalles() {
        return detalles;
    }


    /**
     * Setter de detalles materiales
     * @param detalles
     */
    public void setDetalles(List<DetalleProveedor> detalles) {
        this.detalles = detalles;
    }

    @Override
    public String toString() {
        return tipo + " - " + direccion + " - " + telefono + " - " + fax;
    }
}
