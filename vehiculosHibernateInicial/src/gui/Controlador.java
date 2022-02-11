package gui;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador implements ActionListener, ListSelectionListener {

    private Vista vista;
    private Modelo modelo;

    public Controlador(Vista vista, Modelo modelo){
        this.vista = vista;
        this.modelo = modelo;

        addActionListeners(this);
        addListSelectionListener(this);
    }

    private void addListSelectionListener(ListSelectionListener listener) {
        vista.listCoches.addListSelectionListener(listener);
        vista.listPropietarios.addListSelectionListener(listener);

    }

    private void addActionListeners(ActionListener listener) {
        vista.conexionItem.addActionListener(listener);
        vista.salirItem.addActionListener(listener);
        vista.altaButton.addActionListener(listener);
        vista.borrarButton.addActionListener(listener);
        vista.modificarButton.addActionListener(listener);
        vista.listarCochesButton.addActionListener(listener);
        vista.listarPropietariosButton.addActionListener(listener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        switch (comando){
            case "Salir":
                break;
            case "Alta":
                break;
            case "Listar":
                break;
            case "Modificar":
                break;
            case "Borrar":
                break;
            case "listarPropietarios":
                break;
        }

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }
}
