package enums;

public enum Tarifas {
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
