package controlador;

import modelo.Alfabeto;

import java.util.ArrayList;
import java.util.List;

public class IOServidorDTO {
    private String identificadorAlfabeto;
    private String simbolosAlfabeto;

    private ArrayList<Alfabeto> alfabetos;

    //TODO: agregar atributos conforme se necesiten


    public IOServidorDTO(String identificadorAlfabeto, String simbolosAlfabeto) {
        this.identificadorAlfabeto = identificadorAlfabeto;
        this.simbolosAlfabeto = simbolosAlfabeto;
    }

    public IOServidorDTO(ArrayList<Alfabeto> alfabetos) {
        this.alfabetos = alfabetos;
    }

    public IOServidorDTO(String identificadorAlfabeto) {
        this.identificadorAlfabeto = identificadorAlfabeto;
    }

    public String getIdentificadorAlfabeto() {
        return identificadorAlfabeto;
    }

    public void setIdentificadorAlfabeto(String identificadorAlfabeto) {
        this.identificadorAlfabeto = identificadorAlfabeto;
    }

    public String getSimbolosAlfabeto() {
        return simbolosAlfabeto;
    }

    public void setSimbolosAlfabeto(String simbolosAlfabeto) {
        this.simbolosAlfabeto = simbolosAlfabeto;
    }

    public ArrayList<Alfabeto> getAlfabetos() {
        return alfabetos;
    }

    public void setAlfabetos(ArrayList<Alfabeto> alfabetos) {
        this.alfabetos = alfabetos;
    }
}
