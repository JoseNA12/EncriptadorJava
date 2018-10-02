package vista;


import accionesCliente.TipoAcciones;
import controlador.Controlador;
import datosDTO.CargarDatosDTO;
import datosDTO.DatosDTO;

import java.util.ArrayList;
import java.util.Scanner;

public class Consola {

    private static Scanner in;
    private static final int OPCIONES_ALGORITMOS_INICIALES = 1; // 0 - Confirmar
    private static Controlador miControlador;

    private static ArrayList<String> alfabetos;
    private static ArrayList<String> algoritmos;
    private static ArrayList<String> escritores;

    private static ArrayList<String> algoritmos_seleccionados;
    private static boolean modoCodificacion;

    private static String alfabetoActual;
    private static String entradaActual;

    //private static List

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
        alfabetos = (ArrayList<String>) miDTO.getNombresAlfabetos();
        algoritmos = (ArrayList<String>) miDTO.getNombresAlfabetos();
        escritores = (ArrayList<String>) miDTO.getFormatosEscritura();

        //algoritmos = Arrays.asList(TipoAlgoritmo.values());
        algoritmos_seleccionados = new ArrayList<>();
        modoCodificacion = false; //true = codificar; false = descodificar (default)

        in = new Scanner(System.in);
    }

    private static void run(){
        desplegarMenuInicio();
    }

    private static void desplegarMenuInicio(){
        System.out.println("Consola.desplegarMenuInicio()");
        println(Consola_Display.MENU_PRINCIPAL.getValor());
        println(Consola_Display.SELECCION_OPCION.getValor());
        int seleccion = obtener_Seleccion();

        switch (seleccion){
            case 1: //Herramienta de cifrado
                desplegarPantallaAlgoritmos();
                desplegarPantallaModo();
                desplegarPantallaAlfabetos();
                desplegarPantallaEntrada();
                //Llamar a crear DTO
                /*AlgoritmosDTO dto = new AlgoritmosDTO(
                        entradaActual,
                        null,
                        alfabetoActual,
                        algoritmos_seleccionados,
                        modoCodificacion);*/
                //miControlador.ProcesarTexto(dto); //Sets resultado
                //miControlador.EscribirArch(dto);
                //Algun metodo para desplegarlo en pantalla
                break;
            /*case 2:
                desplegarPantallaAgregarAlfabeto();
                miControlador.AgregarAlfabeto(new AlgoritmosDTO(nuevoAlfabeto));*/
            case 3: //Salir
                break;
            default:
                break;
        }
    }

    private static void desplegarPantallaAlgoritmos(){
        System.out.println("Consola.desplegarPantallaAlgoritmos()");
        println(Consola_Display.SELECCION_ALGORITMO_HEADER.getValor());
        boolean confirmado = false;

        while (!confirmado){
            println(Consola_Display.SELECCION_ALGORITMO_OPCIONES.getValor());
            imprimirListaAlgoritmos();
            int seleccion = obtener_Seleccion();

            if(seleccion == 0){ //Confirmar
                confirmado = true;
            } else if(
                    seleccion >= OPCIONES_ALGORITMOS_INICIALES &&
                    seleccion <= algoritmos.size() + OPCIONES_ALGORITMOS_INICIALES
            ){
                toggle_Seleccion_Algoritmo(algoritmos.get(seleccion - OPCIONES_ALGORITMOS_INICIALES));
            }
        }
    }

    private static void desplegarPantallaModo(){
        System.out.println("Consola.desplegarPantallaModo()");
        println(Consola_Display.SELECCION_MODO.getValor());
        int seleccion = obtener_Seleccion();

        switch (seleccion){
            case 1:
                //Codificar
                modoCodificacion = true;
                break;
            case 2:
                //Descodificar
                modoCodificacion = false;
                break;
            default:
                break;
        }
    }

    private static void desplegarPantallaAlfabetos(){
        System.out.println("Consola.desplegarPantallaAlfabetos()");
        println(Consola_Display.SELECCION_ALFABETO.getValor());
        imprimirListaAlfabetos();
        int seleccion = obtener_Seleccion();
        //alfabetoActual = obtenerAlfabetoSeleccionado(seleccion);
    }

    private static void desplegarPantallaEntrada(){
        System.out.println("Consola.desplegarPantallaEntrada()");
        println(Consola_Display.ENTRADA_TEXTO.getValor());
        in.nextLine(); //Necesario debido a una pulga con nextLine()
        String entrada = in.nextLine();
        //Validar entrada
        entradaActual = entrada;
    }

    /*private static void desplegarPantallaAgregarAlfabeto(){
        System.out.println("Consola.desplegarPantallaAgregarAlfabeto()");
        println(Consola_Display.ENTRADA_SIMBOLOS_ALFABETO.getValor());
        in.nextLine(); //Necesario debido a una pulga con nextLine()
        String simbolos = in.nextLine();
        println(Consola_Display.ENTRADA_ID_ALFABETO.getValor());
        int idAlfabeto = in.nextInt();
        nuevoAlfabeto = new Alfabeto(idAlfabeto, simbolos);
    }*/

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

    private static void imprimirListaAlfabetos(){
        for (String alfabeto : alfabetos){
            println("* " + alfabeto);
        }
    }

    private static int obtener_Seleccion(){
        return in.nextInt();
    }

    /*private static Alfabeto obtenerAlfabetoSeleccionado(int identificador){
        for(Alfabeto alfabeto : alfabetos){
            if(alfabeto.getIdentificador() == identificador)
                return alfabeto;
        }

        return null;
    }*/

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

    public static Boolean validarEntrada(String entrada, int min, int max){
        return null;
    }

    public static Boolean validarEntrada(String entrada, ArrayList<String> conjuntoValido){
        return null;
    }
}
