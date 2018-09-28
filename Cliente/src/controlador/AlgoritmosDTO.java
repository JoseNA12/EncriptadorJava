package controlador;

import java.io.Serializable;
import java.util.List;

public class AlgoritmosDTO implements Serializable {

    private String textoOriginal;
    private String miResultado;
    private String miAlfabeto;
    private List<String> algoritmos;
    private Boolean modoCodificacion;
    private static final long serialVersionUID = 1113799434508676095L;

    public AlgoritmosDTO(String textoOriginal, String miResultado, String miAlfabeto, List<String> algoritmos, Boolean modoCodificacion) {
        this.textoOriginal = textoOriginal;
        this.miResultado = miResultado;
        this.miAlfabeto = miAlfabeto;
        this.algoritmos = algoritmos;
        this.modoCodificacion = modoCodificacion;
    }

    public String getTextoOriginal() {
        return textoOriginal;
    }

    public void setTextoOriginal(String textoOriginal) {
        this.textoOriginal = textoOriginal;
    }

    public String getMiResultado() {
        return miResultado;
    }

    public void setMiResultado(String miResultado) {
        this.miResultado = miResultado;
    }

    public String getAlfabeto() {
        return this.miAlfabeto;
    }

    public void setIdAlfabeto(String miAlfabeto) {
        this.miAlfabeto = miAlfabeto;
    }

    public List<String> getAlgoritmos() {
        return algoritmos;
    }

    public void setAlgoritmos(List<String> algoritmos) {
        this.algoritmos = algoritmos;
    }

    public Boolean getModoCodificacion() {
        return modoCodificacion;
    }

    public void setModoCodificacion(Boolean modoCodificacion) {
        this.modoCodificacion = modoCodificacion;
    }
}
