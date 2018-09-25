package vista;

public enum Consola_Display {
    MENU_PRINCIPAL(
            "Menú principal:\n" +
            "1 - Herramienta de cifrado\n" +
            "2 - Agregar alfabeto\n" +
            "3 - Salir"
    ),
    SELECCION_ALGORITMO_HEADER(
            "Seleccione los algoritmos de cifrado deseados:"
    ),
    SELECCION_ALGORITMO_OPCIONES(
            "0 - Confirmar"
    ),
    SELECCION_MODO(
            "Seleccione el modo:\n" +
            "1 - Codificar\n" +
            "2 - Descodificar:"
    ),
    SELECCION_ALFABETO(
            "Seleccione el alfabeto:"
    ),
    SELECCION_OPCION(
            "Seleccione una opción:"
    ),
    ENTRADA_TEXTO(
            "Ingrese el texto a ser procesado:"
    ),
    ENTRADA_SIMBOLOS_ALFABETO(
            "Agregar un alfabeto\n" +
            "Ingrese los simbolos (abcedfg...) que contiene el alfabeto:"
    ),
    ENTRADA_ID_ALFABETO(
            "Ingrese el nombre (identificador) del alfabeto:"
    );


    String valor;
    Consola_Display(String p_valor){
        this.valor = p_valor;
    }

    public String getValor() {
        return valor;
    }
}
