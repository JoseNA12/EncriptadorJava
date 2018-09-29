package controlador;

import datosDTO.AlgoritmosDTO;
import modelo.Alfabeto;
import modelo.Resultado;

import java.io.*;
import java.net.URL;
import java.util.*;

public class Controlador implements IValidable {

    public Alfabeto alfabetoDefault = new Alfabeto(0, "abcdefghijklmnñopqrstuvwxyz"); // default
    private AlfabetosDAO misAlfabetos = new AlfabetosDAO();
    private IEscritura miEscritura;

    @Override
    public Boolean Validar(String pEntrada) {
        return null;
    }


}
