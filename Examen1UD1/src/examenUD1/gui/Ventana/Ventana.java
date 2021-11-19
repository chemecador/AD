package examenUD1.gui.Ventana;

import com.github.lgooddatepicker.components.DatePicker;
import examenUD1.base.Ordenador;

import javax.swing.*;

public class Ventana {
    private JPanel panel1;
    public JFrame frame;
    public JRadioButton portatilRadioButton;
    public JRadioButton sobremesaRadioButton;
    public JTextField txtNombre;
    public JTextField txtCantidad;
    public JTextField txtPrecio;
    public JButton nuevoBtn;
    public JButton exportarbtn;
    public JButton importarbtn;
    public JLabel lblratonTounPad;
    public JTextField txtRatonTouchPad;
    public JList list1;
    public DatePicker datepicker;
    public JButton btnEliminar;

    //Elementos creados por mi
    public DefaultListModel<Ordenador> dlmOrdenador;

    public Ventana(){
        frame = new JFrame("Ventana");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        portatilRadioButton.setSelected(true);

        initComponents();
    }

    private void initComponents() {
        dlmOrdenador=new DefaultListModel<Ordenador>();
        list1.setModel(dlmOrdenador);
    }
}
