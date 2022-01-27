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

public class Controlador implements ActionListener, ItemListener, ListSelectionListener, WindowListener{

    private Modelo modelo;
    private Vista vista;
    boolean refrescar;
    boolean conectado;

    public Controlador(Modelo modelo, Vista vista) {
        this.modelo = modelo;
        this.vista = vista;
        conectado = true;
        modelo.conectar();
        setOptions();
        addActionListeners(this);
        addItemListeners(this);
        addWindowListeners(this);
        refrescarTodo();
        iniciar();
    }
    private void refrescarTodo() {
        refrescarSocios();
        refrescarInstructores();
        refrescarActividades();
        refrescar = false;
    }

    private void addActionListeners(ActionListener listener) {
        vista.clienButtonFiltrar.addActionListener(listener);
        vista.clienButtonMod.addActionListener(listener);
        vista.clienButtonNuevo.addActionListener(listener);
        vista.clienButtonBorrar.addActionListener(listener);
        vista.instButtonMod.addActionListener(listener);
        vista.instButtonNuevo.addActionListener(listener);
        vista.instButtonBorrar.addActionListener(listener);
        vista.actModButton.addActionListener(listener);
        vista.actNuevButton.addActionListener(listener);
        vista.actBorrButton.addActionListener(listener);
        vista.itemOpciones.addActionListener(listener);
        vista.itemSalir.addActionListener(listener);
        vista.btnValidate.addActionListener(listener);
        vista.itemDesconectar.addActionListener(listener);
        vista.optionDialog.SAVEButton.addActionListener(listener);
    }

    private void addWindowListeners(WindowListener listener) {
        vista.addWindowListener(listener);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Opciones":
                vista.adminPasswordDialog.setVisible(true);
                break;
            case "Desconectar":
                if (conectado){
                    System.out.println("CONECTAR");
                    modelo.desconectar();
                    vista.dtmSocios.setRowCount(0);
                    vista.dtmInstructores.setRowCount(0);
                    vista.dtmActividades.setRowCount(0);
                    vista.itemDesconectar.setText("Conectar");
                    conectado = false;
                    desactivarBotones();
                }else{
                    modelo.conectar();
                    conectado = true;
                    refrescarTodo();
                    vista.itemDesconectar.setText("Desconectar");
                    activarBotones();
                }
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
                new Controlador(new Modelo(), new Vista());
                break;
            case "NuevoSocio": {
                try {

                    if (comprobarSocioVacio()) {
                        Util.showErrorAlert("Rellena todos los campos");
                        vista.tablaSocios.clearSelection();

                    }else if (modelo.existeDniSocio(vista.txtDniSoc.getText())) {
                        Util.showErrorAlert("Ese DNI ya existe.\nIntroduce un DNI diferente");
                        vista.tablaSocios.clearSelection();
                    } else {
                        String password = "";
                        JPasswordField pass = new JPasswordField(10);
                        String[] options = new String[]{"OK", "Cancel"};
                        int option = JOptionPane.showOptionDialog(null, pass, "PASS",
                                JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                                null, options, options[1]);
                        if(option == 0) // pressing OK button
                        {
                            char[] charpass = pass.getPassword();
                            password = new String(charpass);
                            if(password.equals(modelo.getAdminPassword())) {
                                modelo.insertarSocio(vista.txtNombreSoc.getText(), vista.txtApellidosSoc.getText(),
                                        vista.txtDniSoc.getText(),
                                        vista.DatePickerSocios.getDate(),String.valueOf(vista.socTatifCombo.getSelectedItem()),
                                        (String)vista.socActCombo.getSelectedItem(), (String)vista.socInsCombo.getSelectedItem());
                                borrarCamposSocios();
                                refrescarSocios();
                            }else{
                                Util.showWarningAlert("CONTRASEÑA INCORRECTA");
                            }

                        }
                    }
                } catch (NumberFormatException nfe) {
                    Util.showErrorAlert("Introduce números en los campos que lo requieren");
                    vista.tablaInstructores.clearSelection();
                }
                break;
            }
            case "NuevoInstructor": {
                try {

                    if (comprobarInstructorVacio()) {
                        Util.showErrorAlert("Rellena todos los campos");
                        vista.tablaInstructores.clearSelection();

                    }else if (modelo.existeCodigoInstructor(vista.txtCodInsIns.getText())) {
                        Util.showErrorAlert("Ese código de instructor ya existe.\nIntroduce un código diferente");
                        vista.tablaInstructores.clearSelection();
                    } else {
                        String password = "";
                        JPasswordField pass = new JPasswordField(10);
                        String[] options = new String[]{"OK", "Cancel"};
                        int option = JOptionPane.showOptionDialog(null, pass, "PASS",
                                JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                                null, options, options[1]);
                        if(option == 0) // pressing OK button
                        {
                            char[] charpass = pass.getPassword();
                            password = new String(charpass);
                            if(password.equals(modelo.getAdminPassword())) {
                                modelo.insertarInstructor(vista.txtNombreIns.getText(), vista.txtApellidosIns.getText(),
                                        vista.DatePickerInst.getDate(),
                                        vista.txtCodInsIns.getText());
                                borrarCamposInstructores();
                                refrescarInstructores();
                        }else{
                                Util.showWarningAlert("CONTRASEÑA INCORRECTA");
                            }

                        }
                    }
                } catch (NumberFormatException nfe) {
                    Util.showErrorAlert("Introduce números en los campos que lo requieren");
                    vista.tablaInstructores.clearSelection();
                }
                break;
            }
            case "NuevoActividades": {
                try {
                    if (comprobarActividadVacia()) {
                        Util.showErrorAlert("Rellena todos los campos");
                        vista.tablaActividades.clearSelection();

                    } else {
                        modelo.insertarActividad(vista.txtTituloAct.getText(), String.valueOf(vista.actCombo.getSelectedItem()),
                                                   Integer.parseInt(vista.txtHorasAct.getText()), Float.parseFloat(vista.txtPrecioAct.getText()));
                        borrarCamposActividades();
                        refrescarActividades();
                    }
                } catch (NumberFormatException nfe) {
                    Util.showErrorAlert("Introduce números en los campos que lo requieren");
                    vista.tablaActividades.clearSelection();
                }
                break;
            }
            case "BorrarSocio":
                modelo.eliminarSocio(Integer.parseInt((String)vista.tablaSocios.getValueAt(vista.tablaSocios.getSelectedRow(), 0)));
                borrarCamposSocios();
                refrescarSocios();
                break;
            case "BorrarInstructor":
                modelo.eliminarInstructor(Integer.parseInt((String)vista.tablaInstructores.getValueAt(vista.tablaInstructores.getSelectedRow(), 0)));
                borrarCamposInstructores();
                refrescarInstructores();
                break;
            case "BorrarActividades":
                modelo.eliminarActividad(Integer.parseInt((String)vista.tablaActividades.getValueAt(vista.tablaActividades.getSelectedRow(), 0)));
                borrarCamposActividades();
                refrescarActividades();
                break;

            case "ModificarSocio":
                modelo.modificarSocio(vista.txtNombreSoc.getText(),
                        vista.txtApellidosSoc.getText(),
                        vista.txtDniSoc.getText(),
                        vista.DatePickerSocios.getDate(),
                        String.valueOf(vista.socTatifCombo.getSelectedItem()),
                        String.valueOf(vista.socInsCombo.getSelectedItem()),
                        String.valueOf(vista.socActCombo.getSelectedItem()),
                        Integer.parseInt((String)vista.tablaSocios.getValueAt(vista.tablaSocios.getSelectedRow(), 0)));
                borrarCamposSocios();
                refrescarSocios();
                break;
            case "ModificarInstructor":
                modelo.modificarInstructor(vista.txtNombreIns.getText(), vista.txtApellidosIns.getText(),
                        vista.DatePickerInst.getDate(),
                        vista.txtCodInsIns.getText(),
                        Integer.parseInt((String)vista.tablaInstructores.getValueAt(vista.tablaInstructores.getSelectedRow(), 0)));
                borrarCamposInstructores();
                refrescarInstructores();
                break;
            case "ModificarActividades":
                modelo.modificarActividad(vista.txtTituloAct.getText(), String.valueOf(vista.actCombo.getSelectedItem()),
                        Integer.parseInt(vista.txtHorasAct.getText()), Float.parseFloat(vista.txtPrecioAct.getText()),
                        Integer.parseInt((String)vista.tablaActividades.getValueAt(vista.tablaActividades.getSelectedRow(), 0)));
                borrarCamposActividades();
                refrescarActividades();
                break;
            case "FiltrarSocio":
                try {
                    if (vista.txtDniSoc.getText().length() == 0) {
                        JOptionPane.showMessageDialog(null, "Escribe el DNI del socio",
                                "Campo DNI vacío", JOptionPane.PLAIN_MESSAGE);
                        refrescarSocios();
                    } else {
                        vista.tablaSocios.setModel(construirTableModelSocios(modelo.filtrarSocio(vista.txtDniSoc.getText())));
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                break;
        }
    }

    private boolean comprobarSocioVacio() {
        if(vista.txtNombreSoc.getText().isEmpty() ||
            vista.txtApellidosSoc.getText().isEmpty() ||
            vista.txtDniSoc.getText().isEmpty() ||
            vista.DatePickerSocios.getText().isEmpty() ||
            vista.socActCombo.getSelectedIndex() == -1 ||
            vista.socTatifCombo.getSelectedIndex() == -1 ||
            vista.socInsCombo.getSelectedIndex() == -1){
            return true;
        }else{
            return false;
        }
    }

    private boolean comprobarInstructorVacio() {
        if(vista.txtNombreIns.getText().isEmpty() ||
                vista.txtApellidosIns.getText().isEmpty() ||
                vista.DatePickerInst.getText().isEmpty() ||
                vista.txtCodInsIns.getText().isEmpty()){
            return true;
        }else{
            return false;
        }
    }

    private boolean comprobarActividadVacia(){
        if(vista.txtTituloAct.getText().isEmpty() ||
            vista.txtHorasAct.getText().isEmpty() ||
            vista.txtPrecioAct.getText().isEmpty() ||
            vista.actCombo.getSelectedIndex() == -1){
            return true;
        }else{
            return false;
        }
    }

    private void desactivarBotones() {
        vista.clienButtonNuevo.setEnabled(false);
        vista.clienButtonBorrar.setEnabled(false);
        vista.clienButtonMod.setEnabled(false);
        vista.clienButtonFiltrar.setEnabled(false);
        vista.instButtonBorrar.setEnabled(false);
        vista.instButtonNuevo.setEnabled(false);
        vista.instButtonMod.setEnabled(false);
        vista.actNuevButton.setEnabled(false);
        vista.actBorrButton.setEnabled(false);
        vista.actModButton.setEnabled(false);
    }

    private void activarBotones(){
        vista.clienButtonNuevo.setEnabled(true);
        vista.clienButtonBorrar.setEnabled(true);
        vista.clienButtonMod.setEnabled(true);
        vista.clienButtonFiltrar.setEnabled(true);
        vista.instButtonBorrar.setEnabled(true);
        vista.instButtonNuevo.setEnabled(true);
        vista.instButtonMod.setEnabled(true);
        vista.actNuevButton.setEnabled(true);
        vista.actBorrButton.setEnabled(true);
        vista.actModButton.setEnabled(true);
    }

    private void borrarCamposSocios() {
        vista.txtNombreSoc.setText("");
        vista.txtApellidosSoc.setText("");
        vista.txtDniSoc.setText("");
        vista.DatePickerSocios.setText("");
        vista.socTatifCombo.setSelectedIndex(-1);
        vista.socActCombo.setSelectedIndex(-1);
        vista.socInsCombo.setSelectedIndex(-1);

    }

    private void borrarCamposInstructores() {
        vista.txtNombreIns.setText("");
        vista.txtApellidosIns.setText("");
        vista.DatePickerInst.setText("");
        vista.txtCodInsIns.setText("");
    }

    private void borrarCamposActividades() {
        vista.txtTituloAct.setText("");
        vista.txtPrecioAct.setText("");
        vista.txtHorasAct.setText("");
        vista.actCombo.setSelectedIndex(-1);
    }

    private void refrescarActividades() {
        try {
            vista.tablaActividades.setModel(construirTableModeloActividades(modelo.consultarActividades()));
            vista.socActCombo.removeAllItems();
            for(int i = 0; i < vista.dtmActividades.getRowCount(); i++) {
                vista.socActCombo.addItem(vista.dtmActividades.getValueAt(i, 0)+" - "+
                        vista.dtmActividades.getValueAt(i, 1)+" - "+vista.dtmActividades.getValueAt(i, 4) + "€");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private DefaultTableModel construirTableModeloActividades(ResultSet rs)
            throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();

        Vector<String> columnNames = new Vector<>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        Vector<Vector<Object>> data = new Vector<>();
        setDataVector(rs, columnCount, data);
        vista.dtmActividades.setDataVector(data, columnNames);
        return vista.dtmActividades;
    }

    private void refrescarInstructores() {
        try {
            vista.tablaInstructores.setModel(construirTableModeloInstructores(modelo.consultarInstructores()));
            vista.socInsCombo.removeAllItems();
            for(int i = 0; i < vista.dtmInstructores.getRowCount(); i++) {
                vista.socInsCombo.addItem(vista.dtmInstructores.getValueAt(i, 0)+" - "+
                        vista.dtmInstructores.getValueAt(i, 1)+" "+vista.dtmInstructores.getValueAt(i, 2) + " - " + vista.dtmInstructores.getValueAt(i, 4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private DefaultTableModel construirTableModeloInstructores(ResultSet rs)
            throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();

        Vector<String> columnNames = new Vector<>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        Vector<Vector<Object>> data = new Vector<>();
        setDataVector(rs, columnCount, data);
        vista.dtmInstructores.setDataVector(data, columnNames);
        return vista.dtmInstructores;
    }

    private void refrescarSocios() {
        try {
            vista.tablaSocios.setModel(construirTableModelSocios(modelo.consultarSocios()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private DefaultTableModel construirTableModelSocios(ResultSet rs)
            throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();

        Vector<String> columnNames = new Vector<>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        Vector<Vector<Object>> data = new Vector<>();
        setDataVector(rs, columnCount, data);
        vista.dtmSocios.setDataVector(data, columnNames);
        return vista.dtmSocios;
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
        vista.optionDialog.tfIP.setText(modelo.getIP());
        vista.optionDialog.tfUser.setText(modelo.getUser());
        vista.optionDialog.pfPass.setText(modelo.getPassword());
        vista.optionDialog.pfAdmin.setText(modelo.getAdminPassword());
    }

    void iniciar(){
        vista.tablaSocios.setCellSelectionEnabled(true);
        ListSelectionModel cellSelectionModel =  vista.tablaSocios.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()
                        && !((ListSelectionModel) e.getSource()).isSelectionEmpty()) {
                    if (e.getSource().equals(vista.tablaSocios.getSelectionModel())) {
                        int row = vista.tablaSocios.getSelectedRow();
                        vista.txtNombreSoc.setText(String.valueOf(vista.tablaSocios.getValueAt(row, 1)));
                        vista.txtApellidosSoc.setText(String.valueOf(vista.tablaSocios.getValueAt(row, 2)));
                        vista.txtDniSoc.setText(String.valueOf(vista.tablaSocios.getValueAt(row, 3)));
                        vista.DatePickerSocios.setDate((Date.valueOf(String.valueOf(vista.tablaSocios.getValueAt(row, 4)))).toLocalDate());
                        vista.socTatifCombo.setSelectedItem(String.valueOf(vista.tablaSocios.getValueAt(row, 5)));
                        vista.socInsCombo.setSelectedItem(String.valueOf(vista.tablaSocios.getValueAt(row, 6)));
                        vista.socActCombo.setSelectedItem(String.valueOf(vista.tablaSocios.getValueAt(row, 7)));
                    } else if (e.getValueIsAdjusting()
                            && ((ListSelectionModel) e.getSource()).isSelectionEmpty() && !refrescar) {
                        if (e.getSource().equals(vista.tablaActividades.getSelectionModel())) {
                            borrarCamposActividades();
                        } else if (e.getSource().equals(vista.tablaInstructores.getSelectionModel())) {
                            borrarCamposInstructores();
                        } else if (e.getSource().equals(vista.tablaSocios.getSelectionModel())) {
                            borrarCamposSocios();
                        }
                    }
                }
            }
        });

        vista.tablaInstructores.setCellSelectionEnabled(true);
        ListSelectionModel cellSelectionModel2 =  vista.tablaInstructores.getSelectionModel();
        cellSelectionModel2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        cellSelectionModel2.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()
                        && !((ListSelectionModel) e.getSource()).isSelectionEmpty()) {
                    if (e.getSource().equals(vista.tablaInstructores.getSelectionModel())) {
                        int row = vista.tablaInstructores.getSelectedRow();
                        vista.txtNombreIns.setText(String.valueOf(vista.tablaInstructores.getValueAt(row, 1)));
                        vista.txtApellidosIns.setText(String.valueOf(vista.tablaInstructores.getValueAt(row, 2)));
                        vista.DatePickerInst.setDate((Date.valueOf(String.valueOf(vista.tablaInstructores.getValueAt(row, 3)))).toLocalDate());
                        vista.txtCodInsIns.setText(String.valueOf(vista.tablaInstructores.getValueAt(row, 4)));
                    } else if (e.getValueIsAdjusting()
                            && ((ListSelectionModel) e.getSource()).isSelectionEmpty() && !refrescar) {
                        if (e.getSource().equals(vista.tablaActividades.getSelectionModel())) {
                            borrarCamposActividades();
                        } else if (e.getSource().equals(vista.tablaInstructores.getSelectionModel())) {
                            borrarCamposInstructores();
                        } else if (e.getSource().equals(vista.tablaSocios.getSelectionModel())) {
                            borrarCamposSocios();
                        }
                    }
                }
            }
        });

        vista.tablaActividades.setCellSelectionEnabled(true);
        ListSelectionModel cellSelectionModel3 =  vista.tablaActividades.getSelectionModel();
        cellSelectionModel3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        cellSelectionModel3.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()
                        && !((ListSelectionModel) e.getSource()).isSelectionEmpty()) {
                    if (e.getSource().equals(vista.tablaActividades.getSelectionModel())) {
                        int row = vista.tablaActividades.getSelectedRow();
                        vista.txtTituloAct.setText(String.valueOf(vista.tablaActividades.getValueAt(row, 1)));
                        vista.txtHorasAct.setText(String.valueOf(vista.tablaActividades.getValueAt(row, 3)));
                        vista.actCombo.setSelectedItem(String.valueOf(vista.tablaActividades.getValueAt(row, 2)));
                        vista.txtPrecioAct.setText(String.valueOf(vista.tablaActividades.getValueAt(row, 4)));
                    } else if (e.getValueIsAdjusting()
                            && ((ListSelectionModel) e.getSource()).isSelectionEmpty() && !refrescar) {
                        if (e.getSource().equals(vista.tablaActividades.getSelectionModel())) {
                            borrarCamposActividades();
                        } else if (e.getSource().equals(vista.tablaInstructores.getSelectionModel())) {
                            borrarCamposInstructores();
                        } else if (e.getSource().equals(vista.tablaSocios.getSelectionModel())) {
                            borrarCamposSocios();
                        }
                    }
                }
            }
        });
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()
                && !((ListSelectionModel) e.getSource()).isSelectionEmpty()) {
            if (e.getSource().equals(vista.tablaSocios.getSelectionModel())) {
                int row = vista.tablaSocios.getSelectedRow();
                vista.txtNombreSoc.setText(String.valueOf(vista.tablaSocios.getValueAt(row, 1)));
                vista.txtApellidosSoc.setText(String.valueOf(vista.tablaSocios.getValueAt(row, 2)));
                vista.txtDniSoc.setText(String.valueOf(vista.tablaSocios.getValueAt(row, 3)));
                vista.DatePickerSocios.setDate((Date.valueOf(String.valueOf(vista.tablaSocios.getValueAt(row, 4)))).toLocalDate());
                vista.socTatifCombo.setSelectedItem(String.valueOf(vista.tablaSocios.getValueAt(row, 5)));
                vista.socInsCombo.setSelectedItem(String.valueOf(vista.tablaSocios.getValueAt(row, 6)));
                vista.socActCombo.setSelectedItem(String.valueOf(vista.tablaSocios.getValueAt(row, 7)));
            } else if (e.getSource().equals(vista.tablaInstructores.getSelectionModel())) {
                int row = vista.tablaInstructores.getSelectedRow();
                vista.txtNombreIns.setText(String.valueOf(vista.tablaInstructores.getValueAt(row, 1)));
                vista.txtApellidosIns.setText(String.valueOf(vista.tablaInstructores.getValueAt(row, 2)));
                vista.DatePickerInst.setDate((Date.valueOf(String.valueOf(vista.tablaInstructores.getValueAt(row, 3)))).toLocalDate());
                vista.txtCodInsIns.setText(String.valueOf(vista.tablaInstructores.getValueAt(row, 4)));
            } else if (e.getSource().equals(vista.tablaActividades.getSelectionModel())) {
                int row = vista.tablaActividades.getSelectedRow();
                vista.txtTituloAct.setText(String.valueOf(vista.tablaActividades.getValueAt(row, 1)));
                vista.txtHorasAct.setText(String.valueOf(vista.tablaActividades.getValueAt(row, 3)));
                vista.actCombo.setSelectedItem(String.valueOf(vista.tablaActividades.getValueAt(row, 2)));
                vista.txtPrecioAct.setText(String.valueOf(vista.tablaActividades.getValueAt(row, 4)));
            } else if (e.getValueIsAdjusting()
                    && ((ListSelectionModel) e.getSource()).isSelectionEmpty() && !refrescar) {
                if (e.getSource().equals(vista.tablaActividades.getSelectionModel())) {
                    borrarCamposActividades();
                } else if (e.getSource().equals(vista.tablaInstructores.getSelectionModel())) {
                    borrarCamposInstructores();
                } else if (e.getSource().equals(vista.tablaSocios.getSelectionModel())) {
                    borrarCamposSocios();
                }
            }
        }
    }

    private void addItemListeners(Controlador controlador) {
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
