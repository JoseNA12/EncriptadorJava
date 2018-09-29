package servidor;

import accionesCliente.TipoAcciones;
import controlador.Controlador;
import datosDTO.AlgoritmosDTO;
import datosDTO.CargarDatosDTO;
import datosDTO.DatosDTO;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

import static accionesCliente.TipoAcciones.*;

public class ClienteHandler extends Thread {

    final ObjectInputStream dis;
    final ObjectOutputStream dos;
    final Socket s;

    private Controlador miControlador;

    // Constructor
    public ClienteHandler(Socket s, ObjectInputStream dis, ObjectOutputStream dos)
    {
        this.s = s;
        this.dos = dos;
        this.dis = dis;
        this.miControlador = new Controlador();
    }

    @Override
    public void run()
    {
        DatosDTO dtoRecibido; //String received;

        while (true) {
            try {

                dtoRecibido = (DatosDTO) dis.readObject();

                switch (dtoRecibido.getAccion()) {

                    case CARGAR_ALGORIT_ALFAB:
                        dos.writeObject(new CargarDatosDTO(miControlador.CargarAlgoritmos(), CARGAR_ALGORIT_ALFAB, miControlador.CargarAlfabetos()));
                        dos.flush();
                        break;

                    case PROCESAR_TEXTO:
                        AlgoritmosDTO prueba = new AlgoritmosDTO(new ArrayList<String>(), PROCESAR_TEXTO, "el servidor envió esto", "", null, true);
                        dos.writeObject(prueba);
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
