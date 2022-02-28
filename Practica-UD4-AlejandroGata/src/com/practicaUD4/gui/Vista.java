package com.practicaUD4.gui;

import com.github.lgooddatepicker.components.DatePicker;
import com.practicaUD4.base.Puzzle;
import com.practicaUD4.base.Editorial;
import com.practicaUD4.base.Vendedor;

import javax.swing.*;

/**
 * Clase Vista
 * @author
 */
public class Vista {
    //Campos
    private JPanel panel1;
    //Puzzle
    JTextField txtNombre;
    JTextField txtPrecio;
    JTextField txtBuscar;
    DatePicker dateFabricacion;
    JList listPuzzles;
    JButton btnNuevoPuzzle;
    JButton btnModificarPuzzle;
    JButton btnEliminarPuzzle;
    JTextField txtMarca;
    //Editorial
    private JTabbedPane tabbedPane1;
    JTextField txtNombreEditorial;
    JTextField txtDireccion;
    JTextField txtBuscarEditorial;
    JTextField txtMediaVentas;
    JList listEditorial;
    DatePicker dateCreacion;
    JButton btnNuevaEditorial;
    JButton btnModificarEditorial;
    JButton btnEliminarEditorial;
    //Vendedor
    JList listVendedor;
    JTextField txtBuscarVendedor;
    DatePicker dateNacimiento;
    JTextField txtNombreVendedor;
    JTextField txtSalario;
    JTextField txtApellidos;
    JComboBox comboEditorial;
    JButton nuevoVendedorBtn;
    JButton modificarVendedorBtn;
    JButton eliminarVendedorBtn;
    JList listEditorialPuzzles;

    DefaultListModel<Puzzle> dlmPuzzles;
    DefaultListModel<Editorial> dlmEditoriales;
    DefaultListModel<Vendedor> dlmVendedores;

    /**
     * Constructor de Vista()
     */
    public Vista() {
        JFrame frame = new JFrame("Vista");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        inicializar();
    }

    /**
     * inicializar(),inicializar modelos
     */
    private void inicializar(){
        dlmPuzzles = new DefaultListModel<Puzzle>();
        listPuzzles.setModel(dlmPuzzles);

        dlmEditoriales = new DefaultListModel<Editorial>();
        listEditorial.setModel(dlmEditoriales);

        dlmVendedores = new DefaultListModel<Vendedor>();
        listVendedor.setModel(dlmVendedores);

        dateFabricacion.getComponentToggleCalendarButton().setText("Calendario");
        dateCreacion.getComponentToggleCalendarButton().setText("Calendario");
        dateNacimiento.getComponentToggleCalendarButton().setText("Calendario");
    }
}
