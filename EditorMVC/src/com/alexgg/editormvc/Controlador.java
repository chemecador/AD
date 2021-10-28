package com.alexgg.editormvc;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

public class Controlador implements ActionListener {
    private Vista vista;
    private Modelo modelo;

    public Controlador(Vista v, Modelo m) {
        this.vista = v;
        this.modelo = m;
        asociarListeners(this);
    }

    private void asociarListeners(ActionListener listener) {

        vista.itemAbrir.addActionListener(listener);
        vista.itemGuardar.addActionListener(listener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String accion = e.getActionCommand();
        switch (accion) {
            case "abrir": {
                JFileChooser selectorFichero = new JFileChooser();
                int opcion = selectorFichero.showOpenDialog(null);
                if (opcion == JFileChooser.APPROVE_OPTION) {
                    File fichero = selectorFichero.getSelectedFile();
                    try {
                        vista.textArea.setText(modelo.cargarTexto(fichero));
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            break;
            case "guardar": {

                JFileChooser selectorFichero = new JFileChooser();
                int opcion = selectorFichero.showSaveDialog(null);
                if (opcion == JFileChooser.APPROVE_OPTION) {
                    File ficheroSeleccionado = selectorFichero.getSelectedFile();
                    try {
                        modelo.guardarTexto(ficheroSeleccionado, vista.textArea.getText());
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
                break;
            }
        }
    }
}
