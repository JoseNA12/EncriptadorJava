package controlador;

import modelo.LineaResultado;
import modelo.Resultado;
import modelo.TipoAlgoritmo;

public class SustVigenere extends Algoritmo{

    public static LineaResultado Codificar(String pFrase, String pClave, String pAlfabeto) {
        String nuevaClave = ObtenerNuevaClave(pClave, pFrase.length());
        String fraseCifrada = "";

        for (int i = 0; i < pFrase.length(); i++) {

            if (String.valueOf(pFrase.charAt(i)).equals(" ")) {
                fraseCifrada += " ";
            }
            else {
                for (int y = 0; y < pAlfabeto.length(); y++) {
                    if (pFrase.charAt(i) == pAlfabeto.charAt(y)) {
                        int proximoCaracter = Integer.parseInt(String.valueOf(nuevaClave.charAt(i))) + y;

                        if (proximoCaracter >= pAlfabeto.length()){
                            proximoCaracter -= pAlfabeto.length();
                        }

                        fraseCifrada += pAlfabeto.charAt(proximoCaracter);
                        break;
                    }
                }
            }
        }
        return new LineaResultado(TipoAlgoritmo.SUSTVIGENERE, fraseCifrada, true);
    }

    public static LineaResultado Descodificar(String pFrase, String pClave, String pAlfabeto) {
        String nuevaClave = ObtenerNuevaClave(pClave, pFrase.length());
        String fraseDescifrada = "";

        for (int i = 0; i < pFrase.length(); i++) {

            if (String.valueOf(pFrase.charAt(i)).equals(" ")) {
                fraseDescifrada += " ";
            }
            else {
                for (int y = 0; y < pAlfabeto.length(); y++) {
                    if (pFrase.charAt(i) == pAlfabeto.charAt(y)) {
                        int proximoCaracter = y - Integer.parseInt(String.valueOf(nuevaClave.charAt(i)));

                        if (proximoCaracter < 0){
                            proximoCaracter += pAlfabeto.length();
                        }

                        fraseDescifrada += pAlfabeto.charAt(proximoCaracter);
                        break;
                    }
                }
            }
        }
        return new LineaResultado(TipoAlgoritmo.SUSTVIGENERE, fraseDescifrada, false);
    }

    /**
     * Crear una nueva clave que sea del mismo tamaño del texto. Son secuencias repetidas.
     * Ejempl: 123 -> 123123...
     * @param pClave
     * @param pLongitudFrase
     * @return
     */
    private static String ObtenerNuevaClave(String pClave, int pLongitudFrase) {
        String nuevaClave = "";

        if (pLongitudFrase > pClave.length()) {
            int contador = 0;

            for (int i = 0; i < pLongitudFrase; i++) {
                nuevaClave += pClave.charAt(contador);
                contador++;

                if (contador == pClave.length()) { contador = 0; }
            }
        }
        else {
            nuevaClave = pClave;
        }
        return nuevaClave;
    }
}
