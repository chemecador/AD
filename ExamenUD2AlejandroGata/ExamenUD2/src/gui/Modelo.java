package gui;

import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.Properties;

public class Modelo {
    private String ip;
    private String user;
    private String password;
    private String adminPassword;
    private Connection conexion;

    public Modelo() {
        getPropValues();
    }

    String getIP() {
        return ip;
    }

    String getUser() {
        return user;
    }

    String getPassword() {
        return password;
    }

    String getAdminPassword() {
        return adminPassword;
    }


    void conectar() {
        try {
            conexion = DriverManager.getConnection(
                    "jdbc:mysql://" + ip + ":3307/gymbbdd", user, password);
            System.out.println("Conectado");
        } catch (SQLException sqle) {
            try {
                conexion = DriverManager.getConnection(
                        "jdbc:mysql://" + ip + ":3307/", user, password);

                PreparedStatement statement = null;

                String code = leerFichero();
                String[] query = code.split("--");
                for (String aQuery : query) {
                    statement = conexion.prepareStatement(aQuery);
                    statement.executeUpdate();
                }
                assert statement != null;
                statement.close();

            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String leerFichero() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("baseDatos_java.sql"));
        String linea;
        StringBuilder stringBuilder = new StringBuilder();
        while ((linea = reader.readLine()) != null) {
            stringBuilder.append(linea);
            stringBuilder.append(" ");
        }

        return stringBuilder.toString();
    }

    void desconectar() {
        try {
            conexion.close();
            conexion = null;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    void insertarSocio(String nombre, String apellidos, String dni, LocalDate fechaNacimiento, String tarifa,
                       String instructor, String actividades) {
        String sentenciaSql = "INSERT INTO socios (nombre, apellidos, dni, fechanacimiento, tarifa, idActividad, idInstructor)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement sentencia = null;

        int idActividades = Integer.valueOf(actividades.split(" ")[0]);
        int idInstructor = Integer.valueOf(instructor.split(" ")[0]);
        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setString(1, nombre);
            sentencia.setString(2, apellidos);
            sentencia.setString(3, dni);
            sentencia.setDate(4, Date.valueOf(fechaNacimiento));
            sentencia.setString(5, tarifa);
            sentencia.setInt(6, idActividades);
            sentencia.setInt(7, idInstructor);

            sentencia.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (sentencia != null)
                try {
                    sentencia.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
        }
    }

    void insertarInstructor(String nombre, String apellidos, LocalDate fechaNacimiento, String codigo) {
        String sentenciaSql = "INSERT INTO instructores (nombre, apellidos, fechanacimiento, codigoInstructor)" +
                " VALUES (?, ?, ?, ?)";
        PreparedStatement sentencia = null;

        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setString(1, nombre);
            sentencia.setString(2, apellidos);
            sentencia.setDate(3, Date.valueOf(fechaNacimiento));
            sentencia.setString(4, codigo);

            sentencia.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (sentencia != null)
                try {
                    sentencia.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
        }
    }

    void insertarActividad(String titulo, String instalacion, int horasSemanales, float precio) {
        String sentenciaSql = "INSERT INTO actividades (titulo, instalacion, horasSemanales, precio)" +
                " VALUES (?, ?, ?, ?)";
        PreparedStatement sentencia = null;

        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setString(1, titulo);
            sentencia.setString(2, instalacion);
            sentencia.setInt(3, horasSemanales);
            sentencia.setFloat(4, precio);

            sentencia.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (sentencia != null)
                try {
                    sentencia.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
        }
    }

    void eliminarSocio(int idSocio) {
        String sentenciaSql = "DELETE FROM socios WHERE idsocio = ?";
        PreparedStatement sentencia = null;

        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setInt(1, idSocio);
            sentencia.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (sentencia != null)
                try {
                    sentencia.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
        }
    }

    void eliminarInstructor(int idInstructor) {
        String sentenciaSql = "DELETE FROM instructores WHERE idinstructor = ?";
        PreparedStatement sentencia = null;

        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setInt(1, idInstructor);
            sentencia.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (sentencia != null)
                try {
                    sentencia.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
        }
    }

    void eliminarActividad(int idActividad) {
        String sentenciaSql = "DELETE FROM actividades WHERE idactividad = ?";
        PreparedStatement sentencia = null;

        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setInt(1, idActividad);
            sentencia.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (sentencia != null)
                try {
                    sentencia.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
        }
    }

    void modificarSocio(String nombre, String apellidos, String dni, LocalDate fechaNacimiento, String tarifa,
                        String instructor, String actividades, int idsocio) {
        String sentenciaSql = "UPDATE socios SET nombre = ?, apellidos = ?, dni = ?, fechanacimiento = ?," +
                "tarifa = ?, idActividad = ?, idInstructor = ? WHERE idsocio = ?";

        int idActividad = Integer.valueOf(actividades.split(" ")[0]);
        int idInstructor = Integer.valueOf(instructor.split(" ")[0]);
        PreparedStatement sentencia = null;
        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setString(1, nombre);
            sentencia.setString(2, apellidos);
            sentencia.setString(3, dni);
            sentencia.setDate(4, Date.valueOf(fechaNacimiento));
            sentencia.setString(5, tarifa);
            sentencia.setInt(6, idActividad);
            sentencia.setInt(7, idInstructor);
            sentencia.setInt(8, idsocio);

            sentencia.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (sentencia != null)
                try {
                    sentencia.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
        }
    }

    void modificarInstructor(String nombre, String apellidos, LocalDate fechaNacimiento, String codigo, int codigoInstructor) {
        String sentenciaSql = "UPDATE instructores SET nombre = ?, apellidos = ?, fechanacimiento = ?," +
                "codigoInstructor = ? WHERE idInstructor = ?";
        PreparedStatement sentencia = null;
        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setString(1, nombre);
            sentencia.setString(2, apellidos);
            sentencia.setDate(3, Date.valueOf(fechaNacimiento));
            sentencia.setString(4, codigo);
            sentencia.setInt(5, codigoInstructor);

            sentencia.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (sentencia != null)
                try {
                    sentencia.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
        }
    }

    void modificarActividad(String titulo, String instalacion, int horasSemanales, float precio, int idactividad) {
        String sentenciaSql = "UPDATE actividades SET titulo = ?, instalacion = ?, horasSemanales = ?," +
                "precio = ? WHERE idactividad = ?";
        PreparedStatement sentencia = null;
        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setString(1, titulo);
            sentencia.setString(2, instalacion);
            sentencia.setInt(3, horasSemanales);
            sentencia.setFloat(4, precio);
            sentencia.setInt(5, idactividad);

            sentencia.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (sentencia != null)
                try {
                    sentencia.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
        }
    }

    ResultSet consultarSocios() throws SQLException {
        String sentenciaSql = "SELECT concat(s.idsocio) as 'ID', concat(s.nombre) as 'Nombre', concat(s.apellidos) as 'Apellidos'," +
                "concat(s.dni) as 'DNI' , concat(s.fechanacimiento) as 'Fecha de Nacimiento', concat(s.tarifa) as 'Tarifa'," +
                "concat (i.idInstructor, ' - ', i.nombre, ' ', i.apellidos, ' - ',i.codigoInstructor) as 'Código Instructor'," +
                "concat (a.idActividad, ' - ', a.titulo, ' - ', a.precio, '€') as 'Actividad'" +
                "FROM socios s " +
                "inner join instructores as i on i.idinstructor = s.idInstructor " +
                "inner join actividades as a on a.idactividad = s.idActividad";
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        sentencia = conexion.prepareStatement(sentenciaSql);
        resultado = sentencia.executeQuery();
        return resultado;
    }


    ResultSet consultarInstructores() throws SQLException {
        String sentenciaSql = "SELECT concat(idinstructor) as 'ID', concat(nombre) as 'Nombre', concat(apellidos) as 'Apellidos', " +
                "concat(fechanacimiento) as 'Fecha de Nacimiento', concat(codigoInstructor) as 'Código' FROM instructores";
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        sentencia = conexion.prepareStatement(sentenciaSql);
        resultado = sentencia.executeQuery();
        return resultado;
    }

    ResultSet consultarActividades() throws SQLException {
        String sentenciaSql = "SELECT concat(idactividad) as 'ID', concat(titulo) as 'Titulo', concat(instalacion) as 'Instalación', concat(horasSemanales) as 'Horas Semanales', " +
                "concat(precio) as 'Precio de Actividad' FROM actividades";
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        sentencia = conexion.prepareStatement(sentenciaSql);
        resultado = sentencia.executeQuery();
        return resultado;
    }


    public ResultSet cargarDniSocio(String dni) throws SQLException {
        String sentenciaSql = "SELECT concat(s.idsocio) as 'ID', concat(s.nombre) as 'Nombre', concat(s.apellidos) as 'Apellidos'," +
                "concat(s.dni) as 'DNI' , concat(s.fechanacimiento) as 'Fecha de Nacimiento', concat(s.tarifa) as 'Tarifa'," +
                "concat (i.idInstructor, ' - ', i.codigoInstructor) as 'Código Instructor'," +
                "concat (a.idActividad, ' - ', a.titulo) as 'Actividad'" +
                "FROM socios s " +
                "inner join instructores as i on i.idinstructor = s.idInstructor " +
                "inner join actividades as a on a.idactividad = s.idActividad " +
                "WHERE dni = ?";
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        sentencia = conexion.prepareStatement(sentenciaSql);
        sentencia.setString(1, dni);
        resultado = sentencia.executeQuery();
        return resultado;
    }

    public boolean existeDniSocio(String dni) {
        String dniSocio = "SELECT existeDniSocio(?)";
        PreparedStatement function;
        boolean dniExists = false;
        byte resultado = 0;
        try {
            function = conexion.prepareStatement(dniSocio);
            function.setString(1, dni);
            ResultSet rs = function.executeQuery();
            rs.next();

            resultado = rs.getByte(1);

            if (resultado == 1) {
                dniExists = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dniExists;
    }

    public boolean existeCodigoInstructor(String codigoInstructor) {
        String codIns = "SELECT existeCodigoInstructor(?)";
        PreparedStatement function;
        boolean dniExists = false;
        byte resultado = 0;
        try {
            function = conexion.prepareStatement(codIns);
            function.setString(1, codigoInstructor);
            ResultSet rs = function.executeQuery();
            rs.next();

            resultado = rs.getByte(1);

            if (resultado == 1) {
                dniExists = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dniExists;
    }

    private void getPropValues() {
        InputStream inputStream = null;
        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";

            inputStream = new FileInputStream(propFileName);

            prop.load(inputStream);
            ip = prop.getProperty("ip");
            user = prop.getProperty("user");
            password = prop.getProperty("pass");
            adminPassword = prop.getProperty("admin");

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            try {
                if (inputStream != null) inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void setPropValues(String ip, String user, String pass, String adminPass) {
        try {
            Properties prop = new Properties();
            prop.setProperty("ip", ip);
            prop.setProperty("user", user);
            prop.setProperty("pass", pass);
            prop.setProperty("admin", adminPass);
            OutputStream out = new FileOutputStream("config.properties");
            prop.store(out, null);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.ip = ip;
        this.user = user;
        this.password = pass;
        this.adminPassword = adminPass;
    }

    public ResultSet filtrarSocio(String DNI) {
        try {
            String consulta = "SELECT * FROM socios WHERE dni = ?";
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, DNI);
            return sentencia.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
