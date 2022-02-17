package com.alejandrogata.practica2ud3;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "comprador_puzzle", schema = "tiendapuzzles", catalog = "")
public class CompradorPuzzle {
    private int idcompradorpuzzle;

    @Id
    @Column(name = "idcompradorpuzzle")
    public int getIdcompradorpuzzle() {
        return idcompradorpuzzle;
    }

    public void setIdcompradorpuzzle(int idcompradorpuzzle) {
        this.idcompradorpuzzle = idcompradorpuzzle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompradorPuzzle that = (CompradorPuzzle) o;
        return idcompradorpuzzle == that.idcompradorpuzzle;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idcompradorpuzzle);
    }
}
