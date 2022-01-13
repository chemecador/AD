package base.enums;

/**
 * Esta clase enum enumera las constantes con las que se rellena
 * el JComoboBox comboTipoEditorial de la vista.
 * Representan los de eeditorial que existen que existen.
 */
public enum TiposEditoriales {
    AUTOREDITOR("Autor-Editor"),
    EDITORIAL("Editorial"),
    ORGANISMOOFICIAL("Organismo Oficial"),
    UNIVERSIDAD("Universidad");

    private String valor;

    TiposEditoriales(String valor) {

        this.valor = valor;
    }

    public String getValor() {

        return valor;
    }
}
