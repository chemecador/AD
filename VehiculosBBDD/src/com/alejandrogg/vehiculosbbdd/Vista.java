package com.alejandrogg.vehiculosbbdd;

import com.github.lgooddatepicker.components.DateTimePicker;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Vista {
    private JPanel panel1;
    JTextField txtMatricula;
    JTextField txtMarca;
    JButton btnBuscar;
    JButton btnNuevo;
    JButton btnEliminar;
    JTable tabla;
    JTextField txtBuscar;
    JLabel lblAccion;
    DateTimePicker dateTimePicker;
    JTextField txtModelo;
    JFrame frame;
    DefaultTableModel dtm;
    JMenuItem itemConectar;
    JMenuItem itemSalir;

    public Vista() {
        frame = new JFrame("Vista");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        crearMenu();
        //inicializar el dtm editable
        dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0) {
                    return false;
                }
                return true;
            }
        };
        tabla.setModel(dtm);
        frame.pack();
        frame.setVisible(true);
    }

    private void crearMenu() {
        itemConectar = new JMenuItem("Conectar");
        itemConectar.setActionCommand("Conectar");
        itemSalir = new JMenuItem("Salir");
        itemSalir.setActionCommand("Salir");

        JMenu menuArchivo = new JMenu("Archivo");
        menuArchivo.add(itemConectar);
        menuArchivo.add(itemSalir);

        JMenuBar barraMenu = new JMenuBar();
        barraMenu.add(menuArchivo);

        frame.setJMenuBar(barraMenu);
    }
}
