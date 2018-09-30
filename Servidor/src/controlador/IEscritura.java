package controlador;

import datosDTO.AlgoritmosDTO;

import javax.swing.*;

public interface IEscritura {
    String DIRECTORIO = new JFileChooser().getFileSystemView().getDefaultDirectory().toString() + "/bitacoras/";

    Boolean Escribir(AlgoritmosDTO miDTO);
}
