package controlador.escritores;


import controlador.IEscritura;
import datosDTO.AlgoritmosDTO;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;

public class EscritorPDF implements IEscritura {

    @Override
    public Boolean Escribir(AlgoritmosDTO miDTO) {
        String resultado = miDTO.getMiResultado();
        resultado = resultado.replace("\t", "    ");
        String[] lineas = resultado.split("\r\n");

        try {
            File file = new File(DIRECTORIO + "bitacora.pdf");
            PDDocument documento;
            PDPage pagina;

            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
                documento = new PDDocument();

            } else {
                documento = PDDocument.load(file);
            }

            pagina = new PDPage();
            documento.addPage(pagina);

            PDPageContentStream contentStream = new PDPageContentStream(documento, pagina);
            contentStream.beginText();

            contentStream.newLineAtOffset(25, 700);
            contentStream.setFont(PDType1Font.COURIER, 12);
            contentStream.setLeading(14.5f);

            for (String linea: lineas) {
                contentStream.showText(linea);
                contentStream.newLine();
            }

            contentStream.endText();
            contentStream.close();

            documento.save(DIRECTORIO + "bitacora.pdf");
            documento.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
