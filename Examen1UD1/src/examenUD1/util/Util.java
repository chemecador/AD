package examenUD1.util;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class Util {

    public static void mensajeError(String mensaje) {
        JOptionPane.showMessageDialog(null,mensaje,"Error",JOptionPane.ERROR_MESSAGE);
    }
    public static int mensajeConfirmacion(String mensaje, String titulo) {
        return JOptionPane.showConfirmDialog(null,mensaje,titulo,JOptionPane.YES_NO_OPTION);
    }
    public static JFileChooser crearSelectorFicheros(File rutaDefecto, String tipoArchivos, String extension) {
        JFileChooser selectorFichero = new JFileChooser();
        if (rutaDefecto!=null) {
            selectorFichero.setCurrentDirectory(rutaDefecto);
        }
        if (extension!=null) {
            FileNameExtensionFilter filtro = new FileNameExtensionFilter(tipoArchivos,extension);
            selectorFichero.setFileFilter(filtro);
        }
        return selectorFichero;
    }

    public static void mensajeExitoso(String mensaje) {
        JOptionPane.showMessageDialog(null,mensaje,"Exito",JOptionPane.INFORMATION_MESSAGE);
    }
}
