package controlador.escritores;


import controlador.IEscritura;
import datosDTO.AlgoritmosDTO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class EscritorTXT implements IEscritura {

    @Override
    public Boolean Escribir(AlgoritmosDTO miDTO) {
        String resultado = miDTO.getMiResultado();

        try {
            File file = new File(DIRECTORIO + "bitacora.txt");

            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);

            out.println(resultado);
            out.println("-----------------------------------------------------------------");

            out.close();
            bw.close();
            fw.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
