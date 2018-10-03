package modelo;

import controlador.IValidable;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    public static Boolean esSubconjunto(String simbolosEntrada, String simbolosAlfabeto){
        List<Character> charsAlfabeto =
                simbolosAlfabeto.toUpperCase().chars().mapToObj(e->(char)e).collect(Collectors.toList());
        final Set<Character> conjunto = new HashSet<>(charsAlfabeto);

        List<Character> charsEntrada =
                simbolosEntrada.toUpperCase().chars().mapToObj(e->(char)e).collect(Collectors.toList());

        for(Character c : charsEntrada){
            if(!conjunto.contains(c) && !c.toString().equals(" ")){
                return false;
            }
        }
        return true;
    }

    @Override
    public Boolean Validar(String pEntrada) {
        List<Character> chars = simbolos.chars().mapToObj(e->(char)e).collect(Collectors.toList());
        final Set<Character> conjunto = new HashSet<>();

        for(Character simbolo : chars){
            if(!conjunto.add(simbolo))
                return false;
        }

        return true;
    }
}