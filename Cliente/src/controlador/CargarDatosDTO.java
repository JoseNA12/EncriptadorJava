package controlador;

import java.io.Serializable;
import java.util.List;

/**
 * Clase DTO encargada de transportar los datos de alfabetos y algoritmos
 * para ser desplegados en pantalla
 */
public class CargarDatosDTO implements Serializable {

    private List<String> nombresAlfabetos;
    private List<String> nombresAlgoritmos;

    public CargarDatosDTO() {}

    public CargarDatosDTO(List<String> nombresAlfabetos, List<String> nombresAlgoritmos) {
        this.nombresAlfabetos = nombresAlfabetos;
        this.nombresAlgoritmos = nombresAlgoritmos;
    }

    public List<String> getNombresAlfabetos() {
        return nombresAlfabetos;
    }

    public void setNombresAlfabetos(List<String> nombresAlfabetos) {
        this.nombresAlfabetos = nombresAlfabetos;
    }

    public List<String> getNombresAlgoritmos() {
        return nombresAlgoritmos;
    }

    public void setNombresAlgoritmos(List<String> nombresAlgoritmos) {
        this.nombresAlgoritmos = nombresAlgoritmos;
    }
}
