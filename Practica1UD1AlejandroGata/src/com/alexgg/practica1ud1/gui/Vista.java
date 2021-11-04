package com.alexgg.practica1ud1.gui;

import com.alexgg.practica1ud1.base.Producto;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;

public class Vista {
    private JPanel panel1;
    public JTextField idProductoTxt;
    public JTextArea piezasJugadoresTxt;
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
    public JList list1;
    public JFrame frame;
    public DefaultListModel<Producto> dlmProducto;

    public Vista() {
        frame = new JFrame("Vista");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        dlmProducto = new DefaultListModel<Producto>();
        list1.setModel(dlmProducto);
    }
}

