package com.alejandrogg.libreriabbddmvc;

import com.github.lgooddatepicker.components.DateTimePicker;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Vista {
    private JPanel panel1;

    JTextField txtIsbn;
    JTextField txtTitulo;
    JTextField txtAutor;

    JButton insertarBtn;
    JButton eliminarBtn;
    JButton listarBtn;

    JTable table1;

    DateTimePicker dateTimePicker;

    //para mostrar la tabla
    DefaultTableModel dtm;

    public Vista() {
        JFrame frame = new JFrame("Vista");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        //llamamos a un metodo que mostrará en el dtm las cabeceras
        iniciarTabla();
    }

    private void iniciarTabla() {
        dtm=new DefaultTableModel();
        table1.setModel(dtm);

        Object[] cabeceras ={"id","isbn","titulo","autor","fecha publicación"};

        dtm.setColumnIdentifiers(cabeceras);
    }
}
