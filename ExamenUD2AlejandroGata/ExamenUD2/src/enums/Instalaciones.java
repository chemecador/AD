package enums;

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
