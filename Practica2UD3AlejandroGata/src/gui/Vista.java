package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Clase Vista que hereda de JFrame que permite la utilización de la interfaz
 */
public class Vista extends JFrame {
    private JPanel panel1;
    private JFrame frame;

    private JTabbedPane tabbedPane1;
    public JTextField txtTitulo;

    public JButton nuevoPuzzleBtn;
    public JButton modificarPuzzleBtn;
    public JTextField txtNombreComprador;
    public JButton altaCompradorBtn;

    public JButton eliminarTiendaBtn;
    public JTextField txtNombreEditorial;
    public JButton altaEditorialBtn;
    public JComboBox comboActDispnible;

    public JTextField txtIsbn;
    public JTextField txtPrecio;
    public JButton eliminarPuzzleBtn;
    public JTextField txtApellidos;
    public JTextField txtDNI;
    public JButton modificarCompradorBtn;
    public JButton eliminarCompradorBtn;
    public JButton altaTiendaBtn;
    public JButton modificarTiendaBtn;
    public JButton modificarEditorialBtn;
    public JButton eliminarEditorialBtn;
    public JTextField txtNombreTienda;
    public JTextField txtTlfTienda;

    public JList listPuzzles;
    public JList listCompradores;
    public JList listTiendas;
    public JList listEditoriales;

    public JButton listarCompradorPuzzles;
    public JButton listarEditorialPuzzles;
    public JTextField txtTlfEditorial;
    public JComboBox comboEditoriales;
    public JComboBox comboTiendas;
    public JComboBox comboPuzzles;
    public JComboBox comboActividadesInstalacion;
    public JButton altaVentaBtn;
    public JButton eliminarVentaBtn;
    public JButton modificarVentaBtn;
    public JButton altaCompraBtn;
    public JButton modificarCompraBtn;
    public JButton eliminarCompraBtn;
    public JList listCompradorPuzzles;
    public JList listEditorialPuzzles;
    public JList listVentas;
    public JList listCompras;
    public JButton listarPuzzleCompradores;
    public JComboBox comboPuzzles2;
    public JComboBox comboComprador2;

    DefaultListModel dlmPuzzles;
    DefaultListModel dlmCompradores;
    DefaultListModel dlmTiendas;
    DefaultListModel dlmEditoriales;


    //Filtros
    DefaultListModel dlmCompradorPuzzles;
    DefaultListModel dlmEditorialPuzzles;
    DefaultListModel dlmVentas;
    DefaultListModel dlmCompras;

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
        //frame.pack();
        frame.setVisible(true);
        frame.setSize(new Dimension(800,430));
        frame.setLocationRelativeTo(null);

        crearMenu();
        crearModelos();

    }

    /**
     * Métodoque permite crear los modelos, es decir añadir DLM a las listas
     */
    private void crearModelos() {
        dlmPuzzles = new DefaultListModel();
        listPuzzles.setModel(dlmPuzzles);

        dlmCompradores = new DefaultListModel();
        listCompradores.setModel(dlmCompradores);

        dlmTiendas = new DefaultListModel();
        listTiendas.setModel(dlmTiendas);

        dlmEditoriales = new DefaultListModel();
        listEditoriales.setModel(dlmEditoriales);

        dlmEditorialPuzzles = new DefaultListModel();
        listEditorialPuzzles.setModel(dlmEditorialPuzzles);

        dlmVentas = new DefaultListModel();
        listVentas.setModel(dlmVentas);

        dlmCompras = new DefaultListModel();
        listCompras.setModel(dlmCompras);

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
