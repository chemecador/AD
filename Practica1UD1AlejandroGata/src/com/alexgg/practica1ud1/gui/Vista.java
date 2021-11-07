package com.alexgg.practica1ud1.gui;

import com.alexgg.practica1ud1.base.Producto;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;

/**
 * Clase que contiene la interfaz gráfica.
 * */
public class Vista {
    private JPanel panel1;
    public JTextField idProductoTxt;
    public JTextArea piezasFigurasJugadoresTxt;
    public JButton nuevoBtn;
    public JButton importarBtn;
    public JButton exportarBtn;
    public DatePicker fechaDP;
    public JList list;
    public JRadioButton puzzleRadioBtn;
    public JRadioButton maquetaRadioBtn;
    public JRadioButton juegoDeMesaRadioBtn;
    public JTextField precioTxt;
    public JTextField marcaTxt;
    public JLabel piezasFigurasJugadoresLbl;
    public JFrame frame;
    public DefaultListModel<Producto> dlmProducto;

    //Constructor
    public Vista() {
        frame = new JFrame("VehiculosMVC");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        initComponents();
    }
    //Iniciar los componentes
    private void initComponents() {
        dlmProducto = new DefaultListModel<Producto>();
        list.setModel(dlmProducto);
    }
}

