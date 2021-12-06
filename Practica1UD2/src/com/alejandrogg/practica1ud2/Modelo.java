package com.alejandrogg.practica1ud2;

import java.sql.*;
import java.time.LocalDateTime;

public class Modelo {

    private Connection conexion;

    public void conectar() throws SQLException {
        conexion = null;
        conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/tiendapuzzles",
                "root", "");
    }

    public void desconectar() throws SQLException {
        conexion.close();
        conexion = null;
    }

    public ResultSet obtenerDatos() throws SQLException {
        if (conexion == null || conexion.isClosed()) {
            return null;
        }
        String consulta = "SELECT * FROM productos";
        PreparedStatement sentencia = conexion.prepareStatement(consulta);
        return sentencia.executeQuery();
    }

    public int insertarProducto(Double precio, String marca, LocalDateTime fechaProduccion) throws SQLException {
        if (conexion == null) {
            return -1;
        }
        if (conexion.isClosed()) {
            return -2;
        }
        String consulta = "INSERT INTO productos(precio, marca, fecha_produccion)" +
                "VALUES (?,?,?)";
        PreparedStatement sentencia = null;

        sentencia = conexion.prepareStatement(consulta);
        sentencia.setDouble(1, precio);
        sentencia.setString(2, marca);
        sentencia.setTimestamp(3, Timestamp.valueOf(fechaProduccion));

        int numeroRegistros = sentencia.executeUpdate();

        if (sentencia != null) {
            sentencia.close();
        }

        return numeroRegistros;
    }

    public int eliminarProducto(int id) throws SQLException {
        if (conexion == null) {
            return -1;
        }
        if (conexion.isClosed()) {
            return -2;
        }
        String consulta = "DELETE FROM productos WHERE id=?";
        PreparedStatement sentencia = conexion.prepareStatement(consulta);
        sentencia.setInt(1, id);
        int numRegistros = sentencia.executeUpdate();
        if (sentencia != null) {
            conexion.close();
        }
        return numRegistros;
    }

    public int modificarProducto(int id, Double precio, String marca, Timestamp fecha) throws SQLException {
        if (conexion == null) {
            return -1;
        }
        if (conexion.isClosed()) {
            return -2;
        }
        String consulta = "UPDATE productos SET precio=?,marca=?," +
                "fecha_produccion=? WHERE id=?";
        PreparedStatement sentencia = conexion.prepareStatement(consulta);
        sentencia.setDouble(1, precio);
        sentencia.setString(2, marca);
        sentencia.setTimestamp(3, fecha);
        sentencia.setInt(4, id);
        int numRegistros = sentencia.executeUpdate();
        if (sentencia != null) {
            conexion.close();
        }
        return numRegistros;
    }
}
