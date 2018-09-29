package datosDTO;

import java.io.Serializable;
import java.util.List;
import accionesCliente.TipoAcciones;

public class DatosDTO implements Serializable {

    private List<String> nombresAlgoritmos;
    private TipoAcciones accion;

    public DatosDTO(List<String> nombresAlgoritmos, TipoAcciones accion) {
        this.nombresAlgoritmos = nombresAlgoritmos;
        this.accion = accion;
    }

    public List<String> getNombresAlgoritmos() {
        return nombresAlgoritmos;
    }

    public void setNombresAlgoritmos(List<String> nombresAlgoritmos) {
        this.nombresAlgoritmos = nombresAlgoritmos;
    }

    public TipoAcciones getAccion() {
        return accion;
    }

    public void setAccion(TipoAcciones accion) {
        this.accion = accion;
    }
}
