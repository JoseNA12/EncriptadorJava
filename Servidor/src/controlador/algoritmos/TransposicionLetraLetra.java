package controlador.algoritmos;

import controlador.Algoritmo;
import modelo.LineaResultado;

public class TransposicionLetraLetra extends Algoritmo {

    public LineaResultado Codificar(String pEntrada) {
        String temp = "";
        String fraseCifrada = "";

        for (int i = 0; i < pEntrada.length(); i++){
            temp += pEntrada.charAt(i);

            if (String.valueOf(pEntrada.charAt(i)).equals(" ") | i == (pEntrada.length() - 1)){

                if (i == (pEntrada.length() - 1)) { fraseCifrada += " "; }

                for (int y = (temp.length() - 1); y != -1; y--){
                    fraseCifrada += temp.charAt(y);
                }
                temp = "";
            }
        }
        return new LineaResultado(this.getClass().getSimpleName(), fraseCifrada.trim(), true);
    }

    public LineaResultado Descodificar(String pEntrada){
        LineaResultado lineaResultado = Codificar(pEntrada);
        lineaResultado.setModoCodificacion(false);

        return lineaResultado;
    }


}
