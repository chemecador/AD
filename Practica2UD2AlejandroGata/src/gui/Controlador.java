package gui;

import util.Util;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

public class Controlador implements ActionListener, ItemListener, ListSelectionListener, WindowListener {
    private Modelo modelo;
    private Vista vista;
    private boolean refrescar;

    public Controlador(Vista vista, Modelo modelo) {
        this.modelo = modelo;
        this.vista = vista;
        modelo.conectar();
        setOptions();
        addActionListeners(this);
        addItemListeners(this);
        addWindowListeners(this);
        refrescarTodo();
    }

    private void refrescarTodo() {
        refrescarCompradores();
        refrescarEditorial();
        refrescarPuzzles();
        refrescar = false;
    }

    private void addActionListeners(ActionListener listener) {
        vista.anadirPuzzle.addActionListener(listener);
        vista.anadirPuzzle.setActionCommand("anadirPuzzle");
        vista.anadirComprador.addActionListener(listener);
        vista.anadirComprador.setActionCommand("anadirComprador");
        vista.anadirEditorial.addActionListener(listener);
        vista.anadirEditorial.setActionCommand("anadirEditorial");
        vista.eliminarPuzzle.addActionListener(listener);
        vista.eliminarPuzzle.setActionCommand("eliminarPuzzle");
        vista.eliminarComprador.addActionListener(listener);
        vista.eliminarComprador.setActionCommand("eliminarComprador");
        vista.eliminarEditorial.addActionListener(listener);
        vista.eliminarEditorial.setActionCommand("eliminarEditorial");
        vista.modificarPuzzle.addActionListener(listener);
        vista.modificarPuzzle.setActionCommand("modificarPuzzle");
        vista.modificarComprador.addActionListener(listener);
        vista.modificarComprador.setActionCommand("modificarComprador");
        vista.modificarEditorial.addActionListener(listener);
        vista.modificarEditorial.setActionCommand("modificarEditorial");
        vista.optionDialog.btnOpcionesGuardar.addActionListener(listener);
        vista.itemOpciones.addActionListener(listener);
        vista.itemSalir.addActionListener(listener);
        vista.btnValidate.addActionListener(listener);
    }

    private void addWindowListeners(WindowListener listener) {
        vista.addWindowListener(listener);
    }

    private void addItemListeners(Controlador controlador) {
    }

    /**
     * Muestra los atributos de un objeto seleccionado y los borra una vez se deselecciona
     *
     * @param e Evento producido en una lista
     */

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()
                && !((ListSelectionModel) e.getSource()).isSelectionEmpty()) {
            if (e.getSource().equals(vista.editorialesTabla.getSelectionModel())) {
                int row = vista.editorialesTabla.getSelectedRow();
                vista.txtNombreEditorial.setText(String.valueOf(vista.editorialesTabla.getValueAt(row, 1)));
                vista.txtEmail.setText(String.valueOf(vista.editorialesTabla.getValueAt(row, 2)));
                vista.txtTelefono.setText(String.valueOf(vista.editorialesTabla.getValueAt(row, 3)));
                vista.comboRepu.setSelectedItem(String.valueOf(vista.editorialesTabla.getValueAt(row, 4)));
                vista.txtWeb.setText(String.valueOf(vista.editorialesTabla.getValueAt(row, 5)));
            } else if (e.getSource().equals(vista.compradoresTabla.getSelectionModel())) {
                int row = vista.compradoresTabla.getSelectedRow();
                vista.txtNombre.setText(String.valueOf(vista.compradoresTabla.getValueAt(row, 1)));
                vista.txtApellidos.setText(String.valueOf(vista.compradoresTabla.getValueAt(row, 2)));
                vista.fechaCompra.setDate((Date.valueOf(String.valueOf(vista.compradoresTabla.getValueAt(row, 3)))).toLocalDate());
                vista.txtPais.setText(String.valueOf(vista.compradoresTabla.getValueAt(row, 4)));
            } else if (e.getSource().equals(vista.puzzlesTabla.getSelectionModel())) {
                int row = vista.puzzlesTabla.getSelectedRow();
                vista.txtTitulo.setText(String.valueOf(vista.puzzlesTabla.getValueAt(row, 1)));
                vista.comboComprador.setSelectedItem(String.valueOf(vista.puzzlesTabla.getValueAt(row, 5)));
                vista.comboEditorial.setSelectedItem(String.valueOf(vista.puzzlesTabla.getValueAt(row, 3)));
                vista.comboGenero.setSelectedItem(String.valueOf(vista.puzzlesTabla.getValueAt(row, 4)));
                vista.fecha.setDate((Date.valueOf(String.valueOf(vista.puzzlesTabla.getValueAt(row, 7)))).toLocalDate());
                vista.txtIsbn.setText(String.valueOf(vista.puzzlesTabla.getValueAt(row, 2)));
                vista.txtPrecio.setText(String.valueOf(vista.puzzlesTabla.getValueAt(row, 6)));
            } else if (e.getValueIsAdjusting()
                    && ((ListSelectionModel) e.getSource()).isSelectionEmpty() && !refrescar) {
                if (e.getSource().equals(vista.editorialesTabla.getSelectionModel())) {
                    borrarCamposEditoriales();
                } else if (e.getSource().equals(vista.compradoresTabla.getSelectionModel())) {
                    borrarCamposCompradores();
                } else if (e.getSource().equals(vista.puzzlesTabla.getSelectionModel())) {
                    borrarCamposPuzzles();
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Opciones":
                vista.adminPasswordDialog.setVisible(true);
                break;
            case "Desconectar":
                modelo.desconectar();
                break;
            case "Salir":
                System.exit(0);
                break;
            case "abrirOpciones":
                if (String.valueOf(vista.adminPassword.getPassword()).equals(modelo.getAdminPassword())) {
                    vista.adminPassword.setText("");
                    vista.adminPasswordDialog.dispose();
                    vista.optionDialog.setVisible(true);
                } else {
                    Util.showErrorAlert("La contraseña introducida no es correcta");
                }
                break;
            case "guardarOpciones":
                modelo.setPropValues(vista.optionDialog.tfIP.getText(),
                        vista.optionDialog.tfUser.getText(),
                        String.valueOf(vista.optionDialog.pfPass.getPassword()),
                        String.valueOf(vista.optionDialog.pfAdmin.getPassword()));
                vista.optionDialog.dispose();
                vista.dispose();
                new Controlador(new Vista(), new Modelo());
                break;
            case "anadirPuzzle":
                try {
                    if (comprobarPuzzleVacio()) {
                        Util.showErrorAlert("Rellena todos los campos");
                        vista.puzzlesTabla.clearSelection();
                    } else if (modelo.puzzleIsbnYaExiste(vista.txtIsbn.getText())) {
                        Util.showErrorAlert("Ese ISBN ya existe");
                        vista.puzzlesTabla.clearSelection();
                    } else {
                        modelo.insertarPuzzle(
                                vista.txtTitulo.getText(),
                                vista.txtIsbn.getText(),
                                String.valueOf(vista.comboEditorial.getSelectedItem()),
                                String.valueOf(vista.comboGenero.getSelectedItem()),
                                String.valueOf(vista.comboComprador.getSelectedItem()),
                                Float.parseFloat(vista.txtPrecio.getText()),
                                vista.fecha.getDate());
                    }
                } catch (NumberFormatException nfe) {
                    Util.showErrorAlert("Introduce numeros en los campos que lo requieran");
                    vista.puzzlesTabla.clearSelection();
                }
                borrarCamposPuzzles();
                refrescarPuzzles();
                break;
            case "modificarPuzzle":
                try {
                    if (comprobarPuzzleVacio()) {
                        Util.showErrorAlert("Rellena todos los campos");
                        vista.puzzlesTabla.clearSelection();
                    } else {
                        modelo.modificarPuzzle(
                                vista.txtTitulo.getText(),
                                vista.txtIsbn.getText(),
                                String.valueOf(vista.comboEditorial.getSelectedItem()),
                                String.valueOf(vista.comboGenero.getSelectedItem()),
                                String.valueOf(vista.comboComprador.getSelectedItem()),
                                Float.parseFloat(vista.txtPrecio.getText()),
                                vista.fecha.getDate(),
                                Integer.parseInt((String) vista.puzzlesTabla.getValueAt(vista.puzzlesTabla.getSelectedRow(), 0))
                        );
                    }
                } catch (NumberFormatException nfe) {
                    Util.showErrorAlert("Introduce numeros en los campos que lo requieran");
                    vista.puzzlesTabla.clearSelection();
                }
                borrarCamposPuzzles();
                refrescarPuzzles();
                break;
            case "eliminarPuzzle":
                modelo.borrarPuzzle(Integer.parseInt((String) vista.puzzlesTabla.getValueAt(vista.puzzlesTabla.getSelectedRow(), 0)));
                borrarCamposPuzzles();
                refrescarPuzzles();
                break;
            case "anadirComprador": {
                try {
                    if (comprobarCompradorVacio()) {
                        Util.showErrorAlert("Rellena todos los campos");
                        vista.compradoresTabla.clearSelection();
                    } else if (modelo.compradorNombreYaExiste(vista.txtNombre.getText(),
                            vista.txtApellidos.getText())) {
                        Util.showErrorAlert("Ese nombre ya existe.\nIntroduce un comprador diferente");
                        vista.compradoresTabla.clearSelection();
                    } else {
                        modelo.insertarComprador(vista.txtNombre.getText(),
                                vista.txtApellidos.getText(),
                                vista.txtDni.getText(),
                                vista.fechaCompra.getDate(),
                                vista.txtPais.getText());
                        refrescarCompradores();
                    }
                } catch (NumberFormatException nfe) {
                    Util.showErrorAlert("Introduce números en los campos que lo requieren");
                    vista.compradoresTabla.clearSelection();
                }
                borrarCamposCompradores();
            }
            break;
            case "modificarComprador": {
                try {
                    if (comprobarCompradorVacio()) {
                        Util.showErrorAlert("Rellena todos los campos");
                        vista.compradoresTabla.clearSelection();
                    } else {
                        modelo.modificarComprador(vista.txtNombre.getText(), vista.txtApellidos.getText(),
                                vista.txtDni.getText(), vista.fechaCompra.getDate(), vista.txtPais.getText(),
                                Integer.parseInt((String) vista.compradoresTabla.getValueAt(vista.compradoresTabla.getSelectedRow(), 0)));
                        refrescarCompradores();
                    }
                } catch (NumberFormatException nfe) {
                    Util.showErrorAlert("Introduce números en los campos que lo requieren");
                    vista.compradoresTabla.clearSelection();
                }
                borrarCamposCompradores();
            }
            break;
            case "eliminarComprador":
                modelo.borrarComprador(Integer.parseInt((String) vista.compradoresTabla.getValueAt(vista.compradoresTabla.getSelectedRow(), 0)));
                borrarCamposCompradores();
                refrescarCompradores();
                break;
            case "anadirEditorial": {
                try {
                    if (comprobarEditorialVacia()) {
                        Util.showErrorAlert("Rellena todos los campos");
                        vista.editorialesTabla.clearSelection();
                    } else if (modelo.editorialNombreYaExiste(vista.txtNombreEditorial.getText())) {
                        Util.showErrorAlert("Ese nombre ya existe.\nIntroduce una editorial diferente.");
                        vista.editorialesTabla.clearSelection();
                    } else {
                        modelo.insertarEditorial(vista.txtNombreEditorial.getText(), vista.txtEmail.getText(),
                                vista.txtTelefono.getText(), Integer.parseInt(vista.txtAnti.getText()),
                                (String) vista.comboRepu.getSelectedItem(),
                                vista.txtWeb.getText());
                        refrescarEditorial();
                    }
                } catch (NumberFormatException nfe) {
                    Util.showErrorAlert("Introduce números en los campos que lo requieren");
                    vista.editorialesTabla.clearSelection();
                }
                borrarCamposEditoriales();
            }
            break;
            case "modificarEditorial": {
                try {
                    if (comprobarEditorialVacia()) {
                        Util.showErrorAlert("Rellena todos los campos");
                        vista.editorialesTabla.clearSelection();
                    } else {
                        modelo.modificarEditorial(vista.txtNombreEditorial.getText(), vista.txtEmail.getText(), vista.txtTelefono.getText(),
                                Integer.parseInt(vista.txtAnti.getText()), String.valueOf(vista.comboRepu.getSelectedItem()), vista.txtWeb.getText(),
                                Integer.parseInt((String) vista.editorialesTabla.getValueAt(vista.editorialesTabla.getSelectedRow(), 0)));
                        refrescarEditorial();
                    }
                } catch (NumberFormatException nfe) {
                    Util.showErrorAlert("Introduce números en los campos que lo requieren");
                    vista.editorialesTabla.clearSelection();
                }
                borrarCamposEditoriales();
            }
            break;
            case "eliminarEditorial":
                modelo.borrarEditorial(Integer.parseInt((String) vista.editorialesTabla.getValueAt(vista.editorialesTabla.getSelectedRow(), 0)));
                borrarCamposEditoriales();
                refrescarEditorial();
                break;
        }
    }

    private void refrescarEditorial() {
        try {
            vista.editorialesTabla.setModel(construirTableModelEditoriales(modelo.consultarEditorial()));
            vista.comboEditorial.removeAllItems();
            for (int i = 0; i < vista.dtmEditoriales.getRowCount(); i++) {
                vista.comboEditorial.addItem(vista.dtmEditoriales.getValueAt(i, 0) + " - " +
                        vista.dtmEditoriales.getValueAt(i, 1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private DefaultTableModel construirTableModelEditoriales(ResultSet rs)
            throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        // names of columns
        Vector<String> columnNames = new Vector<>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }
        // data of the table
        Vector<Vector<Object>> data = new Vector<>();
        setDataVector(rs, columnCount, data);
        vista.dtmEditoriales.setDataVector(data, columnNames);
        return vista.dtmEditoriales;
    }

    private void refrescarCompradores() {
        try {
            vista.compradoresTabla.setModel(construirTableModeloCompradores(modelo.consultarComprador()));
            vista.comboComprador.removeAllItems();
            for (int i = 0; i < vista.dtmCompradores.getRowCount(); i++) {
                vista.comboComprador.addItem(vista.dtmCompradores.getValueAt(i, 0) + " - " +
                        vista.dtmCompradores.getValueAt(i, 2) + ", " + vista.dtmCompradores.getValueAt(i, 1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private DefaultTableModel construirTableModeloCompradores(ResultSet rs)
            throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        // names of columns
        Vector<String> columnNames = new Vector<>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }
        // data of the table
        Vector<Vector<Object>> data = new Vector<>();
        setDataVector(rs, columnCount, data);
        vista.dtmCompradores.setDataVector(data, columnNames);
        return vista.dtmCompradores;
    }

    /**
     * Actualiza los puzzles que se ven en la lista y los comboboxes
     */
    private void refrescarPuzzles() {
        try {
            vista.puzzlesTabla.setModel(construirTableModelPuzzles(modelo.consultarPuzzles()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private DefaultTableModel construirTableModelPuzzles(ResultSet rs)
            throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        // names of columns
        Vector<String> columnNames = new Vector<>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }
        // data of the table
        Vector<Vector<Object>> data = new Vector<>();
        setDataVector(rs, columnCount, data);
        vista.dtmPuzzles.setDataVector(data, columnNames);
        return vista.dtmPuzzles;

    }

    private void setDataVector(ResultSet rs, int columnCount, Vector<Vector<Object>> data) throws SQLException {
        while (rs.next()) {
            Vector<Object> vector = new Vector<>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }
    }

    private void setOptions() {
    }

    private void borrarCamposPuzzles() {
        vista.comboEditorial.setSelectedIndex(-1);
        vista.comboComprador.setSelectedIndex(-1);
        vista.txtTitulo.setText("");
        vista.txtIsbn.setText("");
        vista.comboGenero.setSelectedIndex(-1);
        vista.txtPrecio.setText("");
        vista.fecha.setText("");
    }

    private void borrarCamposCompradores() {
        vista.txtNombre.setText("");
        vista.txtApellidos.setText("");
        vista.txtPais.setText("");
        vista.fechaCompra.setText("");
    }

    private void borrarCamposEditoriales() {
        vista.txtNombreEditorial.setText("");
        vista.txtEmail.setText("");
        vista.txtTelefono.setText("");
        vista.comboRepu.setSelectedIndex(-1);
        vista.txtWeb.setText("");
    }

    private boolean comprobarPuzzleVacio() {
        return vista.txtTitulo.getText().isEmpty() ||
                vista.txtPrecio.getText().isEmpty() ||
                vista.txtIsbn.getText().isEmpty() ||
                vista.comboGenero.getSelectedIndex() == -1 ||
                vista.comboComprador.getSelectedIndex() == -1 ||
                vista.comboEditorial.getSelectedIndex() == -1 ||
                vista.fecha.getText().isEmpty();
    }

    private boolean comprobarCompradorVacio() {
        return vista.txtApellidos.getText().isEmpty() ||
                vista.txtNombre.getText().isEmpty() ||
                vista.txtPais.getText().isEmpty() ||
                vista.fechaCompra.getText().isEmpty();
    }

    private boolean comprobarEditorialVacia() {
        return vista.txtNombreEditorial.getText().isEmpty() ||
                vista.txtEmail.getText().isEmpty() ||
                vista.txtTelefono.getText().isEmpty() ||
                vista.comboRepu.getSelectedIndex() == -1 ||
                vista.txtWeb.getText().isEmpty();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
