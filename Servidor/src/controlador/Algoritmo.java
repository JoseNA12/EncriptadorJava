package controlador;

import modelo.LineaResultado;
import modelo.Resultado;

public class Algoritmo { //TODO: cambiar por una interfaz para implementar el strategy

    private LineaResultado miResultado;


    static Boolean Validar(String pEntrada) {return false;}

    static LineaResultado Codificar(String pEntrada) {return null;}
    static LineaResultado Decodificar(String pEntrada) {return null;}

    public LineaResultado getMiResultado()
    {
        return miResultado;
    }

    public void setMiResultado(LineaResultado miResultado)
    {
        this.miResultado = miResultado;
    }
}
