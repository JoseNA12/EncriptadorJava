package datosDTO;

import accionesCliente.TipoAcciones;
import accionesCliente.TiposGenerarFrase;
import java.util.List;

public class GenerarFraseDTO extends AlgoritmosDTO {
    
    private int longitud;
    private TiposGenerarFrase tipo;

    public GenerarFraseDTO(int longitud, TiposGenerarFrase tipo, List<String> nombresAlgoritmos, TipoAcciones accion, String textoOriginal, String miResultado, String miAlfabeto, Boolean modoCodificacion, String formatoEscritura) {
        super(nombresAlgoritmos, accion, textoOriginal, miResultado, miAlfabeto, modoCodificacion, formatoEscritura);
        this.longitud = longitud;
        this.tipo = tipo;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public TiposGenerarFrase getTipo() {
        return tipo;
    }

    public void setTipo(TiposGenerarFrase tipo) {
        this.tipo = tipo;
    }
}
