package controlador;

import modelo.Alfabeto;

import java.io.*;
import java.net.URL;
import java.util.*;

public class Controlador implements IValidable {

    public Alfabeto alfabetoDefault = new Alfabeto(0, "abcdefghijklmnñopqrstuvwxyz"); // default
    private AlfabetosDAO misAlfabetos = new AlfabetosDAO();
    private IEscritura miEscritura;

    @Override
    public Boolean Validar(String pEntrada) {
        return null;
    }

    /*public void ProcesarTexto(AlgoritmosDTO miDTO)
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
                        resultado.agregarLineaResultado(TrasposicionLetraLetra.Codificar(textoOriginal));
                        break;

                    case CODTELEFONICO:
                        resultado.agregarLineaResultado(CodigoTelefonico.Codificar(textoOriginal));
                        break;

                    case SUSTVIGENERE:
                        resultado.agregarLineaResultado(SustitucionVigenere.Codificar(textoOriginal, "", miAlfabeto.getSimbolos()));
                        break;
                }
            }
            else
            {
                switch (algoritmos.get(i))
                {
                    case TRASLETRALETRA:
                        resultado.agregarLineaResultado(TrasposicionLetraLetra.Decodificar(textoOriginal));
                        break;

                    case CODTELEFONICO:
                        resultado.agregarLineaResultado(CodigoTelefonico.Decodificar(textoOriginal));
                        break;

                    case SUSTVIGENERE:
                        resultado.agregarLineaResultado(SustitucionVigenere.Decodificar(textoOriginal));
                        break;

                }
            }
        }
        miDTO.setMiResultado(resultado);
    }*/

    /*public void ProcesarTexto(AlgoritmosDTO miDTO, String pParametroArg) {}

    // public void EstablecerAlfabeto(String pSimbolos) {} Eliminado del diagrama

    public Boolean AgregarAlfabeto(AlgoritmosDTO miDTO)
    {
        return misAlfabetos.CrearAlfabeto(miDTO);
    }

    public void EscribirArch(AlgoritmosDTO miDTO) {
        System.out.println("Controlador.EscribirArch(dto)");

        //Resultado result = miDTO.getMiResultado();

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
    }*/

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
        String paquete = "controlador.algoritmos";
        List<String> algoritmosActuales;

        try {
            algoritmosActuales = ObtenerClases(paquete);
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
