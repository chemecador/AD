package com.alexgg.practica1ud1.gui;

import com.alexgg.practica1ud1.base.JuegoDeMesa;
import com.alexgg.practica1ud1.base.Maqueta;
import com.alexgg.practica1ud1.base.Producto;
import com.alexgg.practica1ud1.base.Puzzle;
import com.alexgg.practica1ud1.util.Util;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.util.Properties;

/**
 * Clase ProductosControlador. Contiene el controlador del programa, que se encarga de unir la Vista y el Modelo.
 **/
public class ProductosControlador implements ActionListener, ListSelectionListener, WindowListener {

    //atributos
    private Vista vista;
    private ProductosModelo modelo;
    private File ultimaRutaExportada;

    //constructor
    public ProductosControlador(Vista vista, ProductosModelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
        try {
            cargarDatosConfiguracion();
        } catch (IOException e) {
            System.out.println("No existe el fichero de configuración");
        }
        addActionListener(this);
        addListSelectionListener(this);
        addWindowListener(this);
    }

    //listener de los radioButton y botones
    private void addActionListener(ActionListener listener) {
        vista.puzzleRadioBtn.addActionListener(listener);
        vista.maquetaRadioBtn.addActionListener(listener);
        vista.juegoDeMesaRadioBtn.addActionListener(listener);
        vista.exportarBtn.addActionListener(listener);
        vista.importarBtn.addActionListener(listener);
        vista.nuevoBtn.addActionListener(listener);
    }

    //carga, si puede, los datos del fichero productos.conf
    private void cargarDatosConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        FileReader archivo = null;
        File file = new File("productos.conf");
        if (!file.exists()) {
            file.createNewFile();
        } else {
            if (file.length() > 0) {
                configuracion.load(new FileReader("productos.conf"));
                ultimaRutaExportada = new File(configuracion.getProperty("ultimaRutaExportada"));
            } else {
                System.err.println("El fichero de configuración está vacío");
            }
        }
    }


    //actualiza la última ruta exportada
    private void actualizarDatosConfiguracion(File ultimaRutaExportada) {
        this.ultimaRutaExportada = ultimaRutaExportada;
    }

    //guarda los datos de la última ruta exportada
    private void guardarDatosConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        configuracion.setProperty("ultimaRutaExportada", ultimaRutaExportada.getAbsolutePath());

        configuracion.store(new PrintWriter("productos.conf"),
                "Datos configuracion productos");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        switch (actionCommand) {
            case "Nuevo":
                String tipo = "";
                if (hayCamposVacios()) {
                    Util.mensajeError("Alguno de estos campos está vacío:\n" +
                            "Precio \n Marca \n Id \n Fecha producción\n" +
                            vista.piezasFigurasJugadoresTxt.getText());
                    break;
                }
                if (!isNumeric(vista.precioTxt.getText())) {
                    vista.errorLbl.setText("El precio debe ser un número.");
                    break;
                }
                if (!isNumeric(vista.idProductoTxt.getText())) {
                    vista.errorLbl.setText("El id del producto debe ser un número.");
                    break;
                }
                if (!isNumeric(vista.piezasFigurasJugadoresTxt.getText())) {
                    vista.errorLbl.setText("El " + vista.piezasFigurasJugadoresLbl.getText() + " debe ser un número.");
                    break;
                }


                if (modelo.existeId(Integer.parseInt(vista.idProductoTxt.getText()))) {
                    Util.mensajeError("Ya existe un producto con este ID\n" +
                            vista.piezasFigurasJugadoresTxt.getText());
                    break;
                }

                if (vista.puzzleRadioBtn.isSelected()) {
                    modelo.altaPuzzle(Double.parseDouble(vista.precioTxt.getText()),
                            vista.marcaTxt.getText(),
                            Integer.parseInt(vista.idProductoTxt.getText()),
                            vista.fechaDP.getDate(),
                            Integer.parseInt(vista.piezasFigurasJugadoresTxt.getText()));
                    tipo = "puzzle";
                } else if (vista.maquetaRadioBtn.isSelected()) {
                    modelo.altaMaqueta(Double.parseDouble(vista.precioTxt.getText()),
                            vista.marcaTxt.getText(),
                            Integer.parseInt(vista.idProductoTxt.getText()),
                            vista.fechaDP.getDate(),
                            Integer.parseInt(vista.piezasFigurasJugadoresTxt.getText()));
                    tipo = "maqueta";
                } else if (vista.juegoDeMesaRadioBtn.isSelected()) {
                    modelo.altaJuegoDeMesa(Double.parseDouble(vista.precioTxt.getText()),
                            vista.marcaTxt.getText(),
                            Integer.parseInt(vista.idProductoTxt.getText()),
                            vista.fechaDP.getDate(), Integer.parseInt(vista.piezasFigurasJugadoresTxt.getText()));
                    tipo = "juego de mesa";
                }
                vista.errorLbl.setText("Rellenado un/a " + tipo);
                limpiarCampos();
                refrescar();
                break;
            case "Importar":
                JFileChooser selectorFichero =
                        Util.crearSelectorFichero(ultimaRutaExportada,
                                "Archivos XML", "xml");
                int opt = selectorFichero.showOpenDialog(null);
                if (opt == JFileChooser.APPROVE_OPTION) {
                    try {
                        modelo.importarXML(selectorFichero.getSelectedFile());
                    } catch (ParserConfigurationException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (SAXException e1) {
                        e1.printStackTrace();
                    }
                    refrescar();
                }
                break;
            case "Exportar":
                JFileChooser selectorFichero2 =
                        Util.crearSelectorFichero(ultimaRutaExportada, "Archivos XML", "xml");
                int opt2 = selectorFichero2.showSaveDialog(null);
                if (opt2 == JFileChooser.APPROVE_OPTION) {
                    try {
                        modelo.exportarXML(selectorFichero2.getSelectedFile());
                        actualizarDatosConfiguracion(selectorFichero2.getSelectedFile());
                    } catch (ParserConfigurationException e1) {
                        e1.printStackTrace();
                    } catch (TransformerException e1) {
                        e1.printStackTrace();
                    }
                }
                break;
            case "Puzzle":
                vista.piezasFigurasJugadoresLbl.setText("N Piezas");
                break;
            case "Maqueta":
                vista.piezasFigurasJugadoresLbl.setText("N Figuras");
                break;
            case "Juego de mesa":
                vista.piezasFigurasJugadoresLbl.setText("N Jugadores");
        }
    }

    //vacía los campos de la ventana
    private void limpiarCampos() {
        vista.precioTxt.setText(null);
        vista.marcaTxt.setText(null);
        vista.idProductoTxt.setText(null);
        vista.fechaDP.setText(null);
        vista.piezasFigurasJugadoresTxt.setText(null);
    }

    //comprueba si hay campos vacíos
    private boolean hayCamposVacios() {
        if (vista.precioTxt.getText().isEmpty() ||
                vista.marcaTxt.getText().isEmpty() ||
                vista.idProductoTxt.getText().isEmpty() ||
                vista.fechaDP.getText().isEmpty() ||
                vista.piezasFigurasJugadoresTxt.getText().isEmpty()) {
            return true;
        }
        return false;
    }

    //carga los datos en la lista
    public void refrescar() {
        vista.dlmProducto.clear();
        for (Producto unProducto : modelo.getListaProductos()) {
            vista.dlmProducto.addElement(unProducto);
        }
    }

    //comprueba si el dato introducido por parámetro es un número
    public boolean isNumeric(String numero) {
        return (isInteger(numero) || isDouble(numero));
    }

    //comprueba si el dato introducido por parámetro es un entero
    public boolean isInteger(String numero) {
        try {
            Integer.parseInt(numero);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    //comprueba si el dato introducido por parámetro es un número real
    public boolean isDouble(String numero) {
        try {
            Double.parseDouble(numero);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            Producto productoSeleccionado = (Producto) vista.list.getSelectedValue();
            vista.precioTxt.setText(String.valueOf(productoSeleccionado.getPrecio()));
            vista.marcaTxt.setText(productoSeleccionado.getMarca());
            vista.idProductoTxt.setText(String.valueOf(productoSeleccionado.getId()));
            vista.fechaDP.setDate(productoSeleccionado.getFechaProduccion());

            if (productoSeleccionado instanceof Puzzle) {
                vista.puzzleRadioBtn.doClick();
                vista.piezasFigurasJugadoresTxt.setText(String.valueOf(((Puzzle) productoSeleccionado).getNumPiezas()));
            } else if (productoSeleccionado instanceof Maqueta) {
                vista.maquetaRadioBtn.doClick();
                vista.piezasFigurasJugadoresTxt.setText(String.valueOf(((Maqueta) productoSeleccionado).getNumFiguras()));
            } else if (productoSeleccionado instanceof JuegoDeMesa) {
                vista.juegoDeMesaRadioBtn.doClick();
                vista.piezasFigurasJugadoresTxt.setText(String.valueOf(((JuegoDeMesa) productoSeleccionado).getNumJugadores()));
            }
        }
    }


    @Override
    public void windowClosing(WindowEvent e) {
        int resp = Util.mensajeConfirmacion("¿Desea cerrar la ventana?", "Salir");
        if (resp == JOptionPane.OK_OPTION) {
            try {
                guardarDatosConfiguracion();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            System.exit(0);
        } else if (resp == JOptionPane.OK_CANCEL_OPTION) {
        }
    }

    //añade el listener de la ventana
    private void addWindowListener(WindowListener listener) {
        vista.frame.addWindowListener(listener);
    }

    //añade el listener de la lista
    private void addListSelectionListener(ListSelectionListener listener) {
        vista.list.addListSelectionListener(listener);
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
