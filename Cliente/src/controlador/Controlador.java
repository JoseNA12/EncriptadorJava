package controlador;

import java.util.List;

public class Controlador {

    public Cliente miClienteConexion;

    public Controlador() {
        miClienteConexion = new Cliente();
    }

    public CargarDatosDTO solicitarDatosVisuales(){

        return miClienteConexion.solicitarDatosVisuales();
    }


    public void cerrarConexionServidor(){
        miClienteConexion.cerrarConexion();
    }
}
