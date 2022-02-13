package gui;

import com.github.lgooddatepicker.components.DateTimePicker;

import javax.swing.*;
import java.awt.*;

public class Vista extends JFrame{
    private JFrame frame;
    private JPanel panel1;

    JTextField txtId;
    JTextField txtEditorial;
    JTextField txtModelo;
    JTextField txtIsbn;
    JTextField txtPrecio;
    JTextField txtComprador;
    DateTimePicker dateTimePicker;

    JButton altaButton;
    JButton listarButton;
    JButton modificarButton;
    JButton borrarButton;
    JButton listarCompradoresButton;

    JList listPuzzles;
    JList listPuzzlesCompradores;
    JList listCompradores;

    DefaultListModel dlm;
    DefaultListModel dlmCompradores;
    DefaultListModel dlmPuzzlesCompradores;

    JMenuItem conexionItem;
    JMenuItem salirItem;

    public Vista(){
        frame = new JFrame("Vista");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        this.setSize(new Dimension(this.getWidth()+300, this.getHeight()+200));
        frame.setLocationRelativeTo(null);

        crearMenu();
        crearModelos();

    }

    private void crearModelos() {
        dlm = new DefaultListModel();
        listPuzzles.setModel(dlm);
        dlmCompradores = new DefaultListModel();
        listCompradores.setModel(dlmCompradores);
        dlmPuzzlesCompradores = new DefaultListModel();
        listPuzzlesCompradores.setModel(dlmPuzzlesCompradores);
    }

    private void crearMenu() {
        JMenuBar barra = new JMenuBar();
        JMenu menu = new JMenu("Archivo");

        conexionItem = new JMenuItem("Conectar");
        conexionItem.setActionCommand("Conectar");

        salirItem = new JMenuItem("Salir");
        salirItem.setActionCommand("Salir");

        menu.add(conexionItem);
        menu.add(salirItem);
        barra.add(menu);
        frame.setJMenuBar(barra);
    }
}
