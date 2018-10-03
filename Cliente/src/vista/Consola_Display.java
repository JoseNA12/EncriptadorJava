package vista;

public enum Consola_Display {
    MENU_PRINCIPAL(
            "Menú principal:\n" +
            "1 - Herramienta de cifrado\n" +
            "2 - Opciones de generación\n" +
            "3 - Salir"
    ),
    MENU_GENERACION(
            "Menú de configuración de frases generadas:\n" +
            "1 - Configurar\n" +
            "2 - Volver"
    ),
    SELECCION_GENERACION_HEADER(
            "Seleccione el tipo de generación de frase:"
    ),
    SELECCION_GENERACION_OPCIONES(
            "0 - No aplicar generación."
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
    SELECCION_ESCRITOR(
            "Seleccione el formato de escritura en bitacora:"
    ),
    SELECCION_OPCION(
            "Seleccione una opción:"
    ),
    ENTRADA_TEXTO(
            "Ingrese el texto a ser procesado:"
    ),
    ENTRADA_LONGITUD_GENERACION(
            "Ingrese la longitud en caracteres de la frase:"
    ),
    ERROR_ALFABETO(
            "\nERROR: el mensaje de entrada contiene caracteres que no pertenecen al alfabeto seleccionado!\n"
    );

    String value;
    Consola_Display(String p_valor){
        this.value = p_valor;
    }

    public String getValue() {
        return value;
    }
}
