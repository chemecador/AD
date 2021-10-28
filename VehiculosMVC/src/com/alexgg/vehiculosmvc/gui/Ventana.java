package com.alexgg.vehiculosmvc.gui;

import com.alexgg.vehiculosmvc.base.Vehiculo;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;

public class Ventana {
    private JPanel panel1;
    public JRadioButton cocheRadioButton;
    public JRadioButton motoRadioButton;
    public JTextField matriculaTxt;
    public JTextField marcaTxt;
    public JTextField modeloTxt;
    public JTextField kmsPlazasTxt;
    public JButton nuevoBtn;
    public JButton exportarBtn;
    public JButton importarBtn;
    public JList list1;
    public DatePicker fechaMatriculacionDP;
    public JFrame frame;
    public DefaultListModel<Vehiculo> dlmVehiculo;

    public Ventana(){
        frame = new JFrame("VehiculosMVC");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents(){
        dlmVehiculo = new DefaultListModel<Vehiculo>();
        list1.setModel(dlmVehiculo);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
