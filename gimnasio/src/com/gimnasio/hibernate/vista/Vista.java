package com.gimnasio.hibernate.vista;

import com.gimnasio.hibernate.vista.enums.Instalaciones;
import com.gimnasio.hibernate.vista.enums.Tarifas;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.*;

/**
 * Clase Vista que hereda de JFrame que permite la utilización de la interfaz
 */
public class Vista extends JFrame {
    private JPanel panel1;
    private JFrame frame;

    private JTabbedPane tabbedPane1;
    public JTextField txtSociosNombre;

    public JButton botonSocRegistrar;
    public JButton botonSocModificar;
    public JTextField txtInsNombre;
    public JButton botonInstRegistro;

    public JButton botonActRegistroEliminar;
    public JTextField txtMatNombre;
    public JButton botonMatRegistrar;
    public JComboBox comboActDispnible;
    public JButton btnProvRegistrar;
    public JTextField txtGerenteNombre;
    public JButton btnGereRegistrar;
    public JTextField txtPedidoDesc;
    public JButton btnPedidoRegistrar;

    public JTextField txtSociosApellidos;
    public JTextField txtSociosDni;
    public DatePicker DatePickerSocios;
    public JTextField txtSociosTarifa;
    public JTextField txtSociosInstructor;
    public JButton botonSocEliminar;
    public JTextField txtInsApellidos;
    public DatePicker DatePickerInstruct;
    public JTextField txtInsCodigo;
    public JButton botonInstModificar;
    public JButton botonInstEliminar;
    public JButton botonActRegistro;
    public JButton botonActModificar;
    public JButton botonMatModificar;
    public JButton botonMatEliminar;
    public DatePicker DatePickerPedidos;
    public JTextField txtPedidoCantidad;
    public JButton btnPedidoModificar;
    public JButton btnPedidoEliminar;
    public JTextField txtProvTipo;
    public JTextField txtProvDirecc;
    public JTextField txtProvTelef;
    public JTextField txtProvFax;
    public JButton btnProvModificar;
    public JButton btnProvEliminar;
    public JTextField txtGerenteApellidos;
    public JTextField txtGerenteCodigo;
    public JTextField txtGerenteDireccion;
    public JButton btnGereModificar;
    public JButton btnGereEliminar;
    public JTextField txtActTitulo;
    public JTextField txtActInstalacion;
    public JTextField txtActHSemanal;
    public JTextField txtActPrecio;

    public JList ListSocio;
    public JList listaInstructor;
    public JList ListActividad;
    public JList ListMaterial;
    public JList ListProveedor;
    public JList listaGerente;
    public JList ListaPedido;
    public JList listInstructoresFiltro;

    public JList listActividadesVerInstructor;
    public JList listProvVerMat;


    public JComboBox comboSociosTarifa;
    public JComboBox comboSociosInstructor;
    public JButton btnInstVerSocios;
    public JButton btnActVerSocios;
    public JComboBox comboInstAct;
    public JButton btnProvVerMat;
    public JComboBox comboActividadesInstalacion;
    public JComboBox comboSocActividad;
    public JComboBox comboPedidoProveedor;
    public JComboBox comboPedidoMaterial;

    DefaultListModel dlmSocio;
    DefaultListModel dlmInstructor;
    DefaultListModel dlmActividad;
    DefaultListModel dlmMaterial;
    DefaultListModel dlmProveedor;
    DefaultListModel dlmGerente;
    DefaultListModel dlmPedido;


    //Filtros
    DefaultListModel dlmInstructorFiltroSocio;
    DefaultListModel dlmMaterialProveedor;
    DefaultListModel dlmActividadesVerInstructor;

    JMenuItem conexionItem;
    JMenuItem salirItem;
    JMenuItem actualizarItem;


    /**
     * Constructor de clase vacío que inizializa lo necesario
     */
    public Vista(){
        frame = new JFrame("Vista");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        this.setSize(new Dimension(this.getWidth()+500, this.getHeight()+200));
        frame.setLocationRelativeTo(null);

        crearMenu();
        setCombos();
        crearModelos();

    }

    /**
     * Métodoque permite crear los modelos, es decir añadir DLM a las listas
     */
    private void crearModelos() {
        dlmSocio = new DefaultListModel();
        ListSocio.setModel(dlmSocio);

        dlmInstructor = new DefaultListModel();
        listaInstructor.setModel(dlmInstructor);

        dlmActividad = new DefaultListModel();
        ListActividad.setModel(dlmActividad);

        dlmMaterial = new DefaultListModel();
        ListMaterial.setModel(dlmMaterial);

        dlmProveedor = new DefaultListModel();
        ListProveedor.setModel(dlmProveedor);

        dlmPedido = new DefaultListModel();
        ListaPedido.setModel(dlmPedido);

        dlmInstructorFiltroSocio = new DefaultListModel();
        listInstructoresFiltro.setModel(dlmInstructorFiltroSocio);

        dlmMaterialProveedor = new DefaultListModel();
        listProvVerMat.setModel(dlmMaterialProveedor);

    }

    /**
     * Permite seleccionar los enums y meterlos en los comboBoxes
     */
    private void setCombos(){
        for(Instalaciones constant : Instalaciones.values()) { comboActividadesInstalacion.addItem(constant.getValor()); }
        comboActividadesInstalacion.setSelectedIndex(-1);

        comboActDispnible.addItem("SI");
        comboActDispnible.addItem("NO");

        for(Tarifas constant : Tarifas.values()) { comboSociosTarifa.addItem(constant.getValor()); }
        comboSociosTarifa.setSelectedIndex(-1);
    }

    /**
     * Método que permite crear un menú y añadir actionCommands
     */
    private void crearMenu() {
        JMenuBar barra = new JMenuBar();
        JMenu menu = new JMenu("Archivo");

        conexionItem = new JMenuItem("Conectar");
        conexionItem.setActionCommand("Conectar");


        actualizarItem = new JMenuItem("Actualizar");
        actualizarItem.setActionCommand("Actualizar");

        salirItem = new JMenuItem("Salir");
        salirItem.setActionCommand("Salir");

        menu.add(conexionItem);
        menu.add(actualizarItem);
        menu.add(salirItem);
        barra.add(menu);
        frame.setJMenuBar(barra);
    }
}
