package com.practicaUD4.gui;

import com.github.lgooddatepicker.components.DatePicker;
import com.practicaUD4.base.Puzzle;
import com.practicaUD4.base.Editorial;
import com.practicaUD4.base.Sede;

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
    JTextField txtMediaClientes;
    JTextField txtBuscarEditorial;
    JTextField txtMediaVentas;
    JList listEditorial;
    DatePicker dateFundacion;
    JButton btnNuevaEditorial;
    JButton btnModificarEditorial;
    JButton btnEliminarEditorial;
    //Sede
    JList listSede;
    JTextField txtBuscarSede;
    JTextField txtNombreSede;
    JButton nuevaSedeBtn;
    JButton modificarSedeBtn;
    JButton eliminarSedeBtn;
    JComboBox comboSedes;
    DatePicker dateCreacion;

    DefaultListModel<Puzzle> dlmPuzzles;
    DefaultListModel<Editorial> dlmEditoriales;
    DefaultListModel<Sede> dlmSedes;

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

        dlmSedes = new DefaultListModel<Sede>();
        listSede.setModel(dlmSedes);

        dateFabricacion.getComponentToggleCalendarButton().setText("Calendario");
        dateCreacion.getComponentToggleCalendarButton().setText("Calendario");
        dateFundacion.getComponentToggleCalendarButton().setText("Calendario");
    }
}
