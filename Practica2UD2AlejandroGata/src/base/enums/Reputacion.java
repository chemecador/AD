package base.enums;

public enum Reputacion {
    BUENA("Buena"),
    REGULAR("Regular"),
    MALA("Mala");

    private String valor;

    Reputacion(String valor) {

        this.valor = valor;
    }

    public String getValor() {

        return valor;
    }
}
