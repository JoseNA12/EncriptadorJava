package controlador.algoritmos;

import modelo.LineaResultado;

public class SustitucionVigenere extends Algoritmo {

    private static final String clave = "23";

    public static LineaResultado Codificar(String pFrase, String pAlfabeto) {
        String nuevaClave = ObtenerNuevaClave(clave, pFrase.length());
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
        // fraseDescrifrada // <- CONTIENE EL RESULTADO!!
        return null;
    }

    public static LineaResultado Descodificar(String pFrase, String pAlfabeto) {
        String nuevaClave = ObtenerNuevaClave(clave, pFrase.length());
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
        // fraseDescrifrada // <- CONTIENE EL RESULTADO!!
        return null;
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
