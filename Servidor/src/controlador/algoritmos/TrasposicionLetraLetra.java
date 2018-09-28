package controlador.algoritmos;

import modelo.LineaResultado;

public class TrasposicionLetraLetra extends Algoritmo {


    public static LineaResultado Codificar(String pEntrada) {
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
        // fraseCifrada.trim(); // <- CONTIENE EL RESULTADO!!
        return null;
    }


}
