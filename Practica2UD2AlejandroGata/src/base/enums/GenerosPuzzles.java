package base.enums;

public enum GenerosPuzzles {
    PAISAJE("Paisaje"),
    VIDEOJUEGO("Videojuego"),
    PELICULA("Pelicula"),
    MONUMENTO("Monumento"),
    FAMOSO("Famoso");

    private String valor;

    GenerosPuzzles(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
