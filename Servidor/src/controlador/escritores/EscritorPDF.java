package controlador.escritores;


import controlador.IEscritura;
import datosDTO.AlgoritmosDTO;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class EscritorPDF implements IEscritura {

    @Override
    public Boolean Escribir(AlgoritmosDTO miDTO) {
        String resultado = miDTO.getMiResultado();

        try {
            File file = new File(DIRECTORIO + "bitacora.pdf");
            PDDocument documento;

            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }


            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
