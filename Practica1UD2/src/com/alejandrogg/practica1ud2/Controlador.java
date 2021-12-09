package com.alejandrogg.practica1ud2;

import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by DAM on 02/12/2021.
 */
public class Controlador implements ActionListener, TableModelListener {

    private Vista vista;
    private Modelo modelo;

    private enum tipoEstado {
        conectado,
        desconectado
    }

    ;
    private tipoEstado estado;


    public Controlador(Vista vista, Modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
        estado = tipoEstado.desconectado;

        iniciarTabla();
        addActionListener(this);
        addTableModelListeners(this);
    }

    private void addActionListener(ActionListener listener) {
        vista.btnBuscar.addActionListener(listener);
        vista.btnEliminar.addActionListener(listener);
        vista.btnNuevo.addActionListener(listener);
        vista.itemConectar.addActionListener(listener);
        vista.itemSalir.addActionListener(listener);
    }

    private void addTableModelListeners(TableModelListener listener) {
        vista.dtm.addTableModelListener(listener);
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        if (e.getType() == TableModelEvent.UPDATE) {
            System.out.println("actualizada");
            int filaModificada = e.getFirstRow();
            try {
                modelo.modificarProducto(
                        (Integer) vista.dtm.getValueAt(filaModificada, 0),
                        (Double) vista.dtm.getValueAt(filaModificada, 1),
                        (String) vista.dtm.getValueAt(filaModificada, 2),
                        (java.sql.Timestamp) vista.dtm.getValueAt(filaModificada, 3));
                vista.lblAccion.setText("Columna actualizada");
            } catch (SQLException e1) {
                e1.printStackTrace();
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
                    } catch (SQLException e1) {
                        JOptionPane.showMessageDialog(null
                                , "Error de conexion", "Error", JOptionPane.ERROR_MESSAGE);
                        e1.printStackTrace();
                    }
                } else {
                    try {
                        modelo.desconectar();
                        vista.itemConectar.setText("Conectar");
                        estado = tipoEstado.desconectado;
                        vista.lblAccion.setText("Desconectado");
                    } catch (SQLException e1) {
                        JOptionPane.showMessageDialog(null
                                , "Error de desconexion", "Error"
                                , JOptionPane.ERROR_MESSAGE);
                        e1.printStackTrace();
                    }
                }
                break;
            case "Salir":
                System.exit(0);
                break;
            case "Nuevo":
                try {
                    modelo.insertarProducto(Double.parseDouble(vista.txtPrecio.getText()), vista.txtMarca.getText(),
                            vista.datePicker.getDate());
                    cargarFilas(modelo.obtenerDatos());
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                break;
            case "Buscar":
                if (vista.txtMarca.getText().length() > 0) {
                    try {
                        System.out.println("ENTRO A BUSCAR MARCA");
                        cargarFilas(modelo.buscarPorMarca(vista.txtMarca.getText()));
                    } catch (SQLException ex) {
                        System.out.println("Error al buscar por marca");
                    }
                } else if (vista.txtPrecio.getText().length() > 0) {
                    try {
                        System.out.println("ENTRO A BUSCAR PRECIO");
                        cargarFilas(modelo.buscarPorPrecio(Double.parseDouble(vista.txtPrecio.getText())));
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                break;
            case "Eliminar":
                try {
                    int filaBorrar = vista.tabla.getSelectedRow();
                    int idBorrar = (Integer) vista.dtm.getValueAt(filaBorrar, 0);
                    modelo.eliminarProducto(idBorrar);
                    vista.dtm.removeRow(filaBorrar);
                    vista.lblAccion.setText("Fila eliminada");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                break;
        }

    }

    private void iniciarTabla() {
        String[] headers = {"id", "precio", "marca", "fecha produccion"};
        vista.dtm.setColumnIdentifiers(headers);
    }

    private void cargarFilas(ResultSet resultSet) throws SQLException {
        Object[] fila = new Object[4];

        vista.dtm.setRowCount(0);
        while (resultSet.next()) {

            fila[0] = resultSet.getObject(1);
            fila[1] = resultSet.getObject(2);
            fila[2] = resultSet.getObject(3);
            fila[3] = resultSet.getObject(4);

            vista.dtm.addRow(fila);
        }

        if (resultSet.last()) {
            vista.lblAccion.setVisible(true);
            vista.lblAccion.setText(resultSet.getRow() + " filas cargadas");
        }
    }
}
