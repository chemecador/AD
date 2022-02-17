package com.gimnasio.hibernate.clases;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "socios_gerentes", schema = "gymhibernate", catalog = "")
public class SociosGerentes {
    private int id;
    private Date fechaAlta;
    private Socios socio;
    private Gerentes gerente;

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter id de la tabla
     * @return id
     */
    @Id
    public int getId() {
        return id;
    }

    /**
     * Getter fecha_alta
     * @return fecha alta
     */
    @Basic
    @Column(name = "fecha_alta")
    public Date getFechaAlta() {
        return fechaAlta;
    }

    /**
     * Setter fecha alta
     * @param fechaAlta date del alta
     */
    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    /**
     * Permite ver si un objeto por parametro es igual a SociosGerentes
     * @param o el objeto a ver
     * @return true si es igual
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SociosGerentes that = (SociosGerentes) o;
        return Objects.equals(fechaAlta, that.fechaAlta);
    }

    /**
     * Hashea el objeto SociosGerentes
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(fechaAlta);
    }

    /**
     * Relacion muchas a una, donde se realizarán
     * muchas administraciones para un socio
     * @return lista de socios
     */
    @ManyToOne
    @JoinColumn(name = "id_socio", referencedColumnName = "idsocio")
    public Socios getSocio() {
        return socio;
    }

    /**
     * Setter socio
     * @param socio
     */
    public void setSocio(Socios socio) {
        this.socio = socio;
    }

    /**
     * Relacion de mapeo mucha a una, donde se realizarán
     * muchas administraciones por un gerente.
     * @return el gerente
     */
    @ManyToOne
    @JoinColumn(name = "id_gerente", referencedColumnName = "idgerente")
    public Gerentes getGerente() {
        return gerente;
    }

    public void setGerente(Gerentes gerente) {
        this.gerente = gerente;
    }

}
