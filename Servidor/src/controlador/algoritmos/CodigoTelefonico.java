package controlador.algoritmos;

import controlador.Algoritmo;
import modelo.LineaResultado;

import java.util.HashMap;
import java.util.Map;

public class CodigoTelefonico extends Algoritmo {

    // Forma del diccionario: <Llave, <Digito, Posicion>>
    // -> Llave: caracter del alfabeto
    // -> Digito: numero del teclado telefónico
    // -> Posicion: posicion del digito acorde al teclado telefónico, 2-abc: a tiene posicion 1, b tiene posicion 2, y así
    static Map<String, Map<Integer, Integer>> distTecladoMap = new HashMap<String, Map<Integer, Integer>>();

    public LineaResultado Codificar(String pFrase){
        CrearDiccionario();
        String fraseCifrada = "";
        String simboloEspacio = "* ";

        for (int i = 0; i < pFrase.length(); i++){
            Map<Integer, Integer> caracter = distTecladoMap.get(String.valueOf(pFrase.charAt(i)));

            for (Map.Entry<Integer, Integer> datos : caracter.entrySet()){
                if (datos.getKey() == -1) {
                    fraseCifrada += simboloEspacio;
                }
                else {
                    fraseCifrada += String.valueOf(datos.getKey()) + String.valueOf(datos.getValue()) + " ";
                }
            }
        }
        return new LineaResultado(this.getClass().getSimpleName(), fraseCifrada, true);
    }

    public LineaResultado Descodificar(String pFrase){
        CrearDiccionario();
        String fraseDescifrada = "";
        String simboloEspacio = "* ";

        for (int i = 0; i < pFrase.length(); i++){
            for (Map.Entry<String, Map<Integer, Integer>> datos : distTecladoMap.entrySet()){
                for (Map.Entry<Integer, Integer> datos2 : datos.getValue().entrySet()){ // solo hace una iteración
                    try {
                        if (String.valueOf(datos2.getKey()).equals(String.valueOf(pFrase.charAt(i))) &&
                                String.valueOf(datos2.getValue()).equals(String.valueOf(pFrase.charAt(i + 1)))) {
                            fraseDescifrada += datos.getKey();
                        }
                    } catch (StringIndexOutOfBoundsException e) {  }
                }
            }
            if (String.valueOf(pFrase.charAt(i)).equals("*")) { fraseDescifrada += " "; }
        }
        return new LineaResultado(this.getClass().getSimpleName(), fraseDescifrada, false);
    }

    private void CrearDiccionario(){ // <Simbolo, digito>
        String alfabeto = super.getSimbolosAlfabetos();
        int contador = 0;
        Integer numeroTeclado = 2;
        // 8 por: 2, 3, 4, 5, 6, 7, 8, 9 (los digitos del teclado telefónico)
        int cantCarecteresPorDigito = alfabeto.length() / 8;

        if (alfabeto.length() >= 8){
            for (int i = 0; i < alfabeto.length(); i++){
                AgregarItemDiccionario(String.valueOf(alfabeto.charAt(i)), numeroTeclado, contador);
                contador += 1;

                if (contador >= cantCarecteresPorDigito && numeroTeclado != 9){
                    contador = 0;
                    numeroTeclado += 1;
                }
            }
        }
        AgregarItemDiccionario(" ", -1, -1);
    }

    private static void AgregarItemDiccionario(String pSimbolo, int pNumTeclado, int pCont){
        Map<Integer, Integer> mapTemp = new HashMap<Integer, Integer>();
        mapTemp.put(pNumTeclado, pCont);
        distTecladoMap.put(pSimbolo, mapTemp);
    }

}
