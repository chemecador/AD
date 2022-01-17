package gui;

import base.enums.GenerosPuzzles;
import base.enums.Reputacion;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Vista extends JFrame {
    private final static String TITULOFRAME = "Aplicación Varias Tablas";
    private JTabbedPane tabbedPane;
    private JPanel panel1;
    private JPanel jPanelPuzzle;
    private JPanel jPanelComprador;
    private JPanel jPanelEditorial;

    //PUZZLES
    JTextField txtTitulo;
    JComboBox comboComprador;
    JComboBox comboEditorial;
    JComboBox comboGenero;
    DatePicker fecha;
    JTextField txtIsbn;
    JTextField txtPrecio;
    JTable puzzlesTabla;
    JButton anadirPuzzle;
    JButton buscarPuzzle;
    JButton modificarPuzzle;
    JButton eliminarPuzzle;

    //COMPRADORES
    JTextField txtNombre;
    JTextField txtApellidos;
    DatePicker fechaCompra;
    JTextField txtPais;
    JTable compradoresTabla;
    JButton eliminarComprador;
    JButton buscarComprador;
    JButton anadirComprador;
    JButton modificarComprador;

    //EDITORIAL
    JTextField txtNombreEditorial;
    JTextField txtEmail;
    JTextField txtTelefono;
    JComboBox comboRepu;
    JTextField txtWeb;
    JTable editorialesTabla;
    JButton eliminarEditorial;
    JButton buscarEditorial;
    JButton anadirEditorial;
    JButton modificarEditorial;
    JTextField txtAnti;
    JTextField txtDni;

    //BUSQUEDA
    JLabel etiquetaEstado;

    //DEFAULT TABLE MODEL
    DefaultTableModel dtmEditoriales;
    DefaultTableModel dtmCompradores;
    DefaultTableModel dtmPuzzles;

    //BARRA MENU
    JMenuItem itemOpciones;
    JMenuItem itemCrearBBDD;
    JMenuItem itemBorrarBBDD;
    JMenuItem itemDesconectar;
    JMenuItem itemSalir;

    //CUADRO DIALOGO
    OptionDialog optionDialog;
    JDialog adminPasswordDialog;
    JButton btnValidate;
    JPasswordField adminPassword;

    public Vista() {
        super(TITULOFRAME);
        initFrame();
    }

    public void initFrame() {
        this.jPanelPuzzle.setBackground(Color.LIGHT_GRAY);
        this.panel1.setBackground(Color.LIGHT_GRAY);
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setSize(new Dimension(this.getWidth() + 200, this.getHeight() + 100));
        this.setLocationRelativeTo(null);
        //creo cuadro de dialogo
        optionDialog = new OptionDialog(this);
        setMenu();
        setAdminDialog();
        setEnumComboBox();
        setTableModels();
    }

    private void setTableModels() {
        this.dtmPuzzles = new DefaultTableModel();
        this.puzzlesTabla.setModel(dtmPuzzles);
        this.dtmCompradores = new DefaultTableModel();
        this.compradoresTabla.setModel(dtmCompradores);
        this.dtmEditoriales = new DefaultTableModel();
        this.editorialesTabla.setModel(dtmEditoriales);
        this.compradoresTabla.setBackground(Color.LIGHT_GRAY);
        this.puzzlesTabla.setBackground(Color.LIGHT_GRAY);
        this.editorialesTabla.setBackground(Color.LIGHT_GRAY);
    }

    private void setMenu() {
        JMenuBar mbBar = new JMenuBar();
        JMenu menu = new JMenu("Archivo");
        //por cada item que tenga funcionalidad tiene un ActionCommand
        itemOpciones = new JMenuItem("Opciones");
        itemOpciones.setActionCommand("Opciones");
        itemCrearBBDD = new JMenuItem("Crear base de datos");
        itemCrearBBDD.setActionCommand("CrearBBDD");
        itemBorrarBBDD = new JMenuItem("Borrar base de datos");
        itemBorrarBBDD.setActionCommand("BorrarBBDD");
        itemDesconectar = new JMenuItem("Desconectar");
        itemDesconectar.setActionCommand("Desconectar");
        itemSalir = new JMenuItem("Salir");
        itemSalir.setActionCommand("Salir");
        menu.add(itemOpciones);
        menu.add(itemCrearBBDD);
        menu.add(itemBorrarBBDD);
        menu.add(itemDesconectar);
        menu.add(itemSalir);
        mbBar.add(menu);
        //centrar en horizontal
        mbBar.add(Box.createHorizontalGlue());
        this.setJMenuBar(mbBar);
    }

    private void setEnumComboBox() {
        for (Reputacion constant : Reputacion.values()) {
            comboRepu.addItem(constant.getValor());
        }
        comboRepu.setSelectedIndex(-1);
        for (GenerosPuzzles constant : GenerosPuzzles.values()) {
            comboGenero.addItem(constant.getValor());
        }
        comboGenero.setSelectedIndex(-1);
    }

    private void setAdminDialog() {
        btnValidate = new JButton("Validar");
        btnValidate.setActionCommand("abrirOpciones");
        adminPassword = new JPasswordField();
        adminPassword.setPreferredSize(new Dimension(100, 26));
        Object[] options = new Object[]{adminPassword, btnValidate};
        JOptionPane jop = new JOptionPane("Introduce la contraseña",
                JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION, null, options);
        adminPasswordDialog = new JDialog(this, "Opciones", true);
        adminPasswordDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        adminPasswordDialog.setContentPane(jop);
        adminPasswordDialog.pack();
        adminPasswordDialog.setLocationRelativeTo(this);
    }

}
