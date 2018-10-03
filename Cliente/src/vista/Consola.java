package vista;

import accionesCliente.TipoAcciones;
import accionesCliente.TiposGenerarFrase;
import controlador.Controlador;
import datosDTO.AlgoritmosDTO;
import datosDTO.CargarDatosDTO;
import datosDTO.DatosDTO;
import datosDTO.GenerarFraseDTO;

import java.util.ArrayList;
import java.util.Scanner;

public class Consola {

    private static Scanner in; //Objeto de entrada de datos
    private static final int OPCIONES_ALGORITMOS_INICIALES = 1; // 0 - Confirmar
    private static Controlador miControlador;

    //region Datos para algoritmos
    private static ArrayList<String> alfabetos;
    private static ArrayList<String> algoritmos;
    private static ArrayList<String> escritores;

    private static ArrayList<String> algoritmos_seleccionados = new ArrayList<>();
    private static boolean codificar = false; //true = codificar; false = descodificar (default)

    private static String alfabetoActual;
    private static String escritorActual;
    private static String entradaActual;
    //endregion

    //region Datos para generacion de frases
    private static int longitudFrase;
    private static TiposGenerarFrase tipoGeneracionFrase;
    private static TipoAcciones tipoAccion = TipoAcciones.PROCESAR_TEXTO;
    //endregion

    public static void main(String args[]){
        inicializar();
        run();
    }

    private static void inicializar(){
        miControlador = new Controlador();
        CargarDatosDTO miDTO = miControlador.SolicitarDatosVisuales(
                new DatosDTO(
                        null,
                        TipoAcciones.CARGAR_ALGORIT_ALFAB)
        );
        algoritmos = (ArrayList<String>) miDTO.getNombresAlgoritmos();
        alfabetos = (ArrayList<String>) miDTO.getNombresAlfabetos();
        escritores = (ArrayList<String>) miDTO.getFormatosEscritura();

        in = new Scanner(System.in);
    }

    private static void run(){
        desplegarMenuInicio();
    }

    private static void callProcesar(){
        AlgoritmosDTO respuesta;
        if(tipoAccion.equals(TipoAcciones.PROCESAR_TEXTO_GENERAR_FRASE)){
             respuesta = miControlador.ProcesarTexto(
                     new GenerarFraseDTO(
                             longitudFrase,
                             tipoGeneracionFrase,
                             algoritmos_seleccionados,
                             tipoAccion,
                             entradaActual,
                             "",
                             alfabetoActual, // identificador de alfabeto
                             codificar,
                             escritorActual // true -> Codificar
                     )
             );
        } else{
            respuesta = miControlador.ProcesarTexto(
                    new AlgoritmosDTO(
                            algoritmos_seleccionados,
                            tipoAccion,
                            entradaActual,
                            "",
                            alfabetoActual, // identificador de alfabeto
                            codificar,
                            escritorActual // true -> Codificar
                    )
            );
        }
        imprimirResultado(respuesta);
    }

    private static void desplegarMenuInicio(){
        System.out.println("Consola.desplegarMenuInicio()");
        println(Consola_Display.MENU_PRINCIPAL.getValue());
        println(Consola_Display.SELECCION_OPCION.getValue());

        int seleccion = obtener_Seleccion();

        switch (seleccion){
            case 1: //Herramienta de cifrado
                desplegarPantallaAlgoritmos();
                desplegarPantallaModo();
                desplegarPantallaAlfabetos();
                desplegarPantallaEscritores();
                desplegarPantallaEntrada();

                callProcesar();

                desplegarMenuInicio();
                break;
            case 2:
                desplegarMenuGeneracion();
                break;
            case 3:
                //No hacer nada implica terminar el proceso
                break;
            default:
                break;
        }
    }

    private static void desplegarMenuGeneracion(){
        System.out.println("Consola.desplegarMenuGeneracion()");
        println(Consola_Display.MENU_GENERACION.getValue());
        println(Consola_Display.SELECCION_OPCION.getValue());
        int seleccion = obtener_Seleccion();

        switch (seleccion){
            case 1:
                desplegarPantallaGeneracion();
                if(tipoAccion != TipoAcciones.PROCESAR_TEXTO)
                    desplegarPantallaLongitud();
                desplegarMenuGeneracion();
                break;
            case 2:
                desplegarMenuInicio();
                break;
            default:
                break;
        }
    }

    private static void desplegarPantallaAlgoritmos(){
        System.out.println("Consola.desplegarPantallaAlgoritmos()");
        println(Consola_Display.SELECCION_ALGORITMO_HEADER.getValue());
        boolean confirmado = false;

        while (!confirmado){
            println(Consola_Display.SELECCION_ALGORITMO_OPCIONES.getValue());
            imprimirListaAlgoritmos();
            int seleccion = obtener_Seleccion();

            if(seleccion == 0){ //Confirmar
                confirmado = true;
            } else {
                toggle_Seleccion_Algoritmo(algoritmos.get(seleccion - OPCIONES_ALGORITMOS_INICIALES));
            }
        }
    }

    private static void desplegarPantallaModo(){
        System.out.println("Consola.desplegarPantallaModo()");
        println(Consola_Display.SELECCION_MODO.getValue());
        int seleccion = obtener_Seleccion();

        switch (seleccion){
            case 1:
                //Codificar
                codificar = true;
                break;
            case 2:
                //Descodificar
                codificar = false;
                break;
            default:
                break;
        }
    }

    private static void desplegarPantallaAlfabetos(){
        System.out.println("Consola.desplegarPantallaAlfabetos()");
        println(Consola_Display.SELECCION_ALFABETO.getValue());
        imprimirListaGenerica(alfabetos);

        int seleccion = obtener_Seleccion();

        alfabetoActual = alfabetos.get(seleccion - 1);
    }

    private static void desplegarPantallaEscritores(){
        System.out.println("Consola.desplegarPantallaEscritores()");
        println(Consola_Display.SELECCION_ESCRITOR.getValue());
        imprimirListaGenerica(escritores);

        int seleccion = obtener_Seleccion();

        escritorActual = escritores.get(seleccion - 1);
    }

    private static void desplegarPantallaEntrada(){
        System.out.println("Consola.desplegarPantallaEntrada()");
        println(Consola_Display.ENTRADA_TEXTO.getValue());
        in.nextLine(); //Necesario debido a una pulga con nextLine()
        entradaActual = in.nextLine();
        //TODO: Validar entrada
    }

    private static void desplegarPantallaGeneracion(){
        TiposGenerarFrase[] tipos = TiposGenerarFrase.values();

        println(Consola_Display.SELECCION_GENERACION_HEADER.getValue());
        imprimirListaTiposGeneracion(tipos);
        println(Consola_Display.SELECCION_OPCION.getValue());

        int seleccion = obtener_Seleccion();

        if(seleccion == 0){
            tipoAccion = TipoAcciones.PROCESAR_TEXTO;
        } else {
            tipoAccion = TipoAcciones.PROCESAR_TEXTO_GENERAR_FRASE;
            tipoGeneracionFrase = tipos[seleccion - 1];
        }
    }

    private static void desplegarPantallaLongitud(){
        println(Consola_Display.ENTRADA_LONGITUD_GENERACION.getValue());
        longitudFrase = in.nextInt();
    }

    private static void imprimirListaAlgoritmos(){
        int numOpcion = OPCIONES_ALGORITMOS_INICIALES;

        for (String algoritmo : algoritmos) {
            print(numOpcion + " - " + algoritmo + " ");

            if(algoritmos_seleccionados.contains(algoritmo)){
                println("[X]");
            } else{
                println("[ ]");
            }
            numOpcion++;
        }
    }

    private static void imprimirListaGenerica(ArrayList<String> lista){
        int i = 1;

        for (String item : lista){
            println(i + " - " + item);
            i++;
        }
    }

    private static void imprimirListaTiposGeneracion(TiposGenerarFrase[] tiposGeneracion){
        int i = 1;

        println(Consola_Display.SELECCION_GENERACION_OPCIONES.getValue());
        for(TiposGenerarFrase tipo : tiposGeneracion){
            println(i + " - " + tipo.toString());
            i++;
        }
    }

    private static void imprimirResultado(AlgoritmosDTO respuesta){
        String resultado = respuesta.getMiResultado();
        if(resultado.equals("$ERROR$")){
            println(Consola_Display.ERROR_ALFABETO.getValue());
        }else {
            println(resultado);
        }
    }

    private static int obtener_Seleccion(){
        return in.nextInt();
    }

    private static void toggle_Seleccion_Algoritmo(String algoritmo){
        if(algoritmos_seleccionados.contains(algoritmo)){
            algoritmos_seleccionados.remove(algoritmo);
        }else{
            algoritmos_seleccionados.add(algoritmo);
        }
    }

    private static void print(String s){
        System.out.print(s);
    }

    private static void println(String s){
        System.out.println(s);
    }

    public static Boolean validarEntrada(int entrada, int min, int max){
        return (entrada >= min && entrada >= max);
    }

    public static Boolean validarEntrada(String entrada, ArrayList<String> conjuntoValido){
        return conjuntoValido.contains(entrada);
    }
}
