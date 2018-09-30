package controlador.escritores;

import controlador.IEscritura;
import datosDTO.AlgoritmosDTO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class EscritorTxt implements IEscritura {

    String ruta = "src/bitacoras/filename.txt";

    @Override
    public Boolean Escribir(AlgoritmosDTO miDTO) {

        boolean escribio = false;
        try {
            String contenido = "Contenido de ejemplo";
            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                escribio = file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(contenido);
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return escribio;
    }
}
