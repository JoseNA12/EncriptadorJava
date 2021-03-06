package controlador;

import modelo.Alfabeto;
import modelo.estructuras.TablaAlfabetos;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class AlfabetosDAO {
    private TablaAlfabetos tablaAlfabetos;
    private String pathTabla = "alfabetos.abc";
    //private String pathTabla = new JFileChooser().getFileSystemView().getDefaultDirectory().toString() + "/alfabetos.abc";

    public AlfabetosDAO(){
        tablaAlfabetos = new TablaAlfabetos();
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

    public Boolean crearAlfabeto(IOServidorDTO miDTO)
    {
        try {
            tablaAlfabetos.addAlfabeto(miDTO.getIdentificadorAlfabeto(), miDTO.getSimbolosAlfabeto());
            guardarAlfabetos();
            // System.out.println("AlfabetosDAO.crearAlfabeto(dto)");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ArrayList<Alfabeto> recuperarAlfabetos()
    {
        try {
            FileInputStream fileInputStream = new FileInputStream(pathTabla);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
            Object object = objectInputStream.readObject();
            objectInputStream.close();
            this.tablaAlfabetos = (TablaAlfabetos) object;
        } catch (Exception e) {
            return null;
        }

        return tablaAlfabetos.toArrayList();
    }

    public Boolean modificarAlfabeto(IOServidorDTO miDTO){
        tablaAlfabetos.editarAlfabeto(miDTO.getIdentificadorAlfabeto(), miDTO.getSimbolosAlfabeto());
        guardarAlfabetos();
        return true;
    }

    public Boolean removerAlfabeto(IOServidorDTO miDTO){
        tablaAlfabetos.borrarAlfabeto(miDTO.getIdentificadorAlfabeto());
        guardarAlfabetos();
        // System.out.println("AlfabetosDAO.removerAlfabeto(dto)");
        return true;
    }

    public ArrayList<String> recuperarIDsAlfabetos(){
        recuperarAlfabetos();
        return tablaAlfabetos.getIDs();
    }

    public TablaAlfabetos getTablaAlfabetos() {
        recuperarAlfabetos();
        return tablaAlfabetos;
    }
}
