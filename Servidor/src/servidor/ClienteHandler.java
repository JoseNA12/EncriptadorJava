package servidor;

import controlador.ControladorCliente;
import datosDTO.AlgoritmosDTO;
import datosDTO.CargarDatosDTO;
import datosDTO.DatosDTO;
import datosDTO.GenerarFraseDTO;

import java.io.*;
import java.net.*;

import static accionesCliente.TipoAcciones.*;

public class ClienteHandler extends Thread {

    final ObjectInputStream dis;
    final ObjectOutputStream dos;
    final Socket s;

    private ControladorCliente miControlador;


    public ClienteHandler(Socket s, ObjectInputStream dis, ObjectOutputStream dos)
    {
        this.s = s;
        this.dos = dos;
        this.dis = dis;
        this.miControlador = new ControladorCliente();
    }

    @Override
    public void run()
    {
        DatosDTO dtoRecibido;

        while (true) {
            try
            {
                dtoRecibido = (DatosDTO) dis.readObject();

                switch (dtoRecibido.getAccion()) {

                    case CARGAR_ALGORIT_ALFAB:

                        dos.writeObject(new CargarDatosDTO(
                                miControlador.CargarAlgoritmos(),
                                CARGAR_ALGORIT_ALFAB, miControlador.CargarIDsAlfabetos(),
                                miControlador.CargarFormatosEscritura()));
                        dos.flush();
                        break;

                    case PROCESAR_TEXTO:

                        dos.writeObject(miControlador.ProcesarTexto((AlgoritmosDTO) dtoRecibido));
                        dos.flush();
                        break;

                    case PROCESAR_TEXTO_GENERAR_FRASE:

                        dos.writeObject(miControlador.GenerarFrase((GenerarFraseDTO) dtoRecibido));
                        dos.flush();
                        break;

                    case CERRAR_CONEXION:
                        break;

                    default:
                        dos.writeUTF("Invalid input");
                        dos.flush();
                        break;
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            catch (SocketException e){
                // Connection reset, el cliente cierra directamente el programa
                break;
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        cerrarRecursos();
    }

    private void cerrarRecursos(){
        try
        {
            System.out.println("- Cliente desconectado: " + s.getLocalAddress() + ", " + s.getPort());
            this.s.close();
            this.dis.close();
            this.dos.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
