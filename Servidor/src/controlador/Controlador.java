package controlador;

import controlador.escritores.IEscritura;
import modelo.Alfabeto;
import modelo.Resultado;
import modelo.TipoAlgoritmo;

import java.io.*;
import java.util.List;

public class Controlador implements IValidable {

    //public Alfabeto alfabetoDefault = new Alfabeto(0, "abcdefghijklmnñopqrstuvwxyz"); // default
    private AlfabetosDAO alfabetosDAO = new AlfabetosDAO();
    private IEscritura miEscritor;

    @Override
    public Boolean Validar(String pEntrada) {
        return null;
    }

    public void ProcesarTexto(AlgoritmosDTO miDTO)
    {
        String textoOriginal = miDTO.getTextoOriginal();
        Alfabeto miAlfabeto = miDTO.getAlfabeto();
        List<TipoAlgoritmo> algoritmos = miDTO.getAlgoritmos();
        Boolean modoCodificacion = miDTO.getModoCodificacion();


        Resultado resultado = new Resultado(textoOriginal);

        for (int i = 0; i < algoritmos.size(); i++)
        {
            if (modoCodificacion)
            {
                switch (algoritmos.get(i))
                {
                    case TRASLETRALETRA:
                        resultado.agregarLineaResultado(TrasLetraLetra.Codificar(textoOriginal));
                        break;

                    case CODTELEFONICO:
                        resultado.agregarLineaResultado(CodTelefonico.Codificar(textoOriginal));
                        break;

                    case SUSTVIGENERE:
                        resultado.agregarLineaResultado(SustVigenere.Codificar(textoOriginal, "", miAlfabeto.getSimbolos()));
                        break;
                }
            }
            else
            {
                switch (algoritmos.get(i))
                {
                    case TRASLETRALETRA:
                        resultado.agregarLineaResultado(TrasLetraLetra.Decodificar(textoOriginal));
                        break;

                    case CODTELEFONICO:
                        resultado.agregarLineaResultado(CodTelefonico.Decodificar(textoOriginal));
                        break;

                    case SUSTVIGENERE:
                        resultado.agregarLineaResultado(SustVigenere.Decodificar(textoOriginal));
                        break;

                }
            }
        }
        miDTO.setMiResultado(resultado);
    }

    //TODO: creo que ya no es necesario debido a que la clase algoritmo define el parametro
    public void ProcesarTexto(AlgoritmosDTO miDTO, String pParametroArg) {}

    //TODO: eliminar codigo, se debe ejecutar en el escritor
    public void EscribirArch(AlgoritmosDTO miDTO) {
        /*System.out.println("Controlador.EscribirArch(dto)");

        Resultado result = miDTO.getMiResultado();

        File f = new File("Resultados/");

        try {
            FileWriter w = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(w);
            PrintWriter wr = new PrintWriter(bw);

            //wr.write(result.toString()); Escribir a un archivo,
        }
        catch(IOException e){
            e.printStackTrace();
        }*/
    }

    public Boolean AgregarAlfabeto(IOServidorDTO miDTO)
    {
        return alfabetosDAO.crearAlfabeto(miDTO);
    }

    public IOServidorDTO CargarAlfabetos()
    {
        System.out.println("Controlador.CargarAlfabetos()");
        return new IOServidorDTO(alfabetosDAO.recuperarAlfabetos());
    }

    public Boolean ModificarAlfabeto(IOServidorDTO miDTO){
        return alfabetosDAO.updateAlfabeto(miDTO);
    }

    public Boolean EliminarAlfabeto(IOServidorDTO miDTO){
        return alfabetosDAO.removerAlfabeto(miDTO);
    }


}
