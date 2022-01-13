package gui;

import base.enums.GenerosLibros;
import base.enums.TiposEditoriales;
import com.github.lgooddatepicker.components.DatePicker;
import jdk.nashorn.internal.scripts.JD;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Vista extends JFrame {
    private final static String TITULOFRAME="Aplicación Varias Tablas";
    private JTabbedPane tabbedPane;
    private JPanel panel1;
    private JPanel JPanelLibro;
    private JPanel JPanelAutor;
    private JPanel JPanelEditorial;

    //LIBROS
    JTextField txtTitulo;
    JComboBox comboAutor;
    JComboBox comboEditorial;
    JComboBox comboGenero;
    DatePicker fecha;
    JTextField txtIsbn;
    JTextField txtPrecioLibro;
    JTable librosTabla;
    JButton anadirLibro;
    JButton modificarLibro;
    JButton eliminarLibro;

    //AUTORES
    JTextField txtNombre;
    JTextField txtApellidos;
    DatePicker fechaNacimiento;
    JTextField txtPais;
    JTable autoresTabla;
    JButton eliminarAutor;
    JButton anadirAutor;
    JButton modificarAutor;

    //EDITORIAL
    JTextField txtNombreEditorial;
    JTextField txtEmail;
    JTextField txtTelefono;
    JComboBox comboTipoEditorial;
    JTextField txtWeb;
    JTable editorialesTabla;
    JButton eliminarEditorial;
    JButton anadirEditorial;
    JButton modificarEditorial;

    //BUSQUEDA
    JLabel etiquetaEstado;

    //DEFAULT TABLE MODEL
    DefaultTableModel dtmEditoriales;
    DefaultTableModel dtmAutores;
    DefaultTableModel dtmLibros;

    //BARRA MENU
    JMenuItem itemOpciones;
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
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setSize(new Dimension(this.getWidth()+200,this.getHeight()+100));
        this.setLocationRelativeTo(this);
        //creo cuadro de dialogo
        optionDialog=new OptionDialog(this);
        setMenu();
        setAdminDialog();
        setEnumComboBox();
        setTableModels();
    }

    private void setTableModels() {
        this.dtmLibros=new DefaultTableModel();
        this.librosTabla.setModel(dtmLibros);
        this.dtmAutores=new DefaultTableModel();
        this.autoresTabla.setModel(dtmAutores);
        this.dtmEditoriales=new DefaultTableModel();
        this.editorialesTabla.setModel(dtmEditoriales);
    }

    private void setMenu() {
        JMenuBar mbBar = new JMenuBar();
        JMenu menu = new JMenu("Archivo");
        //por cada item que tenga funcionalidad tiene un ActionCommand
        itemOpciones= new JMenuItem("Opciones");
        itemOpciones.setActionCommand("Opciones");
        itemDesconectar= new JMenuItem("Desconectar");
        itemDesconectar.setActionCommand("Desconectar");
        itemSalir=new JMenuItem("Salir");
        itemSalir.setActionCommand("Salir");
        menu.add(itemOpciones);
        menu.add(itemDesconectar);
        menu.add(itemSalir);
        mbBar.add(menu);
        //centrar en horizontal
        mbBar.add(Box.createHorizontalGlue());
        this.setJMenuBar(mbBar);
    }

    private void setEnumComboBox() {
        for (TiposEditoriales constant: TiposEditoriales.values()) {
            comboTipoEditorial.addItem(constant.getValor());
        }
        comboTipoEditorial.setSelectedIndex(-1);
        for (GenerosLibros constant: GenerosLibros.values()) {
            comboGenero.addItem(constant.getValor());
        }
        comboGenero.setSelectedIndex(-1);
    }

    private void setAdminDialog() {
        btnValidate= new JButton("Validar");
        btnValidate.setActionCommand("abrirOpciones");
        adminPassword= new JPasswordField();
        adminPassword.setPreferredSize(new Dimension(100,26));
        Object[] options = new Object[] {adminPassword,btnValidate};
        JOptionPane jop =new JOptionPane("Introduce la contraseña",
                JOptionPane.WARNING_MESSAGE,JOptionPane.YES_NO_OPTION,null,options);
        adminPasswordDialog=new JDialog(this,"Opciones",true);
        adminPasswordDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        adminPasswordDialog.setContentPane(jop);
        adminPasswordDialog.pack();
        adminPasswordDialog.setLocationRelativeTo(this);
    }

}
