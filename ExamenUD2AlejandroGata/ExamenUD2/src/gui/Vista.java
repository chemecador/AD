package gui;

import com.github.lgooddatepicker.components.DatePicker;
import enums.Instalaciones;
import enums.Tarifas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Vista extends JFrame{
    private final static String TITULOFRAME = "gymbbdd";
    private JPanel panel1;
    private JTabbedPane tabbedPane1;

    protected JTextField txtNombreSoc;
    protected JTextField txtApellidosSoc;
    protected JTextField txtDniSoc;
    protected JTextField txtNombreIns;
    protected JTextField txtApellidosIns;
    protected JTextField txtCodInsIns;
    protected JTextField txtTituloAct;
    protected JTextField txtHorasAct;
    protected JTextField txtPrecioAct;

    protected JComboBox socInsCombo;
    protected JComboBox socActCombo;
    protected JComboBox actCombo;
    protected JComboBox socTatifCombo;

    protected DatePicker DatePickerSocios;
    protected DatePicker DatePickerInst;

    protected JScrollPane TablaDefault;
    protected JScrollPane TableInst;
    protected JScrollPane tableAct;
    protected JTable tablaSocios;
    protected JTable tablaInstructores;
    protected JTable tablaActividades;

    protected JButton clienButtonMod;
    protected JButton clienButtonBorrar;
    protected JButton clienButtonNuevo;
    protected JButton clienButtonFiltrar;
    protected JButton instButtonNuevo;
    protected JButton instButtonBorrar;
    protected JButton instButtonMod;
    protected JButton actNuevButton;
    protected JButton actBorrButton;
    protected JButton actModButton;

    DefaultTableModel dtmSocios;
    DefaultTableModel dtmInstructores;
    DefaultTableModel dtmActividades;
    JMenuItem itemOpciones;
    JMenuItem itemDesconectar;
    JMenuItem itemSalir;
    OptionDialog optionDialog;
    JDialog adminPasswordDialog;
    JButton btnValidate;
    JPasswordField adminPassword;


    public Vista() {
        super(TITULOFRAME);
        initFrame();
    }

    private void initFrame() {
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setSize(new Dimension(this.getWidth()+500, this.getHeight()));
        this.setLocationRelativeTo(null);
        optionDialog = new OptionDialog(this);
        setMenu();
        setAdminDialog();
        setEnumComboBox();
        setTableModels();
    }

    private void setTableModels() {
        this.dtmSocios = new DefaultTableModel();
        this.tablaSocios.setModel(dtmSocios);
        this.dtmInstructores = new DefaultTableModel();
        this.tablaInstructores.setModel(dtmInstructores);
        this.dtmActividades = new DefaultTableModel();
        this.tablaActividades.setModel(dtmActividades);
    }

    private void setMenu(){
        JMenuBar mbBar = new JMenuBar();
        JMenu menu = new JMenu("Archivo");
        itemOpciones = new JMenuItem("Opciones");
        itemOpciones.setActionCommand("Opciones");
        itemDesconectar = new JMenuItem("Desconectar");
        itemDesconectar.setActionCommand("Desconectar");
        itemSalir = new JMenuItem("Salir");
        itemSalir.setActionCommand("Salir");
        menu.add(itemOpciones);
        menu.add(itemDesconectar);
        menu.add(itemSalir);
        mbBar.add(menu);
        mbBar.add(Box.createHorizontalGlue());
        this.setJMenuBar(mbBar);
    }

    private void setEnumComboBox() {
        for (Instalaciones constant : Instalaciones.values()) {
            actCombo.addItem(constant.getValor());
        }
        actCombo.setSelectedIndex(-1);
        for (Tarifas constant : Tarifas.values()) {
            socTatifCombo.addItem(constant.getValor());
        }
        socTatifCombo.setSelectedIndex(-1);
    }

    private void setAdminDialog() {
        btnValidate = new JButton("Validar");
        btnValidate.setActionCommand("abrirOpciones");
        adminPassword = new JPasswordField();
        adminPassword.setPreferredSize(new Dimension(100, 26));
        Object[] options = new Object[] {adminPassword, btnValidate};
        JOptionPane jop = new JOptionPane("Introduce la contraseña"
                , JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION, null, options);

        adminPasswordDialog = new JDialog(this, "Opciones", true);
        adminPasswordDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        adminPasswordDialog.setContentPane(jop);
        adminPasswordDialog.pack();
        adminPasswordDialog.setLocationRelativeTo(this);
    }

}

