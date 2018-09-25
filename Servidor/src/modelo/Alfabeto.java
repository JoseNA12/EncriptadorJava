package modelo;

import controlador.IValidable;

public class Alfabeto implements IValidable {

    private int identificador;
    private String simbolos;

    public Alfabeto(int identificador, String simbolos) {
        this.identificador = identificador;
        this.simbolos = simbolos;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getSimbolos() {
        return simbolos;
    }

    public void setSimbolos(String simbolos) {
        this.simbolos = simbolos;
    }

    @Override
    public Boolean Validar(String pEntrada) {
        return null;
    }
}