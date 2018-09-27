package servidor;

import controlador.CargarDatosDTO;
import controlador.Controlador;
import controlador.Funciones;

import java.io.*;
import java.text.*;
import java.util.*;
import java.net.*;

import static controlador.Funciones.*;

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
        Object received; //String received;
        String toreturn;
        while (true) {
            try {
                // receive the answer from client
                received = dis.readObject();

                // write on output stream based on the answer from the client
                switch (Funciones.valueOf(received.toString())) {

                    case CARGAR_ALGORIT_ALFAB:
                        dos.writeObject(new CargarDatosDTO(miControlador.CargarAlfabetos(), miControlador.CargarAlgoritmos()));
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
            this.s.close(); // lo agregué yo
            this.dis.close();
            this.dos.close();
            System.out.println("- Cliente desconectado: " + s.getLocalAddress() + ", " + s.getPort());

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
