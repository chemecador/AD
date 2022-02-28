package com.practicaUD4.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Clase Util
 * @author
 */
public class Util {
    /**
     * Método formatearFecha(), formatea la fecha
     * @param fechaMatriculacion
     * @return formateador.format(fechaMatriculacion);
     */
    public static String formatearFecha(LocalDate fechaMatriculacion) {
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return formateador.format(fechaMatriculacion);
    }

    /**
     * Método parsearFecha() de tipo LocalDate
     * @param fecha de tipo String
     * @return LocalDate.parse(fecha, formateador)
     */
    public static LocalDate parsearFecha(String fecha){
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(fecha, formateador);
    }
}
