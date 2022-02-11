package gui;

import com.github.lgooddatepicker.components.DateTimePicker;

import javax.swing.*;

public class Vista {
    private JFrame frame;
    private JPanel panel1;
    JTextField txtId;
    JTextField txtMatricula;
    JTextField txtMarca;
    JTextField txtModelo;
    DateTimePicker datetimePicker;
    JTextField txtPropietario;
    JButton altaButton;
    JButton modificarButton;
    JButton borrarButton;
    JButton listarCochesButton;
    JList listCoches;
    JButton listarPropietariosButton;
    JList listPropietarios;

    JMenuItem conexionItem;
    JMenuItem salirItem;

    DefaultListModel dlm;
    DefaultListModel dlmPropietarios;
    DefaultListModel dlmCochesPropietarios;

    public Vista() {
        frame = new JFrame("Vista");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        crearModelos();
        crearMenu();
    }

    private void crearModelos() {
        dlm = new DefaultListModel();
        listCoches.setModel(dlm);
        dlmPropietarios = new DefaultListModel();
        listPropietarios.setModel(dlmPropietarios);
        dlmCochesPropietarios = new DefaultListModel();
        listCochesPropietario.setModel(dlmCochesPropietarios);
    }

    private void crearMenu() {
        JMenuBar barra = new JMenuBar();
        JMenu menu = new JMenu("Archivo");
        conexionItem = new JMenuItem("Conectar");
        conexionItem.setActionCommand("Conectar");
        salirItem=new JMenuItem("Salir");
        salirItem.setActionCommand("Salir");
        menu.add(conexionItem);
        menu.add(salirItem);
        barra.add(menu);
        frame.setJMenuBar(barra);
    }
}
