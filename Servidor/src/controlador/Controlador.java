package controlador;

import datosDTO.AlgoritmosDTO;
import modelo.Alfabeto;
import modelo.Resultado;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.List;

public class Controlador implements IValidable {

    //public Alfabeto alfabetoDefault = new Alfabeto(0, "abcdefghijklmnñopqrstuvwxyz"); // default
    private AlfabetosDAO alfabetosDAO = new AlfabetosDAO();
    private IEscritura miEscritor;

    @Override
    public Boolean Validar(String pEntrada) {
        return null;
    }


    //TODO: eliminar codigo, se debe ejecutar en el escritor
    public void EscribirArch(AlgoritmosDTO miDTO) {
        /*System.out.println("Controlador.EscribirArch(dto)");

        Resultado result = miDTO.getMiResultado();

        File f = new File("Resultados/");

        try {
            FileWriter w = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(w);
            PrintWriter wr = new PrintWriter(bw);

            //wr.write(result.toString()); Escribir a un archivo,
        }
        catch(IOException e){
            e.printStackTrace();
        }*/
    }

    public Boolean AgregarAlfabeto(IOServidorDTO miDTO)
    {
        return alfabetosDAO.crearAlfabeto(miDTO);
    }

    public IOServidorDTO CargarAlfabetos()
    {
        System.out.println("Controlador.CargarAlfabetos()");
        return new IOServidorDTO(alfabetosDAO.recuperarAlfabetos());
    }

    public Boolean ModificarAlfabeto(IOServidorDTO miDTO){
        return alfabetosDAO.updateAlfabeto(miDTO);
    }

    public Boolean EliminarAlfabeto(IOServidorDTO miDTO){
        return alfabetosDAO.removerAlfabeto(miDTO);
    }


}
