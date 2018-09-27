package controlador;

import modelo.Alfabeto;
import modelo.Resultado;
import modelo.TipoAlgoritmo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Controlador implements IValidable {

    public Alfabeto alfabetoDefault = new Alfabeto(0, "abcdefghijklmnñopqrstuvwxyz"); // default
    private AlfabetosDAO misAlfabetos = new AlfabetosDAO();
    private IEscritura miEscritura;

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

    public void ProcesarTexto(AlgoritmosDTO miDTO, String pParametroArg) {}

    // public void EstablecerAlfabeto(String pSimbolos) {} Eliminado del diagrama

    public Boolean AgregarAlfabeto(AlgoritmosDTO miDTO)
    {
        return misAlfabetos.CrearAlfabeto(miDTO);
    }

    public void EscribirArch(AlgoritmosDTO miDTO) {
        System.out.println("Controlador.EscribirArch(dto)");

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
        }
    }

    public List<String> CargarAlfabetos() // Llamar al DAO (misAlfabetos.getAlfabetos();) y obtener el nombre los alfabetos
    {
        List<String> lista = new ArrayList<String>();
        lista.add("Alfabeto 1");
        lista.add("Alfabeto 2");
        lista.add("Alfabeto 3");

        return lista;
    }

    public List<String> CargarAlgoritmos() // Llamar al DAO (misAlfabetos.getAlfabetos();) y obtener el nombre los alfabetos
    {
        List<String> lista = new ArrayList<String>();
        lista.add("Algoritmo 1");
        lista.add("Algoritmo 2");
        lista.add("Algoritmo 3");

        return lista;
    }
}
