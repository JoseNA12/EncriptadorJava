package controlador;

import modelo.LineaResultado;
import modelo.Resultado;

public class Algoritmo {

    private LineaResultado miResultado;

    static Boolean Validar(String pEntrada) {return false;}

    public LineaResultado Codificar(String pEntrada) {return null;}
    public LineaResultado Decodificar(String pEntrada) {return null;}

    public LineaResultado getMiResultado()
    {
        return miResultado;
    }

    public void setMiResultado(LineaResultado miResultado)
    {
        this.miResultado = miResultado;
    }
}
