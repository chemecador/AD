package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by DAM on 13/12/2021.
 */
public class OptionDialog extends JDialog{
    private JPanel panel1;
    JTextField tfIP;
    JTextField tfUser;
    JPasswordField pfPass;
    JPasswordField pfAdmin;
    JButton btnOpcionesGuardar;

    private Frame owner;

    /**constructor de la clase
     * @param owner propietario del dialog
     */
    public OptionDialog(Frame owner) {
        super(owner,"Opciones",true);
        this.owner=owner;
        initDialog();
    }

    private void initDialog() {
        this.setContentPane(panel1);
        this.panel1.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.pack();
        this.setSize(new Dimension(this.getWidth()+200,this.getHeight()));
        this.setLocationRelativeTo(owner);
    }



}
