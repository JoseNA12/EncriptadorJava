package controlador;

import java.util.List;

public class Controlador {

    public Cliente miClienteConexion;

    public Controlador() {
        miClienteConexion = new Cliente();
    }

    public CargarDatosDTO SolicitarDatosVisuales(){

        return miClienteConexion.solicitarDatosVisuales();
    }

    public AlgoritmosDTO ProcesarTexto(AlgoritmosDTO miProcesadoDTO){
        return miClienteConexion.ProcesarTexto(miProcesadoDTO);
    }


    public void CerrarConexionServidor(){
        miClienteConexion.cerrarConexion();
    }
}
