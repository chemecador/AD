package com.alejandrogg.vehiculosbbdd;

import java.sql.*;
import java.time.LocalDateTime;

public class Modelo {

    private Connection conexion;

    public void conectar() throws SQLException {
        conexion = null;
        conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/vehiculos",
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
        String consulta = "SELECT * FROM coches";
        PreparedStatement sentencia = conexion.prepareStatement(consulta);
        return sentencia.executeQuery();
    }

    public int insertarVehiculo(String matricula, String marca, String modelo, LocalDateTime fechaMatriculacion) throws SQLException {
        if (conexion==null) {
            return -1;
        }
        if (conexion.isClosed()) {
            return -2;
        }
        String consulta="INSERT INTO coches(matricula,marca, modelo, fecha_matriculacion)"+
                "VALUES (?,?,?,?)";
        PreparedStatement sentencia=null;

        sentencia=conexion.prepareStatement(consulta);
        sentencia.setString(1,matricula);
        sentencia.setString(2,marca);
        sentencia.setString(3, modelo);
        sentencia.setTimestamp(4, Timestamp.valueOf(fechaMatriculacion));

        int numeroRegistros=sentencia.executeUpdate();

        if (sentencia!=null) {
            sentencia.close();
        }

        return numeroRegistros;
    }

    public int eliminarVehiculo(int id) throws SQLException {
        if (conexion == null) {
            return -1;
        }
        if (conexion.isClosed()){
            return -2;
        }
        String consulta = "DELETE FROM coches WHERE id=?";
        PreparedStatement sentencia = conexion.prepareStatement(consulta);
        sentencia.setInt(1,id);
        int numRegistros = sentencia.executeUpdate();
        if (sentencia != null){
            conexion.close();
        }
        return numRegistros;
    }

    public int modificarVehiculo(int id, String matricula, String marca, String modelo, Timestamp fecha) throws SQLException {
        if (conexion == null) {
            return -1;
        }
        if (conexion.isClosed()){
            return -2;
        }
        String consulta = "UPDATE coches SET matricula=?,marca=?,modelo=?," +
                "fecha_matriculacion=? WHERE id=?";
        PreparedStatement sentencia = conexion.prepareStatement(consulta);
        sentencia.setString(1,matricula);
        sentencia.setString(2,marca);
        sentencia.setString(3,modelo);
        sentencia.setTimestamp(4,fecha);
        sentencia.setInt(5,id);
        int numRegistros = sentencia.executeUpdate();
        if (sentencia != null){
            conexion.close();
        }
        return numRegistros;
    }
}
