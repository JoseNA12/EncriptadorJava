package controlador;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class Introspeccion {

    /**
     * Dada la dirección de un paquete, encuentra todas las clases contenidas en dicho paquete,
     * retornando una lista con los nombres propiamentes de las clases.
     * @param pNombrePaquete
     * @return
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public static List<String> ObtenerClases(String pNombrePaquete) throws ClassNotFoundException, IOException { // Class[]
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

    public static List<Class> EncontrarClases(File pDirectorio, String pNombrePaquete) throws ClassNotFoundException {
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
