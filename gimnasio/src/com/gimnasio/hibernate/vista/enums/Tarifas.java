package com.gimnasio.hibernate.vista.enums;

public enum Tarifas {
    /**
     * Esta clase enum enumera las constantes con las que se rellena
     * el JComoboBox actCombo de la vista.
     * Representan los instalaciones que tiene el gimnasio.
     */
    MES("Mensual"),
    TRIMESTRE("Trimestral"),
    ANUAL("Anual"),
    INDEFINIDO("Socio de honor");

    private String valor;

    Tarifas(String valor) {

        this.valor = valor;
    }

    public String getValor() {

        return valor;
    }

}

