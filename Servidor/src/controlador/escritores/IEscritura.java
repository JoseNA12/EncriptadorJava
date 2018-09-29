package controlador.escritores;

import controlador.AlgoritmosDTO;

public interface IEscritura {
    String DIRECTORIO = "";

    Boolean Escribir(AlgoritmosDTO miDTO);
}
