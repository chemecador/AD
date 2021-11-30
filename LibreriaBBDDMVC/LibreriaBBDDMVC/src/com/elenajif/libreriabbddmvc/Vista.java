package com.elenajif.libreriabbddmvc;

import com.github.lgooddatepicker.components.DateTimePicker;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by PROFESOR on 16/11/2018.
 */
public class Vista {
    private JPanel panel1;
    JTextField txtIsbn;
    JTextField txtTitulo;
    JTextField txtAutor;
    JTable table1;
    JButton listarBtn;
    JButton insertarBtn;
    JButton eliminarBtn;
     DateTimePicker dateTimePicker;
    //Variable creada por mi
     DefaultTableModel dtm;

    public Vista() {
        JFrame frame = new JFrame("Vista");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        iniciarTabla();
    }

    private void iniciarTabla(){
        dtm = new DefaultTableModel();
        table1.setModel(dtm);

        Object[] cabeceras = {"id", "isbn", "titulo", "autor", "fecha publicacion"};

        dtm.setColumnIdentifiers(cabeceras);
    }

}
