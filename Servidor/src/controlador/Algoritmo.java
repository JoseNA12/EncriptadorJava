package controlador;

import modelo.LineaResultado;
import modelo.Resultado;

public class Algoritmo { //TODO: cambiar por una interfaz para implementar el strategy

    private LineaResultado miResultado;
    private String simbolosAlfabetos = ""; // abcdefghijklmnñopqrstuvwxyz


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
