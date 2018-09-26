package controlador;

import modelo.Alfabeto;
import modelo.TablaAlfabetos;

import java.io.File;
import java.util.ArrayList;

public class AlfabetosDAO implements IValidable {
    private TablaAlfabetos tablaAlfabetos;

    public AlfabetosDAO(){
        tablaAlfabetos = new TablaAlfabetos();
    }

    @Override
    public Boolean Validar(String pEntrada) {
        return null;
    }

    public ArrayList<Alfabeto> getAlfabetos()
    {
        System.out.println("AlfabetosDAO.getAlfabetos()");
        File directory = new File("/path");
        File[] listOfFiles = directory.listFiles();

        if (listOfFiles != null)
        {
            for (File file : listOfFiles)
            {
                // Aqui se obtienen los alfabetos, almacenandolos en una lista para ser retornada
                // TODO: usar XStream para manejar la lista de alfabetos
                // Es necesario utilizar una clase que albergue los alfabetos como una lista (TablaAlfabetos)
            }
        }

        // Alfabeto de prueba
        ArrayList<Alfabeto> miLista = new ArrayList<>();
        miLista.add(new Alfabeto(1, ".ñ{}vfsbhjSDVhjab"));

        return miLista;
    }

    //TODO: implementar metodos del CRUD
    public Boolean CrearAlfabeto(AlgoritmosDTO miDTO)
    {
        System.out.println("AlfabetosDAO.crearAlfabeto(dto)");
        return true;
    }
}
