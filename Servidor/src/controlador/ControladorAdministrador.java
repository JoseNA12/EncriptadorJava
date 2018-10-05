package controlador;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ControladorAdministrador {

    private final String dirPaqueteAlgoritmos = "controlador.algoritmos";
    private AlfabetosDAO alfabetosDAO = new AlfabetosDAO();


    public Boolean AgregarAlfabeto(IOServidorDTO miDTO)
    {
        return alfabetosDAO.crearAlfabeto(miDTO);
    }

    public IOServidorDTO CargarAlfabetos()
    {
        return new IOServidorDTO(alfabetosDAO.recuperarAlfabetos());
    }

    public Boolean ModificarAlfabeto(IOServidorDTO miDTO){
        return alfabetosDAO.modificarAlfabeto(miDTO);
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

    public boolean AgregarClaseAlgoritmo(File miArch, File miArchDestino){
        try {
            Files.copy(miArch.toPath(), miArchDestino.toPath());
            return true;

        } catch (IOException e) {
            return false;
        }
    }

    public boolean EliminarClaseAlgoritmo(String pNombreClase){
        File fileDestino = new File((Paths.get(System.getProperty("user.dir") + "/src/controlador/algoritmos/" + pNombreClase + ".java")).toString());

        if(fileDestino.delete()) { return true; }
        else { return false; }
    }

    public void AbrirDirectorioBitacoras() throws IOException {
        final String dir = System.getProperty("user.dir") + "\\bitacoras"; //Obtiene el path del folder de bitacoras
        Runtime.getRuntime().exec("explorer.exe /open," + dir);
    }
}
