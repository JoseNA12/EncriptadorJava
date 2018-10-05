package controlador.algoritmos;

import controlador.Algoritmo;
import modelo.LineaResultado;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PalabraClave extends Algoritmo {

    private static final String clave = "tango";

    public LineaResultado Codificar(String mensaje){
        String codigo="";
        char actual;
        int actual_clave=0;

        for(int i=0;i<mensaje.length();i++){
            actual=mensaje.charAt(i);
            if(Character.isWhitespace(actual)){
                codigo=codigo+" ";
                actual_clave=0;
            }
            else{
                if(actual_clave>=clave.length()){
                    actual_clave=0;
                }
                codigo=codigo+clave.charAt(actual_clave);
                actual_clave++;
            }
        }
        return CodificarAux(codigo, mensaje);
    }

    public LineaResultado CodificarAux(String codigo, String mensaje){
        char actual_codigo;
        char actual_mensaje;
        String fraseCodificada = "";
        String abecedario = super.getSimbolosAlfabetos();

        for(int i=0;i<mensaje.length();i++){
           actual_mensaje= mensaje.charAt(i);
           actual_codigo= codigo.charAt(i);
           if(!Character.isWhitespace(actual_mensaje)){
               fraseCodificada += buscaValor(actual_codigo,actual_mensaje,abecedario,1);
           }
           else{
               fraseCodificada += " ";
           }
        }
        return new LineaResultado(this.getClass().getSimpleName(), fraseCodificada, true);
    }

    public char buscaValor(char codigo, char mensaje, String abecedario, int modo){
        int valor1=abecedario.indexOf(mensaje)+1;
        int valor2=abecedario.indexOf(codigo)+1;
        int valor_final=0;
        char devolver;
        if(modo==1){
            valor_final=valor1+valor2;
            if(valor_final>abecedario.length()){
                valor_final-=abecedario.length();
            }
            devolver=abecedario.charAt(valor_final-1);
        }
        else{
            valor_final=valor1-valor2;
            if(valor_final<1){
                valor_final+=abecedario.length();
            }
            devolver=abecedario.charAt(valor_final-1);
        }
        return devolver;
    }

    public LineaResultado Descodificar(String mensaje){
        char actual_codigo;
        char actual_mensaje;
        String Final="";
        int aux_clave=0;
        String abecedario = super.getSimbolosAlfabetos();

        for(int i=0;i<mensaje.length();i++){
            actual_mensaje= mensaje.charAt(i);
            if(aux_clave>=clave.length()){
                aux_clave=0;
            }
            actual_codigo= clave.charAt(aux_clave);
            if(!Character.isWhitespace(actual_mensaje)){
                Final=Final+buscaValor(actual_codigo,actual_mensaje,abecedario,0);
                aux_clave++;
            }
            else{
                Final=Final+" ";
                aux_clave=0;
            }
        }
        return new LineaResultado(this.getClass().getSimpleName(), Final, false);
    }
}
