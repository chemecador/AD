package com.alejandrogg.libreriabbddmvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by DAM on 23/11/2021.
 */
public class Controlador implements ActionListener {

    private Modelo modelo;
    private Vista vista;

    public Controlador(Vista vista, Modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
        anadirActionListener(this);
        modelo.conectar();
    }

    private void anadirActionListener(ActionListener listener) {
        vista.eliminarBtn.addActionListener(listener);
        vista.insertarBtn.addActionListener(listener);
        vista.listarBtn.addActionListener(listener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        switch (comando) {
            case "Insertar":
                try {
                    modelo.insertar(vista.txtIsbn.getText(), vista.txtTitulo.getText(),
                            vista.txtAutor.getText(), vista.dateTimePicker.getDateTimePermissive());
                    cargarFilas(modelo.consultarLibros());
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                break;
            case "Listar":
                try {
                    modelo.consultarLibros();
                    cargarFilas(modelo.consultarLibros());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                break;
            case "Eliminar":
                try {
                    modelo.eliminar(vista.txtIsbn.getText());
                    cargarFilas(modelo.consultarLibros());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                break;
        }

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
    }


}
