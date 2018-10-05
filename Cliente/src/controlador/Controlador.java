package controlador;

import datosDTO.AlgoritmosDTO;
import datosDTO.CargarDatosDTO;
import datosDTO.DatosDTO;

import java.util.List;

public class Controlador {

    private Cliente miClienteConexion;

    public Controlador() {
        miClienteConexion = new Cliente();
    }

    public CargarDatosDTO SolicitarDatosVisuales(DatosDTO pDTO){

        return miClienteConexion.solicitarDatosVisuales(pDTO);
    }

    public AlgoritmosDTO ProcesarTexto(AlgoritmosDTO miProcesadoDTO){
        return miClienteConexion.ProcesarTexto(miProcesadoDTO);
    }


    public void CerrarConexionServidor(){
        miClienteConexion.cerrarConexion();
    }
}
