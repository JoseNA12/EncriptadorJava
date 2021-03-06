package modelo.estructuras;

import modelo.Alfabeto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Estructura que contiene todos los alfabetos leidos del disco.
 */
public class TablaAlfabetos implements Serializable {
    private HashMap<String, Alfabeto> tablaAlfabetos;

    public TablaAlfabetos() {
        this.tablaAlfabetos = new HashMap<>();
    }

    public void addAlfabeto(String identificador, String simbolos) throws Exception{
        Alfabeto alfabeto = new Alfabeto(identificador, simbolos);

        if (tablaAlfabetos.containsKey(identificador) | !alfabeto.Validar(""))
            throw new Exception("Identificador de alfabeto repetido o alfabeto invalido.");

        tablaAlfabetos.put(identificador, alfabeto);
    }

    public Alfabeto getAlfabeto(String id){
        return tablaAlfabetos.get(id);
    }

    public void editarAlfabeto(String identificador, String simbolos){
        Alfabeto nuevoValor = new Alfabeto(identificador, simbolos);
        tablaAlfabetos.replace(identificador, nuevoValor);
    }

    public void borrarAlfabeto(String id){
        tablaAlfabetos.remove(id);
    }

    public ArrayList<Alfabeto> toArrayList(){
        ArrayList<Alfabeto> alfabetos = new ArrayList<>();

        for(Map.Entry<String, Alfabeto> alfabeto : tablaAlfabetos.entrySet()){
            alfabetos.add(alfabeto.getValue());
        }

        return alfabetos;
    }

    public ArrayList<String> getIDs(){
        ArrayList<String> ids = new ArrayList<>();

        for(Map.Entry<String, Alfabeto> alfabeto : tablaAlfabetos.entrySet()){
            ids.add(alfabeto.getKey());
        }

        return ids;
    }
}
