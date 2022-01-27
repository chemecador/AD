package util;

import javax.swing.*;

public class Util {

    public static void showErrorAlert(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void showWarningAlert(String message) {
        JOptionPane.showMessageDialog(null, message, "Aviso", JOptionPane.WARNING_MESSAGE);
    }

    public static void showInfoAlert(String message) {
        JOptionPane.showMessageDialog(null, message, "Información", JOptionPane.INFORMATION_MESSAGE);
    }
}
