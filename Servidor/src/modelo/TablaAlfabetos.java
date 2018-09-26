package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Estructura que contiene todos los alfabetos leidos del disco.
 */
public class TablaAlfabetos {
    private HashMap<Integer, Alfabeto> tablaAlfabetos;
    private int ultimoId;

    public TablaAlfabetos() {
        this.tablaAlfabetos = new HashMap<>();
        this.ultimoId = -1;
    }

    //Creo que tal vez no es necesario.
    /*public TablaAlfabetos(ArrayList<Alfabeto> alfabetos){
        this.tablaAlfabetos = new HashMap<>();
        this.ultimoId = -1;

        for (Alfabeto alfabeto : alfabetos) {
            Integer idActual = alfabeto.getIdentificador();

            if(idActual > this.ultimoId)
                this.ultimoId = idActual;
            tablaAlfabetos.put(idActual, alfabeto);
        }
    }*/

    public void addAlfabeto(Alfabeto alfabeto){
        this.ultimoId = this.ultimoId++;
        alfabeto.setIdentificador(this.ultimoId);
        tablaAlfabetos.put(this.ultimoId, alfabeto);
    }

    public Alfabeto getAlfabeto(Integer id){
        return tablaAlfabetos.get(id);
    }

    public void editarAlfabeto(Integer id, Alfabeto nuevoValor){
        tablaAlfabetos.replace(id, nuevoValor);
    }

    public void borrarAlfabeto(Integer id){
        tablaAlfabetos.remove(id);
    }

    public ArrayList<Alfabeto> toArrayList(){
        ArrayList<Alfabeto> alfabetos = new ArrayList<>();

        for(Map.Entry<Integer, Alfabeto> alfabeto : tablaAlfabetos.entrySet()){
            alfabetos.add(alfabeto.getValue());
        }

        return alfabetos;
    }
}
