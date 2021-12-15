package com.elenajif.vehiculosbbdd;

import com.github.lgooddatepicker.components.DateTimePicker;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Created by DAM on 02/12/2021.
 */
public class Vista {
    private JPanel panel1;

    JTextField txtMatricula;
    JTextField txtMarca;
    JTextField txtModelo;
    JTextField txtBuscar;

    DateTimePicker dateTimePicker;

    JButton btnBuscar;
    JButton btnNuevo;
    JButton btnEliminar;

    JTable tabla;

    JLabel lblAccion;

    JFrame frame;
    DefaultTableModel dtm;

    JMenuItem itemConectar;
    JMenuItem itemSalir;
    JMenuItem itemCrearTabla;

    public Vista() {
        frame = new JFrame("Vista");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //inicializamos el dtm haciendolo editable
        //boton derecho overwrite
        //isCellEditable
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

        crearMenu();
        frame.pack();
        frame.setVisible(true);
    }

    private void crearMenu() {
        itemConectar= new JMenuItem("Conectar");
        itemConectar.setActionCommand("Conectar");
        itemCrearTabla=new JMenuItem("Crear Tabla Coches");
        itemCrearTabla.setActionCommand("CrearTablaCoches");//case
        itemSalir=new JMenuItem("Salir");
        itemSalir.setActionCommand("Salir");

        JMenu menuArchivo=new JMenu("Archivo");
        menuArchivo.add(itemConectar);
        menuArchivo.add(itemCrearTabla);
        menuArchivo.add(itemSalir);

        JMenuBar barraMenu = new JMenuBar();
        barraMenu.add(menuArchivo);

        frame.setJMenuBar(barraMenu);
    }
}
