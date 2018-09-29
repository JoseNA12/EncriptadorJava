package datosDTO;

import java.util.List;
import accionesCliente.TipoAcciones;

/**
 * Clase DTO encargada de transportar los datos de alfabetos y algoritmos
 * para ser desplegados en pantalla
 */
public class CargarDatosDTO extends DatosDTO { //implements Serializable {

    private List<String> nombresAlfabetos;
    //private List<String> nombresAlgoritmos;


    public CargarDatosDTO(List<String> nombresAlgoritmos, TipoAcciones accion, List<String> nombresAlfabetos) {
        super(nombresAlgoritmos, accion);
        this.nombresAlfabetos = nombresAlfabetos;
    }

    public List<String> getNombresAlfabetos() {
        return nombresAlfabetos;
    }

    public void setNombresAlfabetos(List<String> nombresAlfabetos) {
        this.nombresAlfabetos = nombresAlfabetos;
    }

}
