package util;

import javax.swing.JOptionPane;

/**
 * Esta es una clase en la que tengo métodos estáticos para crear una ventana con un mensaje.
 * Cada método se refiere a un tipo distinto de mensaje.
 */
public class Util {
    /**
     * Este método me muestra un mensaje de error con el texto recibido
     * @param message Texto del mensaje de error
     */
    public static void showErrorAlert(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    /**
     * Este método me muestra un mensaje de aviso con el texto recibido
     * @param message Texto del mensaje de aviso
     */
    public static void showWarningAlert(String message) {
        JOptionPane.showMessageDialog(null, message, "Aviso", JOptionPane.WARNING_MESSAGE);
    }
    /**
     * Este método me muestra un mensaje de información con el texto recibido
     * @param message Texto del mensaje de información
     */
    public static void showInfoAlert(String message) {
        JOptionPane.showMessageDialog(null, message, "Información", JOptionPane.INFORMATION_MESSAGE);
    }
}
