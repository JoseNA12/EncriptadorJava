package controlador;

import datosDTO.AlgoritmosDTO;
import modelo.Alfabeto;
import modelo.Resultado;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ControladorCliente {

    private final String dirPaqueteAlgoritmos = "controlador.algoritmos";
    private final String dirPaqueteEscritores = "controlador.escritores";


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
        return miDTO;
    }

    public List<String> CargarAlfabetos() // Llamar al DAO (misAlfabetos.getAlfabetos();) y obtener el nombre los alfabetos
    {
        List<String> lista = new ArrayList<String>();
        lista.add("Alfabeto 1");
        lista.add("Alfabeto 2");
        lista.add("Alfabeto 3");

        return lista;
    }

    public List<String> CargarFormatosEscritura(){
        List<String> escritoresActuales;

        try {
            escritoresActuales = ObtenerClases(dirPaqueteEscritores);
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
            algoritmosActuales = ObtenerClases(dirPaqueteAlgoritmos);
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

    /**
     * Dada la dirección de un paquete, encuentra todas las clases contenidas en dicho paquete,
     * retornando una lista con los nombres propiamentes de las clases.
     * @param pNombrePaquete
     * @return
     * @throws ClassNotFoundException
     * @throws IOException
     */
    private List<String> ObtenerClases(String pNombrePaquete) throws ClassNotFoundException, IOException { // Class[]
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = pNombrePaquete.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<File>();

        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList<Class> classes = new ArrayList<Class>();

        for (File directory : dirs) {
            classes.addAll(EncontrarClases(directory, pNombrePaquete));
        }

        List<String> miClases = new ArrayList<String>();

        // obtener los nom,bres de las clases
        for(int i = 0; i < classes.size(); i++){
            miClases.add(classes.get(i).getSimpleName());
        }

        return miClases;
        // return classes.toArray(new Class[classes.size()]);
    }

    private List<Class> EncontrarClases(File pDirectorio, String pNombrePaquete) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<Class>();
        if (!pDirectorio.exists()) {
            return classes;
        }
        File[] files = pDirectorio.listFiles();

        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(EncontrarClases(file, pNombrePaquete + "." + file.getName()));
            }
            else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(pNombrePaquete + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }
}
