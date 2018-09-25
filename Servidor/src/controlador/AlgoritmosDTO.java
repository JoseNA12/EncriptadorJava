package controlador;

import modelo.Alfabeto;
import modelo.Resultado;
import modelo.TipoAlgoritmo;

import java.util.List;

public class AlgoritmosDTO {

    private String textoOriginal;
    private String parametroArg;
    private Resultado miResultado;
    private Alfabeto miAlfabeto;
    private List<TipoAlgoritmo> algoritmos;
    private Boolean modoCodificacion;

    public AlgoritmosDTO(String textoOriginal, Resultado miResultado, Alfabeto miAlfabeto, List<TipoAlgoritmo> algoritmos, Boolean modoCodificacion) {
        System.out.println("new DTO(texto, alfabeto, algoritmos, modo)");
        this.textoOriginal = textoOriginal;
        this.miResultado = miResultado;
        this.miAlfabeto = miAlfabeto;
        this.algoritmos = algoritmos;
        this.modoCodificacion = modoCodificacion;
    }

    public AlgoritmosDTO(Alfabeto miAlfabeto) {
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

    public Resultado getMiResultado() {
        return miResultado;
    }

    public void setMiResultado(Resultado miResultado) {
        this.miResultado = miResultado;
    }

    public Alfabeto getAlfabeto() {
        return this.miAlfabeto;
    }

    public void setIdAlfabeto(Alfabeto miAlfabeto) {
        this.miAlfabeto = miAlfabeto;
    }

    public List<TipoAlgoritmo> getAlgoritmos() {
        return algoritmos;
    }

    public void setAlgoritmos(List<TipoAlgoritmo> algoritmos) {
        this.algoritmos = algoritmos;
    }

    public Boolean getModoCodificacion() {
        return modoCodificacion;
    }

    public void setModoCodificacion(Boolean modoCodificacion) {
        this.modoCodificacion = modoCodificacion;
    }
}
