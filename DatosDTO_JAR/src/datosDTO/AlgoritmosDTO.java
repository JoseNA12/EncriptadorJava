package datosDTO;

import java.util.List;
import accionesCliente.TipoAcciones;

/**
 * Clase DTO encargada de transportar los datos requeridos para el
 * procesamiento de información
 */
public class AlgoritmosDTO extends DatosDTO {

    private String textoOriginal;
    private String miResultado;
    private String miAlfabeto;
    private Boolean modoCodificacion;
    private String formatoEscritura;


    public AlgoritmosDTO(List<String> nombresAlgoritmos, TipoAcciones accion, String textoOriginal, String miResultado, String miAlfabeto, Boolean modoCodificacion, String formatoEscritura) {
        super(nombresAlgoritmos, accion);
        this.textoOriginal = textoOriginal;
        this.miResultado = miResultado;
        this.miAlfabeto = miAlfabeto;
        this.modoCodificacion = modoCodificacion;
        this.formatoEscritura = formatoEscritura;
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

    public String getMiAlfabeto() {
        return miAlfabeto;
    }

    public void setMiAlfabeto(String miAlfabeto) {
        this.miAlfabeto = miAlfabeto;
    }

    public Boolean getModoCodificacion() {
        return modoCodificacion;
    }

    public void setModoCodificacion(Boolean modoCodificacion) {
        this.modoCodificacion = modoCodificacion;
    }

    public String getFormatoEscritura() {
        return formatoEscritura;
    }

    public void setFormatoEscritura(String formatoEscritura) {
        this.formatoEscritura = formatoEscritura;
    }
    
    
}
