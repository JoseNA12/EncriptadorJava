package controlador;

import datosDTO.AlgoritmosDTO;
import modelo.Resultado;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ControladorCliente {

    private final String dirPaqueteAlgoritmos = "controlador.algoritmos";
    private final String dirPaqueteEscritores = "controlador.escritores";
    private AlfabetosDAO alfabetosDAO = new AlfabetosDAO();

    public AlgoritmosDTO ProcesarTexto(AlgoritmosDTO miDTO)
    {
        String textoOriginal = miDTO.getTextoOriginal();
        String miAlfabeto = miDTO.getMiAlfabeto();
        List<String> algoritmos = miDTO.getNombresAlgoritmos();
        Boolean modoCodificacion = miDTO.getModoCodificacion();

        Resultado resultado = new Resultado(textoOriginal);

        for (int i = 0; i < algoritmos.size(); i++)
        {
            try {
                String miInstancia = dirPaqueteAlgoritmos + "." + algoritmos.get(i);
                Algoritmo algoritmo = (Algoritmo) Class.forName(miInstancia).newInstance();

                if (modoCodificacion) {
                    resultado.agregarLineaResultado(algoritmo.Codificar(textoOriginal));
                }
                else {
                    resultado.agregarLineaResultado(algoritmo.Descodificar(textoOriginal));
                }
            }
            catch(ClassNotFoundException e) {} catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        miDTO.setMiResultado(resultado.toString());
        EscribirBitacora(miDTO);
        return miDTO;
    }

    public boolean EscribirBitacora(AlgoritmosDTO miDTO) {
        try {
            String miInstancia = dirPaqueteEscritores + "." + miDTO.getFormatoEscritura();
            IEscritura escritor = (IEscritura) Class.forName(miInstancia).newInstance();
            return escritor.Escribir(miDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<String> CargarAlfabetos() {
        List<String> lista = alfabetosDAO.recuperarIDsAlfabetos();
        return lista;
    }

    public List<String> CargarFormatosEscritura(){
        List<String> escritoresActuales;

        try {
            escritoresActuales = Introspeccion.ObtenerClases(dirPaqueteEscritores);
        }
        catch (ClassNotFoundException e) {
            escritoresActuales = new ArrayList<String>();
            // e.printStackTrace();
        }
        catch (IOException e) {
            escritoresActuales = new ArrayList<String>();
            // e.printStackTrace();
        }
        return escritoresActuales;
    }

    public List<String> CargarAlgoritmos()
    {
        List<String> algoritmosActuales;

        try {
            algoritmosActuales = Introspeccion.ObtenerClases(dirPaqueteAlgoritmos);
        }
        catch (ClassNotFoundException e) {
            algoritmosActuales = new ArrayList<String>();
            // e.printStackTrace();
        }
        catch (IOException e) {
            algoritmosActuales = new ArrayList<String>();
            // e.printStackTrace();
        }
        return algoritmosActuales;
    }

}
