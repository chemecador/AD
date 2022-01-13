package gui;

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

    void conectar() {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://"
                    + ip + ":3307/libreria", user, password);
        } catch (SQLException e) {
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
            } catch (SQLException | IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
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
            e.printStackTrace();
        }
    }

    void insertarAutor(String nombre, String apellidos, LocalDate fechaNacimiento, String pais) {
        String sentenciaSql = "INSERT INTO autores (nombre, apellidos, fechanacimiento, pais)" +
                "VALUES (?,?,?,?)";

        PreparedStatement sentencia = null;
        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setString(1, nombre);
            sentencia.setString(2, apellidos);
            sentencia.setDate(3, Date.valueOf(fechaNacimiento));
            sentencia.setString(4, pais);
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

    void insertarEditorial(String editorial, String email, String telefono, String tipoEditorial, String web) {
        String sentenciaSql = "INSERT INTO editoriales (editorial, email, telefono, tipoeditorial, web) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement sentencia = null;

        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setString(1, editorial);
            sentencia.setString(2, email);
            sentencia.setString(3, telefono);
            sentencia.setString(4, tipoEditorial);
            sentencia.setString(5, web);
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

    void insertarLibro(String titulo, String isbn, String editorial, String genero, String autor,
                       float precio, LocalDate fechaLanzamiento) {
        String sentenciaSql = "INSERT INTO libros (titulo, isbn, ideditorial, genero, idautor, precio, fechalanzamiento) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement sentencia = null;

        int ideditorial = Integer.valueOf(editorial.split(" ")[0]);
        int idautor = Integer.valueOf(autor.split(" ")[0]);

        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setString(1, titulo);
            sentencia.setString(2, isbn);
            sentencia.setInt(3, ideditorial);
            sentencia.setString(4, genero);
            sentencia.setInt(5, idautor);
            sentencia.setFloat(6, precio);
            sentencia.setDate(7, Date.valueOf(fechaLanzamiento));
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

    void modificarAutor(String nombre, String apellidos, LocalDate fechanacimiento, String pais, int idautor) {
        String sentenciaSql = "UPDATE autores SET nombre=?,apellidos=?,fechanacimiento=?,pais=?" +
                "WHERE idautor=?";
        PreparedStatement sentencia = null;
        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setString(1, nombre);
            sentencia.setString(2, apellidos);
            sentencia.setDate(3, Date.valueOf(fechanacimiento));
            sentencia.setString(4, pais);
            sentencia.setInt(5, idautor);
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

    void modificarEditorial(String editorial, String email, String telefono, String tipoEditorial, String web, int ideditorial) {

        String sentenciaSql = "UPDATE editoriales SET editorial = ?, email = ?, telefono = ?, tipoeditorial = ?, web = ?" +
                "WHERE ideditorial = ?";
        PreparedStatement sentencia = null;

        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setString(1, editorial);
            sentencia.setString(2, email);
            sentencia.setString(3, telefono);
            sentencia.setString(4, tipoEditorial);
            sentencia.setString(5, web);
            sentencia.setInt(6, ideditorial);
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

    void modificarLibro(String titulo, String isbn, String editorial, String genero, String autor,
                        float precio, LocalDate fechalanzamiento, int idlibro) {

        String sentenciaSql = "UPDATE libros SET titulo = ?, isbn = ?, ideditorial = ?, genero = ?, " +
                "idautor = ?, precio = ?, fechalanzamiento = ? WHERE idlibro = ?";
        PreparedStatement sentencia = null;

        int ideditorial = Integer.valueOf(editorial.split(" ")[0]);
        int idautor = Integer.valueOf(autor.split(" ")[0]);

        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setString(1, titulo);
            sentencia.setString(2, isbn);
            sentencia.setInt(3, ideditorial);
            sentencia.setString(4, genero);
            sentencia.setInt(5, idautor);
            sentencia.setFloat(6, precio);
            sentencia.setDate(7, Date.valueOf(fechalanzamiento));
            sentencia.setInt(8, idlibro);
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

    void borrarAutor(int idautor) {
        String sentenciaSql = "DELETE FROM autores WHERE idautor = ?";
        PreparedStatement sentencia = null;

        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setInt(1, idautor);
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

    void borrarLibro(int idlibro) {
        String sentenciaSql = "DELETE FROM libros WHERE idlibro = ?";
        PreparedStatement sentencia = null;

        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setInt(1, idlibro);
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
                "concat(email) AS 'email', concat(telefono) AS 'Teléfono',concat(tipoeditorial) AS 'Tipo'," +
                "concat(web) AS 'Web' FROM editoriales";
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        sentencia = conexion.prepareStatement(sentenciaSql);
        resultado = sentencia.executeQuery();
        return resultado;
    }

    ResultSet consultarAutor() throws SQLException {
        String sentenciaSql = "SELECT concat(idautor) AS 'ID', concat(nombre) AS 'Nombre', concat(apellidos) AS 'Apellidos', " +
                "concat(fechanacimiento) AS 'Fecha de nacimiento', concat(pais) AS 'País de origen' FROM autores";
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        sentencia = conexion.prepareStatement(sentenciaSql);
        resultado = sentencia.executeQuery();
        return resultado;
    }

    ResultSet consultarLibros() throws SQLException {
        String sentenciaSql = "SELECT concat(b.idlibro) AS 'ID', concat(b.titulo) AS 'Título', concat(b.isbn) AS 'ISBN', " +
                "concat(e.ideditorial, ' - ', e.editorial) AS 'Editorial', concat(b.genero) AS 'Género', " +
                "concat(a.idautor, ' - ', a.apellidos, ', ', a.nombre) AS 'Autor', " +
                "concat(b.precio) AS 'Precio', concat(b.fechalanzamiento) AS 'Fecha de publicación'" +
                " FROM libros AS b " +
                "INNER JOIN editoriales AS e ON e.ideditorial = b.ideditorial INNER JOIN " +
                "autores AS a ON a.idautor = b.idautor";
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
        this.ip=ip;
        this.user=user;
        this.password=pass;
        this.adminPassword=adminPass;
    }

    //comprobaciones llamando a funciones de sql
    public boolean libroIsbnYaExiste(String isbn) {
        String consulta="SELECT existeIsbn(?)";
        PreparedStatement function;
        boolean isbnExists=false;
        try {
            function=conexion.prepareStatement(consulta);
            function.setString(1,isbn);
            ResultSet rs =function.executeQuery();
            rs.next();
            isbnExists=rs.getBoolean(1);
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
    public boolean autorNombreYaExiste(String nombre, String apellidos) {
        String completeName = apellidos + ", " + nombre;
        String authorNameConsult = "SELECT existeNombreAutor(?)";
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
