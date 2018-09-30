package controlador;

import datosDTO.AlgoritmosDTO;

public interface IEscritura {
    String DIRECTORIO = "";

    Boolean Escribir(AlgoritmosDTO miDTO);
}
