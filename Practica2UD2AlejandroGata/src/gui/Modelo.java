package gui;

import javax.swing.*;
import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.Properties;

/**
 * Created by DAM on 13/12/2021.
 */
public class Modelo {
    private String ip;
    private String user;
    private String password;
    private String adminPassword;

    public String getIp() {
        return ip;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public Modelo() {
        getPropValues();
    }

    private Connection conexion;

    boolean conectar() {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://"
                    + ip + ":3307/tiendapuzzles", user, password);
            return true;
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null,"Puedes crearla desde el menú opciones",
                    "No se ha encontrado la base de datos",JOptionPane.PLAIN_MESSAGE);
            return false;
        }
    }
    boolean crearBBDD(){
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://"
                    + ip + ":3307/", user, password);
            PreparedStatement statement = null;

            String code = leerFichero();
            String[] query = code.split("--");
            for (String aQuery : query) {
                statement = conexion.prepareStatement(aQuery);
                statement.executeUpdate();
            }
            assert statement != null;
            statement.close();
            JOptionPane.showMessageDialog(null, "Base de datos creada correctamente",
                    "tiendapuzzles creada", JOptionPane.PLAIN_MESSAGE);
            return true;
        } catch (SQLException | IOException e1) {
            e1.printStackTrace();
        }
        return false;
    }
    boolean borrarBBDD(){

        String sentenciaSql = "DROP DATABASE tiendapuzzles";
        PreparedStatement sentencia = null;

        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.execute();
            JOptionPane.showMessageDialog(null, "Base de datos eliminada correctamente",
                    "tiendapuzzles eliminada", JOptionPane.PLAIN_MESSAGE);
            return true;
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
        return false;
    }
    private String leerFichero() throws IOException {
        //basedatos_java no tiene delimitador
        //StringBuilder es dinamica
        try (BufferedReader reader = new BufferedReader(new FileReader("basedatos_java.sql"))) {
            String linea;
            StringBuilder stringBuilder = new StringBuilder();
            while ((linea = reader.readLine()) != null) {
                stringBuilder.append(linea);
                stringBuilder.append(" ");
            }
            return stringBuilder.toString();
        }
    }

    void desconectar() {
        try {
            conexion.close();
            conexion = null;
        } catch (SQLException e) {
            System.out.println("No se puede desconectar");
        }
    }

    void insertarComprador(String nombre, String apellidos, String dni, LocalDate fechaCompra, String pais) {
        String sentenciaSql = "INSERT INTO compradores (nombre, apellidos, dni, fechacompra, pais)" +
                "VALUES (?,?,?,?,?)";

        PreparedStatement sentencia = null;
        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setString(1, nombre);
            sentencia.setString(2, apellidos);
            sentencia.setString(3, dni);
            sentencia.setDate(4, Date.valueOf(fechaCompra));
            sentencia.setString(5, pais);
            sentencia.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    void insertarEditorial(String editorial, String email, String telefono, int antiguedad, String reputacion, String web) {
        String sentenciaSql = "INSERT INTO editoriales (editorial, email, telefono, antiguedad, reputacion, web) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement sentencia = null;

        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setString(1, editorial);
            sentencia.setString(2, email);
            sentencia.setString(3, telefono);
            sentencia.setInt(4, antiguedad);
            sentencia.setString(5, reputacion);
            sentencia.setString(6, web);
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

    void insertarPuzzle(String titulo, String isbn, String editorial, String genero, String comprador,
                        float precio, LocalDate fechaEdicion) {
        String sentenciaSql = "INSERT INTO puzzles (titulo, isbn, ideditorial, genero, idcomprador, precio, fechaedicion) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement sentencia = null;

        int ideditorial = Integer.valueOf(editorial.split(" ")[0]);
        int idcomprador = Integer.valueOf(comprador.split(" ")[0]);

        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setString(1, titulo);
            sentencia.setString(2, isbn);
            sentencia.setInt(3, ideditorial);
            sentencia.setString(4, genero);
            sentencia.setInt(5, idcomprador);
            sentencia.setFloat(6, precio);
            sentencia.setDate(7, Date.valueOf(fechaEdicion));
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

    void modificarComprador(String nombre, String apellidos, String dni, LocalDate fechaCompra, String pais, int idcomprador) {
        String sentenciaSql = "UPDATE compradores SET nombre=?,apellidos=?,dni=?,fechacompra=?,pais=?" +
                "WHERE idcomprador=?";
        PreparedStatement sentencia = null;
        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setString(1, nombre);
            sentencia.setString(2, apellidos);
            sentencia.setString(3, dni);
            sentencia.setDate(4, Date.valueOf(fechaCompra));
            sentencia.setString(5, pais);
            sentencia.setInt(6, idcomprador);
            sentencia.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (sentencia != null)
                try {
                    sentencia.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
        }
    }

    void modificarEditorial(String editorial, String email, String telefono, int antiguedad, String reputacion, String web, int ideditorial) {

        String sentenciaSql = "UPDATE editoriales SET editorial = ?, email = ?, telefono = ?, antiguedad = ?, reputacion = ?, web = ?" +
                "WHERE ideditorial = ?";
        PreparedStatement sentencia = null;

        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setString(1, editorial);
            sentencia.setString(2, email);
            sentencia.setString(3, telefono);
            sentencia.setInt(4, antiguedad);
            sentencia.setString(5, reputacion);
            sentencia.setString(6, web);
            sentencia.setInt(7, ideditorial);
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

    void modificarPuzzle(String titulo, String isbn, String editorial, String genero, String comprador,
                         float precio, LocalDate fechaEdicion, int idpuzzle) {

        String sentenciaSql = "UPDATE puzzles SET titulo = ?, isbn = ?, ideditorial = ?, genero = ?, " +
                "idcomprador = ?, precio = ?, fechaedicion = ? WHERE idpuzzle = ?";
        PreparedStatement sentencia = null;

        int ideditorial = Integer.valueOf(editorial.split(" ")[0]);
        int idcomprador = Integer.valueOf(comprador.split(" ")[0]);

        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setString(1, titulo);
            sentencia.setString(2, isbn);
            sentencia.setInt(3, ideditorial);
            sentencia.setString(4, genero);
            sentencia.setInt(5, idcomprador);
            sentencia.setFloat(6, precio);
            sentencia.setDate(7, Date.valueOf(fechaEdicion));
            sentencia.setInt(8, idpuzzle);
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

    ResultSet buscarPuzzle(String isbn) {
        try {
            String consulta = "SELECT * FROM puzzles WHERE isbn = " + isbn;
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            return sentencia.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    ResultSet buscarComprador(String dni) {
        try {
            String consulta = "SELECT * FROM compradores WHERE dni = " + dni;
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            return sentencia.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    ResultSet buscarEditorial(String nombre) {
        try {
            String consulta = "SELECT * FROM editoriales WHERE nombre = " + nombre;
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            return sentencia.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    void borrarEditorial(int ideditorial) {
        String sentenciaSql = "DELETE FROM editoriales WHERE ideditorial=?";
        PreparedStatement sentencia = null;
        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setInt(1, ideditorial);
            sentencia.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (sentencia != null)
                try {
                    sentencia.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
        }
    }

    void borrarComprador(int idcomprador) {
        String sentenciaSql = "DELETE FROM compradores WHERE idcomprador = ?";
        PreparedStatement sentencia = null;

        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setInt(1, idcomprador);
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

    void borrarPuzzle(int idpuzzle) {
        String sentenciaSql = "DELETE FROM puzzles WHERE idpuzzle = ?";
        PreparedStatement sentencia = null;

        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setInt(1, idpuzzle);
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

    ResultSet consultarEditorial() throws SQLException {
        String sentenciaSql = "SELECT concat(ideditorial) AS 'ID', concat(editorial) AS 'Nombre editorial', " +
                "concat(email) AS 'email', concat(telefono) AS 'Teléfono'," +
                "concat(antiguedad) AS 'Antigüedad', concat(reputacion) AS 'Reputacion'," +
                "concat(web) AS 'Web' FROM editoriales";
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        sentencia = conexion.prepareStatement(sentenciaSql);
        resultado = sentencia.executeQuery();
        return resultado;
    }

    ResultSet consultarComprador() throws SQLException {
        String sentenciaSql = "SELECT concat(idcomprador) AS 'ID', concat(nombre) AS 'Nombre', concat(apellidos) AS 'Apellidos', " +
                "concat(dni) AS 'DNI', " +
                "concat(fechacompra) AS 'Fecha de compra', concat(pais) AS 'País de origen' FROM compradores";
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        sentencia = conexion.prepareStatement(sentenciaSql);
        resultado = sentencia.executeQuery();
        return resultado;
    }

    ResultSet consultarPuzzles() throws SQLException {
        String sentenciaSql = "SELECT concat(b.idpuzzle) AS 'ID', concat(b.titulo) AS 'Título', concat(b.isbn) AS 'ISBN', " +
                "concat(e.ideditorial, ' - ', e.editorial) AS 'Editorial', concat(b.genero) AS 'Género', " +
                "concat(a.idcomprador, ' - ', a.apellidos, ', ', a.nombre) AS 'Comprador', " +
                "concat(b.precio) AS 'Precio', concat(b.fechaedicion) AS 'Fecha de edición'" +
                " FROM puzzles AS b " +
                "INNER JOIN editoriales AS e ON e.ideditorial = b.ideditorial INNER JOIN " +
                "compradores AS a ON a.idcomprador = b.idcomprador";
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        sentencia = conexion.prepareStatement(sentenciaSql);
        resultado = sentencia.executeQuery();
        return resultado;
    }

    //usamos los datos del cuadro de dialogo
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
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null)
                try {
                    inputStream.close();
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.ip = ip;
        this.user = user;
        this.password = pass;
        this.adminPassword = adminPass;
    }

    //comprobaciones llamando a funciones de sql
    public boolean puzzleIsbnYaExiste(String isbn) {
        String consulta = "SELECT existeIsbn(?)";
        PreparedStatement function;
        boolean isbnExists = false;
        try {
            function = conexion.prepareStatement(consulta);
            function.setString(1, isbn);
            ResultSet rs = function.executeQuery();
            rs.next();
            isbnExists = rs.getBoolean(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isbnExists;
    }

    public boolean editorialNombreYaExiste(String nombre) {
        String editorialNameConsult = "SELECT existeNombreEditorial(?)";
        PreparedStatement function;
        boolean nameExists = false;
        try {
            function = conexion.prepareStatement(editorialNameConsult);
            function.setString(1, nombre);
            ResultSet rs = function.executeQuery();
            rs.next();

            nameExists = rs.getBoolean(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nameExists;
    }

    public boolean compradorNombreYaExiste(String nombre, String apellidos) {
        String completeName = apellidos + ", " + nombre;
        String authorNameConsult = "SELECT existeNombreComprador(?)";
        PreparedStatement function;
        boolean nameExists = false;
        try {
            function = conexion.prepareStatement(authorNameConsult);
            function.setString(1, completeName);
            ResultSet rs = function.executeQuery();
            rs.next();

            nameExists = rs.getBoolean(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nameExists;
    }


}
