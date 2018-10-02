package controlador.algoritmos;

import controlador.Algoritmo;
import modelo.LineaResultado;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class CodificacionBinaria extends Algoritmo {

    public List<String> binario;

    public LineaResultado Codificar(String mensaje){
        String fraseCodificada = "";
        String abecedario = super.getSimbolosAlfabetos();
        LlenarAlfabeto(abecedario);

        for(int i=0;i<mensaje.length();i++){
            char actual= mensaje.charAt(i);
            if(Character.isWhitespace(actual)){
                fraseCodificada += "* ";
            }
            else {
                int posicion = abecedario.indexOf(actual);
                String Cifra = binario.get(posicion);
                Cifra=Cifra+ " ";
                fraseCodificada += Cifra;
            }
        }
        return new LineaResultado(this.getClass().getSimpleName(), fraseCodificada, true);
    }

    public LineaResultado Descodificar(String pEntrada){
        String numero="";
        String Decodificado="";
        String letra="";
        int posicion;
        char actual;
        String abecedario = super.getSimbolosAlfabetos();
        LlenarAlfabeto(abecedario);

        for(int i=0;i<pEntrada.length();i++){
            actual=pEntrada.charAt(i);
            if(Character.isWhitespace(actual)){
                if(numero.equals("*")){
                    Decodificado += " ";
                    numero="";
                }
                else{
                    posicion=binario.indexOf(numero);
                    letra= String.valueOf(abecedario.charAt(posicion));
                    numero="";
                    Decodificado += letra;
                }
            }
            else{
                numero += actual;
            }
        }
        return new LineaResultado(this.getClass().getSimpleName(), Decodificado, false);
    }

    public void LlenarAlfabeto(String abecedario)
    {
        binario = new ArrayList();

        for(int i=0;i<abecedario.length();i++){
            String Entrante= Integer.toBinaryString(i+1);
            while (Entrante.length() < 5) {
                Entrante = "0" + Entrante;
            }
            binario.add(Entrante);
        }
    }
}
