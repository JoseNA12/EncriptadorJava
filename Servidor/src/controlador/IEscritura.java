package controlador;

import datosDTO.AlgoritmosDTO;

import javax.swing.*;

public interface IEscritura {
    String DIRECTORIO = "bitacoras/";

    Boolean Escribir(AlgoritmosDTO miDTO);
}
