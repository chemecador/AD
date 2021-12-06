package com.alejandrogg.vehiculosbbdd;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Controlador implements ActionListener, TableModelListener {
    private Vista vista;
    private Modelo modelo;

    private enum tipoEstado {conectado, desconectado}

    ;
    private tipoEstado estado;

    public Controlador(Vista vista, Modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
        estado = tipoEstado.desconectado;
        iniciarTabla();
        addActionListeners(this);
        addTableModelListeners(this);
    }

    private void addTableModelListeners(TableModelListener listener) {
        vista.dtm.addTableModelListener(listener);
    }

    private void addActionListeners(ActionListener listener) {
        vista.btnBuscar.addActionListener(listener);
        vista.btnEliminar.addActionListener(listener);
        vista.btnNuevo.addActionListener(listener);
        vista.itemConectar.addActionListener(listener);
        vista.itemSalir.addActionListener(listener);
    }

    private void iniciarTabla() {
        String[] headers = {"id", "matricula", "marca", "modelo", "fecha matriculacion"};
        vista.dtm.setColumnIdentifiers(headers);

    }

    private void cargarFilas(ResultSet resultSet) throws SQLException {
        Object[] fila = new Object[5];
        vista.dtm.setRowCount(0);

        while (resultSet.next()) {
            fila[0] = resultSet.getObject(1);
            fila[1] = resultSet.getObject(2);
            fila[2] = resultSet.getObject(3);
            fila[3] = resultSet.getObject(4);
            fila[4] = resultSet.getObject(5);

            vista.dtm.addRow(fila);
        }

        if (resultSet.last()) {
            vista.lblAccion.setVisible(true);
            vista.lblAccion.setText(resultSet.getRow() + " filas cargadas");
        }
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        if(e.getType() == TableModelEvent.UPDATE){
            System.out.println("Actualizada");
            int filaModificada = e.getFirstRow();
            try {
                modelo.modificarVehiculo((Integer)vista.dtm.getValueAt(filaModificada,0),
                        (String)vista.dtm.getValueAt(filaModificada,1),
                        (String)vista.dtm.getValueAt(filaModificada,2),
                        (String)vista.dtm.getValueAt(filaModificada,3),
                        (java.sql.Timestamp)vista.dtm.getValueAt(filaModificada,4));
                vista.lblAccion.setText("Columna actualizada");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        switch (comando) {
            case "Conectar":
                if (estado == tipoEstado.desconectado) {
                    try {
                        modelo.conectar();
                        vista.itemConectar.setText("Desconectar");
                        estado = tipoEstado.conectado;
                        cargarFilas(modelo.obtenerDatos());
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null,
                                "Error de conexión",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    }
                } else {
                    try {
                        modelo.desconectar();
                        vista.itemConectar.setText("Conectar");
                        estado = tipoEstado.desconectado;
                        vista.lblAccion.setText("Desconectado");
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null,
                                "Error de deconexion",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    }
                }
                break;
            case "Salir":
                System.exit(0);
                break;
            case "Nuevo":
                try {
                    modelo.insertarVehiculo(vista.txtMatricula.getText(), vista.txtMarca.getText(),
                            vista.txtModelo.getText(), vista.dateTimePicker.getDateTimePermissive());
                    cargarFilas(modelo.obtenerDatos());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                break;
            case "Buscar":
                break;
            case "Eliminar":
                try {
                    int filaBorrar = vista.tabla.getSelectedRow();
                    int idBorrar = (int) vista.dtm.getValueAt(filaBorrar, 0);
                    modelo.eliminarVehiculo(idBorrar);
                    vista.dtm.removeRow(filaBorrar);
                    vista.lblAccion.setText("Fila eliminada");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                break;
        }
    }


}
