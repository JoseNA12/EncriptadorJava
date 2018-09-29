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

    private final String paqueteAlgoritmos = "controlador.algoritmos";


    public AlgoritmosDTO ProcesarTexto(AlgoritmosDTO miDTO)
    {
        String textoOriginal = miDTO.getTextoOriginal();
        String miAlfabeto = miDTO.getAlfabeto();
        List<String> algoritmos = miDTO.getNombresAlgoritmos();
        Boolean modoCodificacion = miDTO.getModoCodificacion();

        Resultado resultado = new Resultado(textoOriginal);

        for (int i = 0; i < algoritmos.size(); i++)
        {
            try {
                String miInstancia = paqueteAlgoritmos + "." + algoritmos.get(i);
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

    public List<String> CargarAlgoritmos()
    {
        List<String> algoritmosActuales;

        try {
            algoritmosActuales = ObtenerClases(paqueteAlgoritmos);
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

        for(int i = 0; i < classes.size(); i++){
            miClases.add(classes.get(i).getSimpleName());
        }

        return miClases;
        // return classes.toArray(new Class[classes.size()]);
    }

    private List<Class> EncontrarClases(File directory, String packageName) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<Class>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();

        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(EncontrarClases(file, packageName + "." + file.getName()));
            }
            else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }
}
