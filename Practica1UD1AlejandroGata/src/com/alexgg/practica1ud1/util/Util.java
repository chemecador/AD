package com.alexgg.practica1ud1.util;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

/**
 * Clase Util. Contiene métodos para personalizar la aplicación.
 * */
public class Util {

    //muestra el mensaje de error
    public static void mensajeError(String mensaje) {
        JOptionPane.showMessageDialog(null,
                mensaje,"Error",JOptionPane.ERROR_MESSAGE);
    }

    //devuelve la respuesta que se selecciona
    public static int mensajeConfirmacion(String mensaje, String titulo) {
        return JOptionPane.showConfirmDialog(null,mensaje,
                titulo,JOptionPane.YES_NO_OPTION);
    }

    //selecciona el fichero que se quiere utilizar
    public static JFileChooser crearSelectorFichero(File rutaDefecto,
                                                    String tipoArchivos,
                                                    String extension) {
        JFileChooser selectorFichero=new JFileChooser();
        if (rutaDefecto!=null) {
            selectorFichero.setCurrentDirectory(rutaDefecto);
        }
        if (extension!=null) {
            FileNameExtensionFilter filtro = new
                    FileNameExtensionFilter(tipoArchivos,extension);
            selectorFichero.setFileFilter(filtro);
        }
        return selectorFichero;
    }
}
