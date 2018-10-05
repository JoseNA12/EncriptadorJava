package controlador.algoritmos;

import controlador.Algoritmo;
import modelo.LineaResultado;

public class SustitucionVigenere extends Algoritmo {

    private static final String clave = "23";

    public LineaResultado Codificar(String pFrase) {
        String alfabeto = super.getSimbolosAlfabetos();
        String nuevaClave = ObtenerNuevaClave(clave, pFrase.length());
        String fraseCifrada = "";

        for (int i = 0; i < pFrase.length(); i++) {

            if (String.valueOf(pFrase.charAt(i)).equals(" ")) {
                fraseCifrada += " ";
            }
            else {
                for (int y = 0; y < alfabeto.length(); y++) {
                    if (pFrase.charAt(i) == alfabeto.charAt(y)) {
                        int proximoCaracter = Integer.parseInt(String.valueOf(nuevaClave.charAt(i))) + y;

                        if (proximoCaracter >= alfabeto.length()){
                            proximoCaracter -= alfabeto.length();
                        }

                        fraseCifrada += alfabeto.charAt(proximoCaracter);
                        break;
                    }
                }
            }
        }
        return new LineaResultado(this.getClass().getSimpleName(), fraseCifrada, true);
    }

    public LineaResultado Descodificar(String pFrase) {
        String alfabeto = super.getSimbolosAlfabetos();
        String nuevaClave = ObtenerNuevaClave(clave, pFrase.length());
        String fraseDescifrada = "";

        for (int i = 0; i < pFrase.length(); i++) {

            if (String.valueOf(pFrase.charAt(i)).equals(" ")) {
                fraseDescifrada += " ";
            }
            else {
                for (int y = 0; y < alfabeto.length(); y++) {
                    if (pFrase.charAt(i) == alfabeto.charAt(y)) {
                        int proximoCaracter = y - Integer.parseInt(String.valueOf(nuevaClave.charAt(i)));

                        if (proximoCaracter < 0){
                            proximoCaracter += alfabeto.length();
                        }

                        fraseDescifrada += alfabeto.charAt(proximoCaracter);
                        break;
                    }
                }
            }
        }
        return new LineaResultado(this.getClass().getSimpleName(), fraseDescifrada, false);
    }

    /**
     * Crear una nueva clave que sea del mismo tamaño del texto. Son secuencias repetidas.
     * Ejempl: 123 -> 123123...
     * @param pClave
     * @param pLongitudFrase
     * @return
     */
    private String ObtenerNuevaClave(String pClave, int pLongFrase) {
        String nuevaClave = "";

        if (pLongFrase > pClave.length()) {
            int contador = 0;

            for (int i = 0; i < pLongFrase; i++) {
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