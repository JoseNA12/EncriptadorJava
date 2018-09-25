package modelo;

public enum TipoAlgoritmo {

    TRASLETRALETRA("Trasposicion letra a letra"),
    CODTELEFONICO("Código telefónico"),
    SUSTVIGENERE("Sustitucion Vigenere");

    String nombre;
    TipoAlgoritmo(String pNombre) { nombre = pNombre; }

    public String getNombre() { return nombre; }
}
