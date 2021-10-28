import com.alexgg.cochecombobox.Coche;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.crypto.dsig.Transform;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
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
                altaCoche(marcaTxt.getText(), modeloTxt.getText());
                //refrescar cambios
                refrescarComboBox();
            }
        });

        //boton mostrar coche (listener)
        mostrarCocheBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //muestro el coche seleccionado en la label o campo de texto
                Coche seleccionado = (Coche) dcbm.getSelectedItem();
                lblCoche.setText(seleccionado.toString());
            }
        });

        //listener comboBox
        comboBox.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (e.getKeyCode() == KeyEvent.VK_DELETE) {
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
                if (opcionSeleccionada == JFileChooser.APPROVE_OPTION) {
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

    private void refrescarComboBox() {
        dcbm.removeAllElements();
        for (Coche coche : lista) {
            dcbm.addElement(coche);
        }
    }

    private void exportarXML(File fichero) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try{
            builder = factory.newDocumentBuilder();
            DOMImplementation dom = builder.getDOMImplementation();
            //documento que representa xml
            Document documento =
                    dom.createDocument(null,"xml",null);
            //crear nodo raiz coches y añadir al documento
            Element raiz = documento.createElement("coches");
            documento.getDocumentElement().appendChild(raiz);

            Element nodoCoche;
            Element nodoDatos;
            Text dato;

            for (Coche coche : lista){
                //crear nodo coche y añadir a raiz (coches)
                nodoCoche = documento.createElement("coche");
                raiz.appendChild(nodoCoche);
                //a cada coche, añadir marca y modelo
                nodoDatos=documento.createElement("marca");
                nodoCoche.appendChild(nodoDatos);
                //a cada nodo, añadir dato
                //datos marca
                dato = documento.createTextNode(coche.getMarca());
                nodoDatos.appendChild(dato);
                //a cada coche, añadir los nodos marca y modelo
                //nodo modelo
                nodoDatos = documento.createElement("modelo");
                nodoCoche.appendChild(nodoDatos);
                //a cada coche, añadir dato
                //datos modelo
                dato=documento.createTextNode(coche.getModelo());
                nodoDatos.appendChild(dato);
            }
            Source src = new DOMSource(documento);
            Result result = new StreamResult(fichero);

            Transformer transformer =
                    TransformerFactory.newInstance().newTransformer();
            transformer.transform(src,result);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    private void importarXML(File fichero) {
        //cuadro de dialogo Open
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.parse(fichero);

            //recorrer cada nodo del coche para obtener sus campos
            NodeList coches = documento.getElementsByTagName("coche");
            for (int i = 0; i < coches.getLength(); i++) {
                Node coche = coches.item(i);
                Element elemento = (Element) coche;

                //obtener los campos marca y modelo
                String marca = elemento.getElementsByTagName("marca").
                        item(0).getChildNodes().item(0).getNodeValue();
                String modelo = elemento.getElementsByTagName("modelo").
                        item(0).getChildNodes().item(0).getNodeValue();
                altaCoche(marca,modelo);
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Vista vista = new Vista();
    }
}
