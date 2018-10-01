package controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controlador implements IValidable {

    private final String dirPaqueteAlgoritmos = "controlador.algoritmos";
    private AlfabetosDAO alfabetosDAO = new AlfabetosDAO();
    private IEscritura miEscritor;

    @Override
    public Boolean Validar(String pEntrada) {
        return null;
    }

    public Boolean AgregarAlfabeto(IOServidorDTO miDTO)
    {
        return alfabetosDAO.crearAlfabeto(miDTO);
    }

    public IOServidorDTO CargarAlfabetos()
    {
        return new IOServidorDTO(alfabetosDAO.recuperarAlfabetos());
    }

    public Boolean ModificarAlfabeto(IOServidorDTO miDTO){
        return alfabetosDAO.updateAlfabeto(miDTO);
    }

    public Boolean EliminarAlfabeto(IOServidorDTO miDTO){
        return alfabetosDAO.removerAlfabeto(miDTO);
    }

    public List<String> CargarAlgoritmos(){
        List<String> algoritmosActuales;

        try {
            algoritmosActuales = Introspeccion.ObtenerClases(dirPaqueteAlgoritmos);
        }
        catch (ClassNotFoundException e) {
            algoritmosActuales = new ArrayList<String>();
            // e.printStackTrace();
        }
        catch (IOException e) {
            algoritmosActuales = new ArrayList<String>();
            // e.printStackTrace();
        }
        return algoritmosActuales;
    }
}
