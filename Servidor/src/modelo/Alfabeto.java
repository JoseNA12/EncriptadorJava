package modelo;

import controlador.IValidable;

import java.io.Serializable;

public class Alfabeto implements IValidable, Serializable {

    private String identificador;
    private String simbolos;

    public Alfabeto(String identificador, String simbolos) {
        this.identificador = identificador;
        this.simbolos = simbolos;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
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