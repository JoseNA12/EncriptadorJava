package controlador;

import accionesCliente.TiposGenerarFrase;
import controlador.frase.MezclaConsecuDuplica;
import controlador.frase.MezclaConsecuNoDuplica;
import controlador.frase.MezclaNoConsecuNoDuplica;
import controlador.frase.Mezclar;
import datosDTO.AlgoritmosDTO;
import datosDTO.CargarDatosDTO;
import datosDTO.DatosDTO;
import datosDTO.GenerarFraseDTO;
import modelo.Alfabeto;
import modelo.Resultado;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ControladorCliente {

    private final String dirPaqueteAlgoritmos = "controlador.algoritmos";
    private final String dirPaqueteEscritores = "controlador.escritores";
    private AlfabetosDAO alfabetosDAO = new AlfabetosDAO();
    Algoritmo algoritmo;

    public AlgoritmosDTO ProcesarTexto(AlgoritmosDTO miDTO)
    {
        String textoOriginal = miDTO.getTextoOriginal().trim();
        String miAlfabeto = ObtenerSimbolosAlgabeto(miDTO.getMiAlfabeto());
        List<String> algoritmos = miDTO.getNombresAlgoritmos();
        Boolean modoCodificacion = miDTO.getModoCodificacion();

        if(!Alfabeto.esSubconjunto(textoOriginal, miAlfabeto)){
            miDTO.setMiResultado("$ERROR$");
            return miDTO;
        }

        Resultado resultado = new Resultado(textoOriginal);

        for (int i = 0; i < algoritmos.size(); i++)
        {
            try {
                String miInstancia = dirPaqueteAlgoritmos + "." + algoritmos.get(i);
                Algoritmo algoritmo = (Algoritmo) Class.forName(miInstancia).newInstance();
                algoritmo.setSimbolosAlfabetos(miAlfabeto);

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

    public AlgoritmosDTO GenerarFrase(GenerarFraseDTO miDTO)
    {
        String miAlfabeto = ObtenerSimbolosAlgabeto(miDTO.getMiAlfabeto());
        List<String> algoritmos = miDTO.getNombresAlgoritmos();
        Boolean modoCodificacion = miDTO.getModoCodificacion();
        TiposGenerarFrase tipoGenerarFrase = miDTO.getTipo();
        int longitudFrase = miDTO.getLongitud();

        Mezclar mezcla = new Mezclar();

        switch (tipoGenerarFrase){
            case NO_CONSECUTIVOS_Y_NO_DUPLICADOS:
                mezcla.setMezclaSimbolosBuilder(new MezclaNoConsecuNoDuplica());
                break;

            case CONSECUTIVOS_Y_NO_DUPLICADOS:
                mezcla.setMezclaSimbolosBuilder(new MezclaConsecuNoDuplica());
                break;

            case CONSECUTIVOS_Y_DUPLICADOS:
                mezcla.setMezclaSimbolosBuilder(new MezclaConsecuDuplica());
                break;
        }

        mezcla.construirMezcla(miAlfabeto, longitudFrase);
        String resultadoMezcla = mezcla.getMezclaSimbolos().getSimbolos();
        Resultado resultado = new Resultado(resultadoMezcla);

        for (int i = 0; i < algoritmos.size(); i++)
        {
            try {
                String miInstancia = dirPaqueteAlgoritmos + "." + algoritmos.get(i);
                algoritmo = (Algoritmo) Class.forName(miInstancia).newInstance();
                algoritmo.setSimbolosAlfabetos(miAlfabeto);

                if (modoCodificacion) {
                    resultado.agregarLineaResultado(algoritmo.Codificar(resultadoMezcla));
                }
                else {
                    resultado.agregarLineaResultado(algoritmo.Descodificar(resultadoMezcla));
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

    public List<String> CargarIDsAlfabetos() {
        List<String> lista = alfabetosDAO.recuperarIDsAlfabetos();
        return lista;
    }

    private String ObtenerSimbolosAlgabeto(String pAlfabetoSeleccionado){
        return alfabetosDAO.getTablaAlfabetos().getAlfabeto(pAlfabetoSeleccionado).getSimbolos();
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
