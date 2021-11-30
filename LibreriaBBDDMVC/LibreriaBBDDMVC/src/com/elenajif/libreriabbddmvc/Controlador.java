package com.elenajif.libreriabbddmvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by PROFESOR on 16/11/2018.
 */
public class Controlador implements ActionListener{

    private Modelo modelo;
    private Vista vista;

    public Controlador(Vista vista, Modelo modelo){

        this.vista = vista;
        this.modelo = modelo;
        anadirActionListeners(this);

        modelo.conectar();

    }


    private void anadirActionListeners(ActionListener listener){
        vista.eliminarBtn.addActionListener(listener);
        vista.insertarBtn.addActionListener(listener);
        vista.listarBtn.addActionListener(listener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        switch(comando){

            case "Insertar":{
                System.out.println("entro en insertar");
                try {
                    modelo.insertar(vista.txtIsbn.getText(), vista.txtTitulo.getText(), vista.txtAutor.getText(),vista.dateTimePicker.getDateTimePermissive());
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            break;

            case "Listar":{
                    int numero;
                try {
                    modelo.consultarLibros();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            break;
            case "Eliminar":{
                try {
                    modelo.eliminar(vista.txtIsbn.getText());
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            break;
        }
    }
}
