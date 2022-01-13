package base.enums;

/**
 * Esta clase enum enumera las constantes con las que se rellena
 * el JComoboBox comboGenero de la vista.
 * Representan los géneros literarios que existen.
 */
public enum GenerosLibros {
    NOVELAFANTASIA("Fantasía"),
    NOVELACFICCION("Ciencia-ficción"),
    NOVELAAUTOFICTION("Autoficción"),
    NOVELATHRILLER("Suspense"),
    NOVELATERROR("Terror"),
    NOVELAHISTORICA("Histórico"),
    NOVELAAVENTURAS("Aventuras"),
    POLICIACA("Policiaco"),
    NOVELAROMANCE("Romance"),
    NOVELAJUVENIL("Juvenil"),
    NOVELAINFANTIL("Infantil"),
    COMIC("Cómic"),
    MANGA("Manga"),
    RELATO("Relato"),
    MICRORELATO("Microrrelato"),
    CUENTOHADAS("Cuento de hadas"),
    RELATOMODERNO("Relato moderno"),
    AFORISMOS("Aforismos"),
    POESIA("Poesía"),
    ENSAYO("Ensayo");

    private String valor;

    GenerosLibros(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
