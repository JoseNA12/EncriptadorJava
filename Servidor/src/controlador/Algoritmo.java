package controlador;

import modelo.LineaResultado;
import modelo.Resultado;

public class Algoritmo {

    private LineaResultado miResultado;
    private String simbolosAlfabetos = "abcdefghijklmnñopqrstuvwxyz";

    static Boolean Validar(String pEntrada) {return false;}

    public LineaResultado Codificar(String pEntrada) {return null;}
    public LineaResultado Descodificar(String pEntrada) {return null;}

    public LineaResultado getMiResultado()
    {
        return miResultado;
    }

    public void setMiResultado(LineaResultado miResultado)
    {
        this.miResultado = miResultado;
    }

    public String getSimbolosAlfabetos() {
        return simbolosAlfabetos;
    }

    public void setSimbolosAlfabetos(String simbolosAlfabetos) {
        this.simbolosAlfabetos = simbolosAlfabetos;
    }
}
