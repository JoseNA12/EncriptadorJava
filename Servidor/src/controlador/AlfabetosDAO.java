package controlador;

import modelo.Alfabeto;
import modelo.TablaAlfabetos;

import java.io.*;
import java.util.ArrayList;

public class AlfabetosDAO implements IValidable {
    private TablaAlfabetos tablaAlfabetos;
    private String pathTabla = "C:\\Users\\davva\\Desktop\\alfabetos.alf";

    public AlfabetosDAO(){
        tablaAlfabetos = new TablaAlfabetos();
    }

    @Override
    public Boolean Validar(String pEntrada) {
        return null;
    }

    public ArrayList<Alfabeto> getAlfabetos()
    {

        try {
            FileInputStream fileInputStream = new FileInputStream(pathTabla);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
            Object object = objectInputStream.readObject();
            objectInputStream.close();
            this.tablaAlfabetos = (TablaAlfabetos) object;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tablaAlfabetos.toArrayList();

        /*System.out.println("AlfabetosDAO.getAlfabetos()");
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
        //miLista.add(new Alfabeto(1, ".ñ{}vfsbhjSDVhjab"));

        return miLista;*/
    }

    public void guardarAlfabetos(){
        try {
            FileOutputStream fos = new FileOutputStream(pathTabla);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tablaAlfabetos);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //TODO: implementar metodos del CRUD
    public Boolean crearAlfabeto(IOServidorDTO miDTO)
    {
        tablaAlfabetos.addAlfabeto(miDTO.getIdentificadorAlfabeto(), miDTO.getSimbolosAlfabeto());
        guardarAlfabetos();
        System.out.println("AlfabetosDAO.crearAlfabeto(dto)");
        return true;
    }

    public Boolean recuperarAlfabeto(){
        return true;
    }

    public Boolean updateAlfabeto(){
        return true;
    }

    public Boolean removerAlfabeto(){
        return true;
    }
}
