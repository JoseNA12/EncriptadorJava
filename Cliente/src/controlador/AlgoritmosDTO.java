package controlador;

import java.util.List;

public class AlgoritmosDTO {

    private String textoOriginal;
    private String parametroArg;
    private Object miResultado;
    private Object miAlfabeto;
    private List<Object> algoritmos;
    private Boolean modoCodificacion;

    public AlgoritmosDTO(String textoOriginal, Object miResultado, Object miAlfabeto, List<Object> algoritmos, Boolean modoCodificacion) {
        System.out.println("new DTO(texto, alfabeto, algoritmos, modo)");
        this.textoOriginal = textoOriginal;
        this.miResultado = miResultado;
        this.miAlfabeto = miAlfabeto;
        this.algoritmos = algoritmos;
        this.modoCodificacion = modoCodificacion;
    }

    public AlgoritmosDTO(Object miAlfabeto) {
        System.out.println("new DTO(nuevoAlfabeto)");
        this.miAlfabeto = miAlfabeto;
    }

    public String getTextoOriginal() {
        return textoOriginal;
    }

    public void setTextoOriginal(String textoOriginal) {
        this.textoOriginal = textoOriginal;
    }

    public String getParametroArg() {
        return parametroArg;
    }

    public void setParametroArg(String parametroArg) {
        this.parametroArg = parametroArg;
    }

    public Object getMiResultado() {
        return miResultado;
    }

    public void setMiResultado(Object miResultado) {
        this.miResultado = miResultado;
    }

    public Object getAlfabeto() {
        return this.miAlfabeto;
    }

    public void setIdAlfabeto(Object miAlfabeto) {
        this.miAlfabeto = miAlfabeto;
    }

    public List<Object> getAlgoritmos() {
        return algoritmos;
    }

    public void setAlgoritmos(List<Object> algoritmos) {
        this.algoritmos = algoritmos;
    }

    public Boolean getModoCodificacion() {
        return modoCodificacion;
    }

    public void setModoCodificacion(Boolean modoCodificacion) {
        this.modoCodificacion = modoCodificacion;
    }
}
