package com.alexgg.vehiculosmvc.gui;

import com.alexgg.vehiculosmvc.base.Coche;
import com.alexgg.vehiculosmvc.base.Moto;
import com.alexgg.vehiculosmvc.base.Vehiculo;
import com.alexgg.vehiculosmvc.util.Util;
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

public class VehiculosControlador  implements ActionListener, ListSelectionListener, WindowListener{

    private Ventana vista;
    private VehiculosModelo modelo;
    private File ultimaRutaExportada;

    public VehiculosControlador (VehiculosModelo modelo, Ventana vista){
        this.vista = vista;
        this.modelo = modelo;
        try {
            cargarDatosConfiguracion();
        } catch (IOException e) {
            System.out.println("No existe el fichero de configuracion");
        }
        addActionListener(this);
        addListSelectionListener(this);
        addWindowListener(this);
    }

    private void cargarDatosConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        configuracion.load(new FileReader("vehiculos.conf"));
        ultimaRutaExportada = new File(configuracion.getProperty("ultimaRutaExportada"));
    }
    private void actualizarDatosConfiguracion(File ultimaRutaExportada) throws IOException {
        this.ultimaRutaExportada = ultimaRutaExportada;
    }
    private void guardarDatosConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        configuracion.setProperty("ultimaRutaExportada", ultimaRutaExportada.getAbsolutePath());

        configuracion.setProperty(String.valueOf(new PrintWriter("vehiculos.conf")),
                "Datos configuracion vehiculos");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        switch (actionCommand) {
            case "Nuevo":
                if (hayCamposVacios()) {
                    Util.mensajeError("Hay algún campo vacío" + vista.kmsPlazasTxt.getText());
                    break;
                }
                if (modelo.existeMatricula(vista.matriculaTxt.getText())){
                    Util.mensajeError("Ya existe un vehículo con esta matrícula");
                    break;
                }
                if (vista.cocheRadioButton.isSelected()){
                    modelo.altaCoche(vista.matriculaTxt.getText(), vista.marcaTxt.getText(),
                            vista.modeloTxt.getText(), vista.fechaMatriculacionDP.getDate(),
                            Integer.parseInt(vista.kmsPlazasTxt.getText()));
                } else {
                    modelo.altaMoto(vista.matriculaTxt.getText(), vista.marcaTxt.getText(),
                            vista.modeloTxt.getText(), vista.fechaMatriculacionDP.getDate(),
                            Double.parseDouble(vista.kmsPlazasTxt.getText()));
                }
                limpiarCampos();
                refrescar();
                break;
            case "Importar": {
                JFileChooser selectorFichero = Util.crearSelectorFichero(ultimaRutaExportada,
                        "Archivos XML", "xml");
                int opt = selectorFichero.showOpenDialog(null);
                if (opt == JFileChooser.APPROVE_OPTION) {
                    try {
                        modelo.importarXML(selectorFichero.getSelectedFile());
                    } catch (ParserConfigurationException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (SAXException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            case "Exportar":
                JFileChooser selectorFichero = Util.crearSelectorFichero(ultimaRutaExportada,
                        "Archivos XML", "xml");
                int opt2 = selectorFichero.showSaveDialog(null);
                if (opt2 == JFileChooser.APPROVE_OPTION) {
                    try {
                        modelo.exportarXML(selectorFichero.getSelectedFile());
                        actualizarDatosConfiguracion(selectorFichero.getSelectedFile());
                    } catch (TransformerException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (ParserConfigurationException ex) {
                        ex.printStackTrace();
                    }
                }
                break;
            case "Moto":
                vista.kmsPlazasTxt.setText("Kms");
                break;
            case "Coche":
                vista.kmsPlazasTxt.setText("N Plazas");
                break;
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

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
        }
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

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()){
            Vehiculo vehiculo = (Vehiculo) vista.list1.getSelectedValue();
            vista.matriculaTxt.setText(vehiculo.getMatricula());
            vista.marcaTxt.setText(vehiculo.getMarca());
            vista.modeloTxt.setText(vehiculo.getModelo());
            vista.fechaMatriculacionDP.setDate(vehiculo.getFechaMatriculacion());
            if(vehiculo instanceof Coche){
                vista.cocheRadioButton.doClick();
                vista.kmsPlazasTxt.setText(String.valueOf(((Coche) vehiculo).getNumPlazas()));
            }
            else if (vehiculo instanceof Moto){
                vista.motoRadioButton.doClick();
                vista.kmsPlazasTxt.setText(String.valueOf(((Moto) vehiculo).getKms()));
            }
        }

    }
    private void addActionListener (ActionListener listener){
        vista.cocheRadioButton.addActionListener(listener);
        vista.motoRadioButton.addActionListener(listener);
        vista.exportarBtn.addActionListener(listener);
        vista.importarBtn.addActionListener(listener);
        vista.nuevoBtn.addActionListener(listener);
    }
    private void addWindowListener(WindowListener listener){
        vista.frame.addWindowListener(listener);
    }
    private void addListSelectionListener(ListSelectionListener listener){
        vista.list1.addListSelectionListener(listener);
    }

    private void limpiarCampos(){
        vista.kmsPlazasTxt.setText(null);
        vista.marcaTxt.setText(null);
        vista.matriculaTxt.setText(null);
        vista.modeloTxt.setText(null);
        vista.fechaMatriculacionDP.setText(null);
        vista.matriculaTxt.requestFocus();
    }
    private boolean hayCamposVacios(){
        if (vista.kmsPlazasTxt.getText().isEmpty() ||
                vista.marcaTxt.getText().isEmpty() ||
                vista.modeloTxt.getText().isEmpty() ||
                vista.fechaMatriculacionDP.getText().isEmpty() ||
                vista.matriculaTxt.getText().isEmpty()){
            return true;
        }
        return false;
    }
    public void refrescar(){
        vista.dlmVehiculo.clear();
        for (Vehiculo unVehiculo: modelo.obtenerVehiculos()){
            vista.dlmVehiculo.addElement(unVehiculo);
        }
    }
}
