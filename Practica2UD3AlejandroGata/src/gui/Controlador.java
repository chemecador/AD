package gui;

import com.alejandrogata.practica2ud3.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/***
 * Clase controlador
 */
public class Controlador implements ActionListener, ListSelectionListener {
    private Vista vista;
    private Modelo modelo;
    private boolean conectado;

    /***
     * Constructor de la clase
     * @param modelo modelo
     * @param vista vista
     */
    public Controlador(Modelo modelo, Vista vista) {
        this.vista = vista;
        this.modelo = modelo;
        this.conectado = false;

        addActionListeners(this);
        addListSelectionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        String comando = e.getActionCommand();

        if (!conectado && !comando.equalsIgnoreCase("Conectar")) {
            JOptionPane.showMessageDialog(null, "No has conectado con la BBDD",
                    "Error de conexión", JOptionPane.ERROR_MESSAGE);
            return;
        }
        switch (comando) {
            case "Salir":
                modelo.desconectar();
                System.exit(0);
                break;
            case "Conectar":
                vista.conexionItem.setEnabled(false);
                modelo.conectar();
                conectado = true;
                break;

            case "nuevoPuzzleBtn":
                Puzzle nuevoPuzzle = new Puzzle();
                nuevoPuzzle.setTitulo(vista.txtTitulo.getText());
                nuevoPuzzle.setIsbn(vista.txtIsbn.getText());
                nuevoPuzzle.setEditorial((Editorial) vista.comboEditoriales.getSelectedItem());
                modelo.insertar(nuevoPuzzle);
                break;

            case "listarPuzzles":
                listarPuzzles(modelo.getPuzzles());
                break;

            case "modificarPuzzleBtn": {
                Puzzle p = (Puzzle) vista.listPuzzles.getSelectedValue();
                p.setTitulo(vista.txtTitulo.getText());
                p.setIsbn(vista.txtIsbn.getText());
                p.setEditorial((Editorial) vista.comboEditoriales.getSelectedItem());
                modelo.modificar(p);
                break;
            }

            case "eliminarPuzzleBtn": {
                Puzzle p = (Puzzle) vista.listPuzzles.getSelectedValue();
                if (!comprobarPuzzleVenta(p.getIdpuzzle())) {
                    JOptionPane.showMessageDialog(null, "Este puzzle está ligado a una venta, elimina primero la venta.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    break;
                }
                modelo.eliminar(p);
                break;
            }
            case "altaCompradorBtn": {
                Comprador c = new Comprador();
                c.setNombre(vista.txtNombreComprador.getText());
                c.setApellidos(vista.txtApellidos.getText());
                c.setDni(vista.txtDNI.getText());
                modelo.insertar(c);
                break;
            }
            case "modificarCompradorBtn": {
                Comprador c = (Comprador) vista.listCompradores.getSelectedValue();
                c.setNombre(vista.txtNombreComprador.getText());
                c.setApellidos(vista.txtApellidos.getText());
                c.setDni(vista.txtDNI.getText());
                modelo.modificar(c);
            }
            break;
            case "eliminarCompradorBtn": {
                Comprador c = (Comprador) vista.listCompradores.getSelectedValue();
                modelo.eliminar(c);
                break;
            }
            case "altaTiendaBtn": {
                Tienda t = new Tienda();
                t.setNombre(vista.txtNombreTienda.getText());
                t.setTelefono(vista.txtTlfTienda.getText());
                modelo.insertar(t);
                break;
            }
            case "modificarTiendaBtn": {
                Tienda t = (Tienda) vista.listTiendas.getSelectedValue();
                t.setNombre(vista.txtNombreTienda.getText());
                t.setTelefono(vista.txtTlfTienda.getText());
                modelo.modificar(t);
            }
            break;
            case "eliminarTiendaBtn": {
                Tienda t = (Tienda) vista.listTiendas.getSelectedValue();
                if (!comprobarTiendaVenta(t.getIdtienda())) {
                    JOptionPane.showMessageDialog(null, "Esta tienda está ligada a una venta, elimina primero la venta.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    break;
                }
                modelo.eliminar(t);
                break;
            }

            case "altaEditorialBtn": {
                Editorial t = new Editorial();
                t.setNombre(vista.txtNombreEditorial.getText());
                t.setTelefono(vista.txtTlfEditorial.getText());
                modelo.insertar(t);
                break;
            }
            case "modificarEditorialBtn": {
                Editorial t = (Editorial) vista.listEditoriales.getSelectedValue();
                t.setNombre(vista.txtNombreEditorial.getText());
                t.setTelefono(vista.txtTlfEditorial.getText());
                modelo.modificar(t);
            }
            break;
            case "eliminarEditorialBtn": {
                Editorial t = (Editorial) vista.listEditoriales.getSelectedValue();
                modelo.eliminar(t);
                break;
            }
            case "altaVentaBtn": {
                VentaPuzzle v = new VentaPuzzle();
                v.setPrecio(Integer.valueOf(vista.txtPrecio.getText()));
                v.setPuzzle((Puzzle) vista.comboPuzzles.getSelectedItem());
                v.setTienda((Tienda) vista.comboTiendas.getSelectedItem());
                modelo.insertar(v);
                break;
            }
            case "modificarVentaBtn": {
                VentaPuzzle v = (VentaPuzzle) vista.listVentas.getSelectedValue();
                v.setPrecio(Integer.valueOf(vista.txtPrecio.getText()));
                v.setPuzzle((Puzzle) vista.comboPuzzles.getSelectedItem());
                v.setTienda((Tienda) vista.comboTiendas.getSelectedItem());
                modelo.modificar(v);
                break;
            }
            case "eliminarVentaBtn": {
                VentaPuzzle v = (VentaPuzzle) vista.listVentas.getSelectedValue();
                modelo.eliminar(v);
                break;
            }
            case "listarEditorialPuzzlesBtn": {
                Editorial edi = (Editorial) vista.listEditoriales.getSelectedValue();
                listarEditorialPuzzles(modelo.getEditorialPuzzles(edi));
            }
        }
        limpiarCampos();
        actualizar();
    }

    /***
     * Comprobar si un puzzle está en el listado de ventas
     * @param id del puzzle
     * @return true si se puede borrar, false si no
     */
    private boolean comprobarPuzzleVenta(int id) {
        for (VentaPuzzle venta : modelo.getVentas()) {
            Puzzle p = venta.getPuzzle();
            if (p.getIdpuzzle() == id) {
                return false;
            }
        }
        return true;
    }

    /***
     * Comprobar si una tienda está en el listado de ventas
     * @param id de la tienda
     * @return true si se puede borrar, false si no
     */
    private boolean comprobarTiendaVenta(int id) {
        for (VentaPuzzle venta : modelo.getVentas()) {
            Tienda t = venta.getTienda();
            if (t.getIdtienda() == id) {
                return false;
            }
        }
        return true;
    }

    /***
     * Limpiar los campos
     */
    private void limpiarCampos() {
        limpiarCamposPuzzles();
        limpiarCamposCompradores();
        limpiarCamposEditoriales();
        limpiarCamposTiendas();
        limpiarCamposVentas();
    }

    /***
     * Limpiar los campos de las ventas
     */
    private void limpiarCamposVentas() {
        vista.comboPuzzles.setSelectedItem(-1);
        vista.comboTiendas.setSelectedItem(-1);
        vista.txtPrecio.setText("");
    }

    /***
     * Limpiar los campos de las tiendas
     */
    private void limpiarCamposTiendas() {
        vista.txtNombreTienda.setText("");
        vista.txtTlfTienda.setText("");
    }

    /***
     * Limpiar los campos de las editoriales
     */
    private void limpiarCamposEditoriales() {
        vista.txtNombreEditorial.setText("");
        vista.txtTlfEditorial.setText("");
    }

    /***
     * Limpiar los campos de los compradores
     */
    private void limpiarCamposCompradores() {
        vista.txtNombreComprador.setText("");
        vista.txtApellidos.setText("");
        vista.txtDNI.setText("");
    }

    /***
     * Limpiar los campos de los puzzles
     */
    private void limpiarCamposPuzzles() {
        vista.txtTitulo.setText("");
        vista.txtIsbn.setText("");
        vista.comboEditoriales.setSelectedItem(-1);
    }

    /***
     * Actualizar los campos
     */
    private void actualizar() {
        listarPuzzles(modelo.getPuzzles());
        listarCompradores(modelo.getCompradores());
        listarTiendas(modelo.getTiendas());
        listarEditoriales(modelo.getEditoriales());
        listarVentas(modelo.getVentas());
    }

    /***
     * Listar los compradores
     * @param lista de compradores
     */
    public void listarCompradores(ArrayList<Comprador> lista) {
        vista.dlmCompradores.clear();
        for (Comprador c : lista) {
            vista.dlmCompradores.addElement(c);
        }
    }

    /***
     * Listar las tiendas
     * @param lista de tiendas
     */
    private void listarTiendas(ArrayList<Tienda> lista) {
        vista.dlmTiendas.clear();
        for (Tienda t : lista) {
            vista.dlmTiendas.addElement(t);
        }
        vista.comboTiendas.removeAllItems();
        ArrayList<Tienda> tie = modelo.getTiendas();

        for (Tienda t : tie) {
            vista.comboTiendas.addItem(t);
        }
        vista.comboTiendas.setSelectedIndex(-1);
    }

    /***
     * Listar las editoriales
     * @param lista de editoriales
     */
    private void listarEditoriales(ArrayList<Editorial> lista) {
        vista.dlmEditoriales.clear();
        for (Editorial e : lista) {
            vista.dlmEditoriales.addElement(e);
        }
        vista.comboEditoriales.removeAllItems();
        ArrayList<Editorial> ed = modelo.getEditoriales();

        for (Editorial e : ed) {
            vista.comboEditoriales.addItem(e);
        }
        vista.comboEditoriales.setSelectedIndex(-1);
    }

    /***
     * Listar los puzzles
     * @param lista de puzzles
     */
    public void listarPuzzles(ArrayList<Puzzle> lista) {
        vista.dlmPuzzles.clear();
        for (Puzzle unPuzzle : lista) {
            vista.dlmPuzzles.addElement(unPuzzle);
        }
        vista.comboPuzzles.removeAllItems();
        ArrayList<Puzzle> puz = modelo.getPuzzles();

        for (Puzzle p : puz) {
            vista.comboPuzzles.addItem(p);
        }
        vista.comboPuzzles.setSelectedIndex(-1);
    }

    /***
     * Listar las ventas
     * @param ventas lista de ventas
     */
    public void listarVentas(List<VentaPuzzle> ventas) {
        vista.dlmVentas.clear();
        for (VentaPuzzle venta : ventas) {
            vista.dlmVentas.addElement(venta);
        }

        vista.comboPuzzles.removeAllItems();
        ArrayList<Puzzle> p = modelo.getPuzzles();
        vista.comboTiendas.removeAllItems();
        ArrayList<Tienda> t = modelo.getTiendas();


        for (Puzzle puzzle : p) {
            vista.comboPuzzles.addItem(puzzle);
        }
        for (Tienda tienda : t) {
            vista.comboTiendas.addItem(tienda);
        }
        vista.comboPuzzles.setSelectedIndex(-1);
        vista.comboTiendas.setSelectedIndex(-1);
    }

    /***
     * Listar los puzzles que tiene una editorial
     * @param lista de puzzles
     */
    public void listarEditorialPuzzles(List<Puzzle> lista) {
        vista.dlmEditorialPuzzles.clear();
        for (Puzzle unPuzzle : lista) {
            vista.dlmEditorialPuzzles.addElement(unPuzzle);
        }
    }

    /***
     * Añadir los listener
     * @param listener
     */
    private void addActionListeners(ActionListener listener) {
        vista.conexionItem.addActionListener(listener);
        vista.salirItem.addActionListener(listener);
        vista.nuevoPuzzleBtn.addActionListener(listener);
        vista.eliminarPuzzleBtn.addActionListener(listener);
        vista.modificarPuzzleBtn.addActionListener(listener);
        vista.altaCompradorBtn.addActionListener(listener);
        vista.modificarCompradorBtn.addActionListener(listener);
        vista.eliminarCompradorBtn.addActionListener(listener);
        vista.altaTiendaBtn.addActionListener(listener);
        vista.modificarTiendaBtn.addActionListener(listener);
        vista.eliminarTiendaBtn.addActionListener(listener);
        vista.altaEditorialBtn.addActionListener(listener);
        vista.modificarEditorialBtn.addActionListener(listener);
        vista.eliminarEditorialBtn.addActionListener(listener);
        vista.altaVentaBtn.addActionListener(listener);
        vista.modificarVentaBtn.addActionListener(listener);
        vista.eliminarVentaBtn.addActionListener(listener);
        vista.listarEditorialPuzzlesBtn.addActionListener(listener);
    }

    /**
     * Añadir los listeners de las listas
     * @param listener
     */
    private void addListSelectionListener(ListSelectionListener listener) {
        vista.listPuzzles.addListSelectionListener(listener);
        vista.listCompradores.addListSelectionListener(listener);
        vista.listTiendas.addListSelectionListener(listener);
        vista.listEditoriales.addListSelectionListener(listener);
        vista.listVentas.addListSelectionListener(listener);
        vista.listEditorialPuzzles.addListSelectionListener(listener);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            if (e.getSource() == vista.listPuzzles) {
                Puzzle puzzleSeleccion = (Puzzle) vista.listPuzzles.getSelectedValue();
                vista.txtTitulo.setText(String.valueOf(puzzleSeleccion.getTitulo()));
                vista.txtIsbn.setText(String.valueOf(puzzleSeleccion.getIsbn()));
                vista.comboEditoriales.setSelectedItem(puzzleSeleccion.getEditorial());
            }
            if (e.getSource() == vista.listCompradores) {
                Comprador compradorSeleccionado = (Comprador) vista.listCompradores.getSelectedValue();
                vista.txtNombreComprador.setText(String.valueOf(compradorSeleccionado.getNombre()));
                vista.txtApellidos.setText(String.valueOf(compradorSeleccionado.getApellidos()));
                vista.txtDNI.setText(String.valueOf(compradorSeleccionado.getDni()));
            }
            if (e.getSource() == vista.listTiendas) {
                Tienda t = (Tienda) vista.listTiendas.getSelectedValue();
                vista.txtNombreTienda.setText(String.valueOf(t.getNombre()));
                vista.txtTlfTienda.setText(String.valueOf(t.getTelefono()));
            }
            if (e.getSource() == vista.listEditoriales) {
                Editorial t = (Editorial) vista.listEditoriales.getSelectedValue();
                vista.txtNombreEditorial.setText(String.valueOf(t.getNombre()));
                vista.txtTlfEditorial.setText(String.valueOf(t.getTelefono()));
            }
            if (e.getSource() == vista.listVentas) {
                VentaPuzzle vp = (VentaPuzzle) vista.listVentas.getSelectedValue();
                vista.comboPuzzles.setSelectedItem(vp.getPuzzle());
                vista.comboTiendas.setSelectedItem(vp.getTienda());
                vista.txtPrecio.setText(String.valueOf(vp.getPrecio()));
            }
        }

    }
}

