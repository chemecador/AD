package com.gimnasio.hibernate.clases;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "material_proveedor", schema = "gymhibernate", catalog = "")
public class DetalleProveedor {
    private Date fechaPedido;
    private int cantidad;
    private int id;
    private Material material;
    private Proveedores proveedor;

    /**
     * Getter fecha_pedido
     * @return la fecha del pedido
     */
    @Basic
    @Column(name = "fecha_pedido")
    public Date getFechaPedido() {
        return fechaPedido;
    }

    /**
     * Setter materiales
     */
    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    /**
     * Getter cantidad
     * @return cantiad de material
     */
    @Basic
    @Column(name = "cantidad")
    public int getCantidad() {
        return cantidad;
    }

    /**
    * Setter cantidad
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Devuelve id con get
     * @return id
     */
    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Permite ver si un objeto por parametro es igual a detalleProveedor
     * @param o el objeto a ver
     * @return true si es igual
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetalleProveedor that = (DetalleProveedor) o;
        return cantidad == that.cantidad &&
                id == that.id &&
                Objects.equals(fechaPedido, that.fechaPedido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fechaPedido, cantidad, id);
    }

    /**
     * Relacion muchas a una pues varios proveedores pueden proveer un mismo material
     * @return
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
     * Relacion muchas a una pues muchos materiales pueden venir de un mismo proveedor
     * @return
     */
    @ManyToOne
    @JoinColumn(name = "id_proveedor", referencedColumnName = "idproveedor")
    public Proveedores getProveedor() {
        return proveedor;
    }

    /**
     * Setter de proveedor
     * @param proveedor
     */
    public void setProveedor(Proveedores proveedor) {
        this.proveedor = proveedor;
    }

    @Override
    public String toString() {
        return  "Pedido número: " + id + " - " + cantidad + " " + material.getNombre() + " - Proveedor: " + proveedor.getTipo();
    }
}
