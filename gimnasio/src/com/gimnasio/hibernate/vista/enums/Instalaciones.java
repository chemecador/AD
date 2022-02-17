package com.gimnasio.hibernate.vista.enums;


    /**
     * Esta clase enum enumera las constantes con las que se rellena
     * el JComoboBox actCombo de la vista.
     * Representan los instalaciones que tiene el gimnasio.
     */
    public enum Instalaciones {
        GIMNASIO("Gimnasio"),
        SPA("SPA"),
        PISCINA("Piscina"),
        EXT("Exterior");

        private String valor;

        Instalaciones(String valor) {

            this.valor = valor;
        }

        public String getValor() {

            return valor;
        }
}
