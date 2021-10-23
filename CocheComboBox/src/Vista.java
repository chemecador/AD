import com.alexgg.cochecombobox.Coche;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.LinkedList;

public class Vista {
    //creados manualmente
    private JFrame frame;
    //creados automaticamente
    private JPanel panel1;
    private JTextField marcaTxt;
    private JTextField modeloTxt;
    private JComboBox comboBox;
    private JButton altaCocheBtn;
    private JButton mostrarCocheBtn;
    private JLabel lblCoche;
    //añadidos manualmente
    private LinkedList<Coche> lista;
    private DefaultComboBoxModel<Coche> dcbm;

    public Vista() {
        frame = new JFrame("Vista");
        //quitar new Vista()
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        //centrar
        frame.setLocationRelativeTo(null);

        crearMenu();
        //inicializar lista LinkedList
        lista = new LinkedList<>();
        //inicializar comboBoxModel
        dcbm = new DefaultComboBoxModel<>();
        //aplico modelo al comboBox
        comboBox.setModel(dcbm);


       // crearMenu();

        //boton alta coche (listener)
        altaCocheBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //dar de alta coche
                altaCoche(marcaTxt.getText(),modeloTxt.getText());
                //refrescar cambios
                refrescarComboBox();
            }
        });

        //boton mostrar coche (listener)
        mostrarCocheBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //muestro el coche seleccionado en la label o campo de texto
                Coche seleccionado =(Coche) dcbm.getSelectedItem();
                lblCoche.setText(seleccionado.toString());
            }
        });

        //listener comboBox
        comboBox.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (e.getKeyCode()==KeyEvent.VK_DELETE) {
                    lista.remove(dcbm.getSelectedItem());
                    refrescarComboBox();
                }
            }
        });
    }

    //menu para importar y exportar XML
    private void crearMenu() {
        JMenuBar barra = new JMenuBar();
        JMenu menu = new JMenu("Archivo");
        JMenuItem itemExportarXML = new JMenuItem("Exportar XML");
        JMenuItem itemImportarXML = new JMenuItem("Importar XML");
        //listener exportar XML

        itemExportarXML.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser selectorArchivo = new JFileChooser();
                int opcionSeleccionada = selectorArchivo.showSaveDialog(null);
                if (opcionSeleccionada == JFileChooser.APPROVE_OPTION){
                    File fichero = selectorArchivo.getSelectedFile();
                    exportarXML(fichero);
                }
            }
        });
        //listener importar XML
        itemImportarXML.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser selectorArchivo = new JFileChooser();
                int opcionSeleccionada = selectorArchivo.showOpenDialog(null);
                File fichero = selectorArchivo.getSelectedFile();
                importarXML(fichero);
                refrescarComboBox();
            }
        });

        menu.add(itemExportarXML);
        menu.add(itemImportarXML);
        barra.add(menu);
        frame.setJMenuBar(barra);
    }

    private void altaCoche(String marca, String modelo) {
        lista.add(new Coche(marca, modelo));
    }
    private void refrescarComboBox(){
        dcbm.removeAllElements();
        for (Coche coche : lista){
            dcbm.addElement(coche);
        }
    }
    private void exportarXML(File fichero){

    }
    private void importarXML(File fichero){

    }
    public static void main(String[] args) {
        Vista vista = new Vista();
    }
}
