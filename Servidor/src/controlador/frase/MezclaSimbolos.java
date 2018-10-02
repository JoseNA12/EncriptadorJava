package controlador.frase;

public class MezclaSimbolos {

    private String simbolos;
    private String alfabeto;
    private int longitud;

    public MezclaSimbolos(String alfabeto, int longitud) {
        this.alfabeto = alfabeto;
        this.longitud = longitud;
    }

    public String getSimbolos() {
        return this.simbolos;
    }

    public void setSimbolos(String simbolos) {
        this.simbolos = simbolos;
    }

    public String getAlfabeto() {
        return this.alfabeto;
    }

    public void setAlfabeto(String alfabeto) {
        this.alfabeto = alfabeto;
    }

    public int getLongitud() {
        return this.longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }
}
