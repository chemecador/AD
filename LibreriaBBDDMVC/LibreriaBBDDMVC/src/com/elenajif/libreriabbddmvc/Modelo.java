package com.elenajif.libreriabbddmvc;

import javax.swing.*;
import java.sql.*;
import java.time.LocalDateTime;

/**
 * Created by PROFESOR on 15/11/2018.
 */
public class Modelo {
    private Connection conexion;

    public void conectar()  {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/libreria", "root", "");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void desconectar() throws SQLException {
        conexion.close();
    }

    public void insertar(String isbn, String titulo, String autor, LocalDateTime fechaPublicacion) throws SQLException {
        String consulta = "INSERT INTO libros(isbn, titulo, autor, fecha_publicacion) VALUES(?,?,?,?)";
        PreparedStatement sentencia = null;

        sentencia = conexion.prepareStatement(consulta);
        sentencia.setString(1, isbn);
        sentencia.setString(2, titulo);
        sentencia.setString(3, autor);
        sentencia.setTimestamp(4, Timestamp.valueOf(fechaPublicacion));

        sentencia.executeUpdate();

        if( sentencia == null ){
            sentencia.close();
        }
    }

    public void eliminar(String isbn) throws SQLException {
        String consulta = "DELETE FROM libros WHERE isbn = ?";
        PreparedStatement sentencia = null;

        sentencia = conexion.prepareStatement(consulta);
        sentencia.setString(1, isbn);
        sentencia.executeUpdate();

        if( sentencia == null ){
            sentencia.close();
        }
    }

    public ResultSet consultarLibros() throws SQLException {
        String consulta = "SELECT * FROM libros";
        PreparedStatement sentencia = null;
        ResultSet resultado = null;

        sentencia = conexion.prepareStatement(consulta);
        resultado = sentencia.executeQuery();


        return resultado;
    }
}
