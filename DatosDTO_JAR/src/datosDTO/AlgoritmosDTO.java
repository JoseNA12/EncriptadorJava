package datosDTO;

import java.util.List;
import accionesCliente.TipoAcciones;

/**
 * Clase DTO encargada de transportar los datos requeridos para el
 * procesamiento de información
 */
public class AlgoritmosDTO extends DatosDTO { // implements Serializable {

    private String textoOriginal;
    private String miResultado;
    private String miAlfabeto;
    //private List<String> algoritmos;
    private Boolean modoCodificacion;
    // private static final long serialVersionUID = 1113799434508676095L;


    public AlgoritmosDTO(List<String> nombresAlgoritmos, TipoAcciones accion, String textoOriginal, String miResultado, String miAlfabeto, Boolean modoCodificacion) {
        super(nombresAlgoritmos, accion);
        this.textoOriginal = textoOriginal;
        this.miResultado = miResultado;
        this.miAlfabeto = miAlfabeto;
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

    public Boolean getModoCodificacion() {
        return modoCodificacion;
    }

    public void setModoCodificacion(Boolean modoCodificacion) {
        this.modoCodificacion = modoCodificacion;
    }
}
