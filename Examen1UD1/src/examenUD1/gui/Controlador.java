package examenUD1.gui;

import examenUD1.base.Ordenador;
import examenUD1.base.Portatil;
import examenUD1.base.SobreMesa;
import examenUD1.gui.Ventana.Ventana;
import examenUD1.util.Util;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

public class Controlador implements ActionListener, ListSelectionListener, WindowListener{
    private Ventana vista;
    private Modelo modelo;
    private File ultimaRutaExportada;

    public Controlador(Ventana vista, Modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;

        try {
            cargarDatosConfiguracion();
        } catch (IOException e) {
            e.printStackTrace();
        }

        addActionListener(this);
        addListSelectionListener(this);
        addWindowListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        switch (actionCommand) {
            case "Nuevo":
                //completar
                if (hayCamposVacios()) {
                    Util.mensajeError("Alguno de estos campos está vacío:\n" +
                            "Nombre \n Cantidad \n Precio \n Fecha oferta\n" +
                            vista.txtRatonTouchPad.getText());
                    break;
                }
                if (modelo.existeNombre(vista.txtNombre.getText())) {
                    Util.mensajeError("Ya existe un ordenador con este nombre\n" +
                            vista.txtNombre.getText());
                    break;
                }
                if (vista.portatilRadioButton.isSelected()){
                    modelo.altaPortatil(vista.txtNombre.getText(),
                            vista.txtCantidad.getText(),
                            vista.txtPrecio.getText(),
                            vista.txtRatonTouchPad.getText(),
                            vista.datepicker.getDate());
                }
                else if (vista.sobremesaRadioButton.isSelected()){
                    modelo.altaSobreMesa(vista.txtNombre.getText(),
                            vista.txtCantidad.getText(),
                            vista.txtPrecio.getText(),
                            vista.txtRatonTouchPad.getText(),
                            vista.datepicker.getDate());
                }
                limpiarCampos();
                refrescar();
                break;
            case "Importar":
                JFileChooser selectorFichero = Util.crearSelectorFicheros(ultimaRutaExportada, "Archivo XML", "xml");
                int opt = selectorFichero.showOpenDialog(null);
                if (opt == JFileChooser.APPROVE_OPTION) {
                    try {
                        modelo.importarXML(selectorFichero.getSelectedFile());
                    } catch (ParserConfigurationException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (org.xml.sax.SAXException ex) {
                        ex.printStackTrace();
                    }
                    refrescar();
                }
                break;
            case "Exportar":
                JFileChooser selectorFichero2 = Util.crearSelectorFicheros(ultimaRutaExportada, "Archivos XML", "xml");
                int opt2 = selectorFichero2.showSaveDialog(null);
                if (opt2 == JFileChooser.APPROVE_OPTION) {
                    try {
                        modelo.exportarXML(selectorFichero2.getSelectedFile());
                        actualizarDatosConfiguracion(selectorFichero2.getSelectedFile());
                    } catch (ParserConfigurationException ex) {
                        ex.printStackTrace();
                    } catch (TransformerException ex) {
                        ex.printStackTrace();
                    }
                }
                break;
            case "Portatil":
                vista.lblratonTounPad.setText("Touchpad");
                break;
            case "Sobremesa":
                vista.lblratonTounPad.setText("Raton");
                break;
            case "Eliminar":
                if (!modelo.eliminarRecambio(vista.txtNombre.getText())){
                    Util.mensajeError("No se ha podido borrar el recambio");
                }else {
                    limpiarCampos();
                    refrescar();
                    Util.mensajeExitoso("Se elimino el recambio correctamente");
                }
                limpiarCampos();
                refrescar();
                break;
        }
    }

    private boolean hayCamposVacios() {
        if (vista.txtRatonTouchPad.getText().isEmpty() ||
                vista.txtNombre.getText().isEmpty() ||
                vista.txtCantidad.getText().isEmpty() ||
                vista.txtPrecio.getText().isEmpty() ||
                vista.datepicker.getText().isEmpty()) {
            return true;
        }
        return false;
    }

    private void limpiarCampos() {
        //completar
        vista.txtNombre.setText(null);
        vista.txtCantidad.setText(null);
        vista.txtPrecio.setText(null);
        vista.datepicker.setText(null);
        vista.txtRatonTouchPad.setText(null);
    }

    private void refrescar() {
        vista.dlmOrdenador.clear();
        for (Ordenador ordenador : modelo.obtenerRecambios()) {
            vista.dlmOrdenador.addElement(ordenador);
        }
    }

    private void addActionListener(ActionListener listener) {
        vista.portatilRadioButton.addActionListener(listener);
        vista.sobremesaRadioButton.addActionListener(listener);
        vista.exportarbtn.addActionListener(listener);
        vista.importarbtn.addActionListener(listener);
        vista.nuevoBtn.addActionListener(listener);
        vista.btnEliminar.addActionListener(listener);
    }

    private void addWindowListener(WindowListener listener) {
        vista.frame.addWindowListener(listener);
    }

    private void addListSelectionListener(ListSelectionListener listener) {
        vista.list1.addListSelectionListener(listener);
    }

    private void cargarDatosConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        configuracion.load(new FileReader("Recambios.conf"));
        if (configuracion.getProperty("ultimaRutaExportada") != null){
            ultimaRutaExportada = new File(configuracion.getProperty("ultimaRutaExportada"));
        }
    }

    private void actualizarDatosConfiguracion(File ultimaRutaExportada) {
        this.ultimaRutaExportada = ultimaRutaExportada;
    }

    private void guardarConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        if (ultimaRutaExportada == null){
            ultimaRutaExportada = new File(System.getProperty("user.dir"));
        }
        configuracion.setProperty("ultimaRutaExportada", ultimaRutaExportada.getAbsolutePath());
        configuracion.store(new PrintWriter("Recambios.conf"), "Datos configuracion recambios");
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
       //completar

        if (e.getValueIsAdjusting()) {
            Ordenador ordenadorSeleccionado = (Ordenador) vista.list1.getSelectedValue();
            vista.txtNombre.setText(String.valueOf(ordenadorSeleccionado.getNombre()));
            vista.txtCantidad.setText(ordenadorSeleccionado.getCantidad());
            vista.txtPrecio.setText(String.valueOf(ordenadorSeleccionado.getPrecio()));
            vista.datepicker.setDate(ordenadorSeleccionado.getFechaOferta());

            if (ordenadorSeleccionado instanceof Portatil) {
                vista.portatilRadioButton.doClick();
                vista.txtRatonTouchPad.setText(String.valueOf(((Portatil) ordenadorSeleccionado).getTouchpad()));
            } else if (ordenadorSeleccionado instanceof SobreMesa) {
                vista.portatilRadioButton.doClick();
                vista.txtRatonTouchPad.setText(String.valueOf(((SobreMesa) ordenadorSeleccionado).getRaton()));
            }
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        int resp = Util.mensajeConfirmacion("¿Desea salir de la ventana?", "Salir");
        if (resp == JOptionPane.YES_OPTION) {
            vista.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            try {
                guardarConfiguracion();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            vista.frame.dispose();
        }else if (resp == JOptionPane.NO_OPTION){
            vista.frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
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
}
